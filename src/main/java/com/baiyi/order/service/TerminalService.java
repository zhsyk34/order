package com.baiyi.order.service;

import java.util.Date;
import java.util.List;

import com.baiyi.order.model.Template;
import com.baiyi.order.model.Terminal;
import com.baiyi.order.model.TerminalConnect;
import com.baiyi.order.model.TerminalTemplate;
import com.baiyi.order.model.TerminalTime;
import com.baiyi.order.util.EnumList.TemplateDownEnum;
import com.baiyi.order.util.EnumList.TerminalTypeEnum;
import com.baiyi.order.vo.Record;
import com.baiyi.order.vo.TerminalTemplateVO;
import com.baiyi.order.vo.TerminalVO;

public interface TerminalService {

	/* 单独操作 */
	public void save(Terminal terminal);

	public void delete(Integer id);

	public void delete(Terminal terminal);

	public void delete(Integer[] ids);

	public void delete(List<Terminal> terminals);

	public void update(Terminal terminal);

	public void merge(Terminal terminal);

	public Terminal find(Integer id);

	public Terminal find(String terminalNo);

	public List<Terminal> findList();

	public List<Terminal> findList(String terminalNo, TerminalTypeEnum type, Integer userId);

	public List<Terminal> findList(String terminalNo, TerminalTypeEnum type, Integer userId, String sort, String order, int pageNo, int pageSize);

	public int count(String terminalNo, TerminalTypeEnum type, Integer userId);

	public boolean exist(Integer id, String terminalNo);// 重名查询

	/* VO */
	public List<TerminalVO> findVOList();

	public List<TerminalVO> findVOList(String terminalNo, TerminalTypeEnum type, Integer userId);

	public List<TerminalVO> findVOList(String terminalNo, TerminalTypeEnum type, Integer userId, String sort, String order, int pageNo, int pageSize);

	/* 关联更新 */
	public void update(Terminal terminal, List<Date> boots, List<Date> shuts, Integer[] seatIds);// 全部关联更新

	/* 终端连线 */
	public void saveConnect(TerminalConnect terminalConnect);// 保存记录

	public List<Record> findRecordList(String terminalNo, Date begin, Date end, Boolean online);// 历史记录查询

	public List<Record> findRecordList(String terminalNo, Date begin, Date end, Boolean online, String sort, String order, int pageNo, int pageSize);

	public int countRecord(String terminalNo, Date begin, Date end, Boolean online);

	public Record findLastRecord(Integer terminalId);// 历史记录中的最后连线

	public List<Terminal> findOnLine();// 当前在线终端,内存中读取

	public List<Record> findRecord(String terminalNo, Boolean online);// 当前连线信息

	/* 座位 */
	public void updateSeat(Integer terminalId, Integer[] seatIds);// 座位

	public List<Integer> findSeat(Integer terminalId);

	/* 开关机时间 */
	public void updateTime(Integer terminalId, List<Date> boots, List<Date> shuts);// 开关机

	public List<TerminalTime> findTime(Integer terminalId);

	/* 模板 */
	public void mergeTemplate(TerminalTemplate terminalTemplate);// 模板

	public TerminalTemplate findTemplate(Integer terminalId, Integer templateId);

	public Template findUsedTemplate(Integer terminalId);// 已启用的模板

	public List<TerminalTemplate> findTemplateList(Integer terminalId, Integer templateId, TemplateDownEnum status, Boolean renew, Boolean used);// 模板状态

	public List<TerminalTemplateVO> findTemplateList(Integer terminalId, String templateName);// 模板状态

	public void setUsedTemplate(Integer terminalId, Integer templateId);// 启用模板

}
