package com.baiyi.order.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import com.baiyi.order.dao.TerminalConnectDao;
import com.baiyi.order.model.TerminalConnect;
import com.baiyi.order.util.ValidateUtil;
import com.baiyi.order.vo.Record;

@Repository
public class TerminalConnectDaoImpl extends CommonsDaoImpl<TerminalConnect> implements TerminalConnectDao {

	@Override
	public void delete(List<TerminalConnect> terminalConnects) {
		super.delete(terminalConnects);
	}

	@SuppressWarnings("unchecked")
	@Override
	public TerminalConnect findLast(Integer terminalId) {
		StringBuffer queryString = new StringBuffer("from TerminalConnect as terminalConnect where terminalConnect.id = ");
		queryString.append(" (select max(id) from terminalConnect where terminalConnect.terminalId = ?)");
		List<TerminalConnect> list = (List<TerminalConnect>) hibernateTemplate.find(queryString.toString(), terminalId);
		return CollectionUtils.isEmpty(list) ? null : list.get(0);
	}

	@Override
	public List<TerminalConnect> findList(Integer terminalId, Date begin, Date end, Boolean online) {
		return this.findList(terminalId, begin, end, online, null, null, -1, -1);
	}

	@Override
	public List<TerminalConnect> findList(Integer terminalId, Date begin, Date end, Boolean online, String sort, String order, int pageNo, int pageSize) {
		StringBuffer queryString = new StringBuffer("from TerminalConnect as terminalConnect where 1 = 1");
		Map<String, Object> map = new HashMap<>();

		if (ValidateUtil.isPK(terminalId)) {
			queryString.append(" and terminalConnect.terminalId = :terminalId");
			map.put("terminalId", terminalId);
		}
		if (begin != null) {
			queryString.append(" and terminalConnect.date >= :begin");
			map.put("begin", begin);
		}
		if (end != null) {
			queryString.append(" and terminalConnect.date <= :end");
			map.put("end", end);
		}
		if (online != null) {
			queryString.append(" and terminalConnect.online = :online");
			map.put("online", online);
		}

		if (StringUtils.isNotBlank(sort) && StringUtils.isNotBlank(order)) {
			queryString.append(" order by terminalConnect." + sort + " " + order);
		}

		return super.findList(queryString.toString(), (pageNo - 1) * pageSize, pageSize, map);
	}

	@Override
	public int count(Integer terminalId, Date begin, Date end, Boolean online) {
		StringBuffer queryString = new StringBuffer("select count(*) from TerminalConnect as terminalConnect where 1 = 1");
		Map<String, Object> map = new HashMap<>();

		if (ValidateUtil.isPK(terminalId)) {
			queryString.append(" and terminalConnect.terminalId = :terminalId");
			map.put("terminalId", terminalId);
		}
		if (begin != null) {
			queryString.append(" and terminalConnect.date >= :begin");
			map.put("begin", begin);
		}
		if (end != null) {
			queryString.append(" and terminalConnect.date <= :end");
			map.put("end", end);
		}
		if (online != null) {
			queryString.append(" and terminalConnect.online = :online");
			map.put("online", online);
		}

		return super.count(queryString.toString(), map);
	}

	@Override
	public List<Record> findVOList(Integer terminalId, Date begin, Date end, Boolean online) {
		return this.findVOList(terminalId, begin, end, online, null, null, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Record> findVOList(Integer terminalId, Date begin, Date end, Boolean online, String sort, String order, int pageNo, int pageSize) {
		StringBuffer queryString = new StringBuffer("select terminalConnect.*, terminal.terminalNo, terminal.location");
		queryString.append(" from TerminalConnect as terminalConnect left join Terminal as terminal");
		queryString.append(" on terminalConnect.terminalId = terminal.id");
		queryString.append(" where 1 = 1");

		Map<String, Object> map = new HashMap<>();
		if (ValidateUtil.isPK(terminalId)) {
			queryString.append(" and terminalConnect.terminalId = :terminalId");
			map.put("terminalId", terminalId);
		}
		if (begin != null) {
			queryString.append(" and terminalConnect.date >= :begin");
			map.put("begin", begin);
		}
		if (end != null) {
			queryString.append(" and terminalConnect.date <= :end");
			map.put("end", end);
		}
		if (online != null) {
			queryString.append(" and terminalConnect.online = :online");
			map.put("online", online);
		}

		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query query = session.createSQLQuery(queryString.toString());
		query.setResultTransformer(Transformers.aliasToBean(Record.class));
		query.setProperties(map);

		if (pageNo >= 0 && pageSize > 0) {
			query.setFirstResult((pageNo - 1) * pageSize).setMaxResults(pageSize);
		}

		return query.list();
	}

	@Override
	public List<Record> findVOList(String terminalNo, Date begin, Date end, Boolean online) {
		return this.findVOList(terminalNo, begin, end, online, null, null, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Record> findVOList(String terminalNo, Date begin, Date end, Boolean online, String sort, String order, int pageNo, int pageSize) {
		StringBuffer queryString = new StringBuffer("select terminalConnect.*, terminal.terminalNo, terminal.location");
		queryString.append(" from TerminalConnect as terminalConnect left join Terminal as terminal");
		queryString.append(" on terminalConnect.terminalId = terminal.id");
		queryString.append(" where 1 = 1");

		Map<String, Object> map = new HashMap<>();
		if (StringUtils.isNotBlank(terminalNo)) {
			queryString.append(" and terminal.terminalNo like :terminalNo");
			map.put("terminalNo", "%" + terminalNo + "%");
		}
		if (begin != null) {
			queryString.append(" and terminalConnect.date >= :begin");
			map.put("begin", begin);
		}
		if (end != null) {
			queryString.append(" and terminalConnect.date <= :end");
			map.put("end", end);
		}
		if (online != null) {
			queryString.append(" and terminalConnect.online = :online");
			map.put("online", online);
		}

		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query query = session.createSQLQuery(queryString.toString());
		query.setResultTransformer(Transformers.aliasToBean(Record.class));
		query.setProperties(map);

		if (pageNo >= 0 && pageSize > 0) {
			query.setFirstResult((pageNo - 1) * pageSize).setMaxResults(pageSize);
		}

		return query.list();
	}

	@Override
	public int count(String terminalNo, Date begin, Date end, Boolean online) {
		StringBuffer queryString = new StringBuffer("select count(*)");
		queryString.append(" from TerminalConnect as terminalConnect left join Terminal as terminal");
		queryString.append(" on terminalConnect.terminalId = terminal.id");
		queryString.append(" where 1 = 1");

		Map<String, Object> map = new HashMap<>();
		if (StringUtils.isNotBlank(terminalNo)) {
			queryString.append(" and terminal.terminalNo like :terminalNo");
			map.put("terminalNo", "%" + terminalNo + "%");
		}
		if (begin != null) {
			queryString.append(" and terminalConnect.date >= :begin");
			map.put("begin", begin);
		}
		if (end != null) {
			queryString.append(" and terminalConnect.date <= :end");
			map.put("end", end);
		}
		if (online != null) {
			queryString.append(" and terminalConnect.online = :online");
			map.put("online", online);
		}

		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query query = session.createSQLQuery(queryString.toString()).addScalar("count(*)", StandardBasicTypes.INTEGER);
		query.setProperties(map);

		return (Integer) query.uniqueResult();
	}

}
