package com.baiyi.order.web;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.baiyi.order.model.Terminal;
import com.baiyi.order.model.TerminalConnect;
import com.baiyi.order.service.TerminalService;
import com.baiyi.order.util.FormatUtil;
import com.baiyi.order.util.WebContext;
import com.baiyi.order.vo.Record;

import net.sf.json.JSONObject;

//serversocket线程:主要用于监测终端连线,更新终端时间,发送开关机指令...
public class Server extends Thread {

	private TerminalService terminalService = null;
	/* ServerSocket */
	private ServerSocket server = null;
	private final static int SERVERPORT = 5599;// TODO
	private final static int POOLSIZE = 10;

	// private final Map<String, String> hostMap = new HashMap<>();

	// 初始化终端连线信息
	public Server(TerminalService terminalService) {
		this.terminalService = terminalService;
		List<Terminal> terminals = terminalService.findList();
		if (CollectionUtils.isNotEmpty(terminals)) {
			for (Terminal terminal : terminals) {
				Integer terminalId = terminal.getId();

				Record record = new Record();
				record.setTerminalId(terminalId);
				record.setTerminalNo(terminal.getTerminalNo());
				record.setLocation(terminal.getLocation());

				Record history = terminalService.findLastRecord(terminalId);
				record.setDate(history == null ? null : history.getDate());

				WebContext.ConnectMap.put(terminal.getTerminalNo(), record);
			}
		}
	}

