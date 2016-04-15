package com.baiyi.order.web;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import com.baiyi.order.util.WebContext;

//截图上传?
@SuppressWarnings("serial")
public class UploadFileServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("get method");
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tempStr = WebContext.webRootPath + WebContext.TEMP;
		File tempFile = new File(tempStr);
		if (!tempFile.exists()) {
			tempFile.mkdirs();
		}

		String captureStr = WebContext.webRootPath + WebContext.CAPTURE;
		File uploadFile = new File(captureStr);
		if (!uploadFile.exists()) {
			uploadFile.mkdirs();
		}

		try {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(1024 * 4);
			factory.setRepository(tempFile);
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setSizeMax(1024 * 1024 * 4);

			List<FileItem> list = upload.parseRequest(request);

			for (FileItem item : list) {
				if (item.isFormField()) {// 普通的表单域
					continue;
				}

				String name = item.getName();
				if (StringUtils.isBlank(name)) {
					continue;
				}
				// TODO 最终路径
				String dest = captureStr + File.separator + name.replace("_min", "");

				File temp = new File(dest);
				if (temp.exists() && temp.isFile()) {
					temp.delete();
				}

				File srcFile = new File(captureStr + File.separator + name);
				item.write(srcFile);
				File destFile = new File(dest);
				FileUtils.copyFile(srcFile, destFile);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}

		PrintWriter out = response.getWriter();
		out.write("upload success!");
		out.flush();
		out.close();
	}
}
