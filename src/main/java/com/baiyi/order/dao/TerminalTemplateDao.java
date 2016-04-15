package com.baiyi.order.dao;

import java.util.List;

import com.baiyi.order.model.TerminalTemplate;
import com.baiyi.order.util.EnumList.TemplateDownEnum;

public interface TerminalTemplateDao {

	public void save(TerminalTemplate terminalTemplate);

	public void delete(Integer id);

	public void delete(TerminalTemplate terminalTemplate);

	public void delete(Integer[] ids);

	public void delete(List<TerminalTemplate> terminalTemplates);

	public void update(TerminalTemplate terminalTemplate);

	public void merge(TerminalTemplate terminalTemplate);

	public TerminalTemplate find(Integer id);

	public TerminalTemplate find(Integer terminalId, Integer templateId);

	public TerminalTemplate findUsed(Integer terminalId);

	public List<TerminalTemplate> findList();

	public List<TerminalTemplate> findList(Integer terminalId, Integer templateId, TemplateDownEnum status, Boolean renew, Boolean used);

}