	@Override
	public void run() {
		try {
			this.server = new ServerSocket(SERVERPORT);
			System.out.println("服务端正在启动中...");

			int processor = Runtime.getRuntime().availableProcessors();
			ExecutorService executorService = Executors.newFixedThreadPool(POOLSIZE * processor);

			while (true) {
				Socket client = server.accept();
				executorService.execute(new Contact(client, terminalService));

				// String hostname = client.getInetAddress().getHostName();
				// hostMap.put(hostname, null);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class Contact implements Runnable {
	private TerminalService terminalService = null;

	/* ClientSocket */
	private Socket client = null;
	private final static int BUFFERSIZE = 999999;
	private final static int FREQUENCY = 20;

	private ObjectInputStream ois = null;
	private ObjectOutputStream oos = null;

	public Contact(Socket client, TerminalService terminalService) {
		this.client = client;
		this.terminalService = terminalService;
		try {
			client.setReceiveBufferSize(BUFFERSIZE);
			client.setKeepAlive(true);
			client.setSoTimeout(FREQUENCY * 1000 + 2 * 60 * 1000);

			oos = new ObjectOutputStream(client.getOutputStream());
			ois = new ObjectInputStream(new BufferedInputStream(client.getInputStream()));

			System.out.println("终端连线中...");
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("连接终端失败...");
		}
	}

	public void run() {
		TerminalConnect terminalConnect = null;
		String ip = null;
		String terminalNo = null;
		String image = null;

		while (client != null && client.isConnected() && !client.isClosed()) {
			try {
				oos.writeObject(FREQUENCY);
				oos.flush();

				boolean send = false;

				ip = client.getInetAddress().getHostAddress();
				// {terminalNo:terminalNo,image:image}// TODO
				String data = ois.readUTF();
				JSONObject info = JSONObject.fromObject(data);

				terminalNo = (String) info.get("terminalNo");
				image = (String) info.get("image");
				if (StringUtils.isNotBlank(terminalNo) || StringUtils.isNotBlank(image)) {
					Terminal terminal = terminalService.find(terminalNo);
					if (terminal != null) {
						Integer terminalId = terminal.getId();
						terminalConnect.setTerminalId(terminalId);

						terminalConnect = new TerminalConnect();

						terminalConnect.setIp(ip);
						terminalConnect.setOnline(true);
						terminalConnect.setImage(image);
						terminalConnect.setDate(new Date());
						terminalService.saveConnect(terminalConnect);

						// 保存到内存
						Record record = new Record();// TODO
						record.setTerminalId(terminalId);
						record.setTerminalNo(terminalNo);
						record.setLocation(terminal.getLocation());
						record.setIp(ip);
						record.setOnline(true);
						record.setImage(image);
						record.setDate(new Date());

						WebContext.ConnectMap.put(terminalNo, record);
					} else {
						System.out.println("错误的终端...");
					}

				} else {
					send = true;
					oos.writeObject("1||" + FREQUENCY);
					oos.flush();

				}

				/* ------------远程管理 ---------------- */
				// 校正时间
				String date = FormatUtil.dateToString(new Date(), null);
				String dateStr = WebContext.checkTimeMap.containsKey(terminalNo) ? date : "";
				dateStr = (send ? "2||" : "") + dateStr;

				oos.writeObject(dateStr);
				oos.flush();

				String string = (String) ois.readObject();
				if (string.equals("changetimesuccess")) {
					System.out.println("終端號：" + terminalNo + "時間修改成功");
					WebContext.checkTimeMap.remove(terminalNo);
				}

				// 重启
				if (send) {
					if (WebContext.rebootMap.containsKey(terminalNo)) {
						oos.writeObject("3||" + "reboot");
						oos.flush();
						if ("rebootsuccess".equals((String) ois.readObject())) {
							System.out.println("終端號：" + terminalNo + "重啟成功");
							WebContext.rebootMap.remove(terminalNo);
						}
					} else {
						oos.writeObject("3||");
						oos.flush();
						ois.readObject();
					}
				}
				// ===================關閉===================//TODO
				if (send) {
					if (WebContext.shutDownMap.containsKey(terminalNo)) {
						System.out.println("發送給" + terminalNo + "關閉");
						oos.writeObject("4||" + "shutdown");
						oos.flush();
						String terminalReply = (String) ois.readObject();
						if (terminalReply.equals("shutdown")) {
							System.out.println("終端號：" + terminalNo + "關閉成功");
							WebContext.shutDownMap.remove(terminalNo);
						}
					} else {
						oos.writeObject("4||");
						oos.flush();
						ois.readObject();
					}
				}

				// ===================啟動遠端===================
				if (send) {
					if (WebContext.bootTeamViewer.containsKey(terminalNo)) {
						System.out.println("發送給" + terminalNo + "啟動遠端");
						oos.writeObject("6||" + "bootTeamViewer");

						oos.flush();
						String terminalReply = (String) ois.readObject();
						if (terminalReply.equals("bootsuccess")) {
							System.out.println("終端號：" + terminalNo + "啟動遠端成功");
							WebContext.bootTeamViewer.remove(terminalNo);
						}
					} else {
						oos.writeObject("6||");
						oos.flush();
						ois.readObject();
					}
				}
				// ===================關閉遠端===================
				if (send) {
					if (WebContext.closeTeamViewer.containsKey(terminalNo)) {
						System.out.println("發送給" + terminalNo + "關閉遠端程序");
						oos.writeObject("7||" + "closeTeamViewer");

						oos.flush();
						String terminalReply = (String) ois.readObject();
						if (terminalReply.equals("closesuccess")) {
							System.out.println("終端號：" + terminalNo + "關閉遠端程序成功");
							WebContext.closeTeamViewer.remove(terminalNo);
						}
					} else {
						oos.writeObject("7||");
						oos.flush();
						ois.readObject();
					}
				}
			} catch (Exception exception) {
				if (StringUtils.isNotBlank(terminalNo)) {
					Terminal terminal = terminalService.find(terminalNo);
					if (terminal != null) {
						Integer terminalId = terminal.getId();
						terminalConnect.setTerminalId(terminalId);

						terminalConnect = new TerminalConnect();

						// terminalConnect.setIp(ip);
						// terminalConnect.setOnline(true);
						// terminalConnect.setImage(image);
						terminalConnect.setDate(new Date());
						terminalService.saveConnect(terminalConnect);

						// 保存到内存
						Record record = new Record();
						record.setTerminalId(terminalId);
						record.setTerminalNo(terminalNo);
						record.setLocation(terminal.getLocation());
						record.setDate(new Date());

						WebContext.ConnectMap.put(terminalNo, record);
					} else {
						System.out.println("错误的终端...");
					}
				}
			} finally {
				try {
					client.close();
					client = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
