package com.baiyi.order.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;

import com.baiyi.order.dao.TerminalTemplateDao;
import com.baiyi.order.model.TerminalTemplate;
import com.baiyi.order.util.EnumList.TemplateDownEnum;
import com.baiyi.order.util.ValidateUtil;

@Repository
public class TerminalTemplateDaoImpl extends CommonsDaoImpl<TerminalTemplate> implements TerminalTemplateDao {

	@Override
	public void delete(List<TerminalTemplate> terminalTemplates) {
		super.delete(terminalTemplates);
	}

	@SuppressWarnings("unchecked")
	@Override
	public TerminalTemplate find(Integer terminalId, Integer templateId) {
		String queryString = "from TerminalTemplate as terminalTemplate where terminalTemplate.terminalId = ? and terminalTemplate.templateId = ?";
		List<TerminalTemplate> list = (List<TerminalTemplate>) hibernateTemplate.find(queryString, terminalId, templateId);
		return CollectionUtils.isEmpty(list) ? null : list.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public TerminalTemplate findUsed(Integer terminalId) {
		String queryString = "from TerminalTemplate as terminalTemplate where terminalTemplate.terminalId = ? and terminalTemplate.used = ?";
		List<TerminalTemplate> list = (List<TerminalTemplate>) hibernateTemplate.find(queryString, terminalId, true);
		return CollectionUtils.isEmpty(list) ? null : list.get(0);
	}

	@Override
	public List<TerminalTemplate> findList(Integer terminalId, Integer templateId, TemplateDownEnum status, Boolean renew, Boolean used) {
		StringBuffer queryString = new StringBuffer("from TerminalTemplate as terminalTemplate where 1 = 1");
		Map<String, Object> map = new HashMap<>();

		if (ValidateUtil.isPK(terminalId)) {
			queryString.append(" and terminalTemplate.terminalId = :terminalId");
			map.put("terminalId", terminalId);
		}
		if (ValidateUtil.isPK(templateId)) {
			queryString.append(" and terminalTemplate.templateId = :templateId");
			map.put("templateId", templateId);
		}
		if (status != null) {
			queryString.append(" and terminalTemplate.status = :status");
			map.put("status", status);
		}
		if (renew != null) {
			queryString.append(" and terminalTemplate.renew = :renew");
			map.put("renew", renew);
		}
		if (used != null) {
			queryString.append(" and terminalTemplate.used = :used");
			map.put("used", used);
		}

		return super.findList(queryString.toString(), map);
	}

}
