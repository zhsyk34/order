package com.baiyi.order.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.baiyi.order.dao.ActivityDao;
import com.baiyi.order.model.Activity;
import com.baiyi.order.util.EnumList.ActivityTypeEnum;
import com.baiyi.order.util.EnumList.TerminalTypeEnum;
import com.baiyi.order.util.ValidateUtil;
import com.baiyi.order.vo.ActivityVO;

@Repository
public class ActivityDaoImpl extends CommonsDaoImpl<Activity> implements ActivityDao {

	@Override
	public void delete(List<Activity> activitys) {
		super.delete(activitys);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Activity find(Integer kitchenId, Integer foodId) {
		String queryString = "from Activity as activity where activity.kitchenId = ? and activity.foodId = ?";
		List<Activity> list = (List<Activity>) hibernateTemplate.find(queryString, kitchenId, foodId);
		return CollectionUtils.isEmpty(list) ? null : list.get(0);
	}

	@Override
	public List<Activity> findList(Integer kitchenId, Integer foodId, ActivityTypeEnum type, Boolean used) {
		return this.findList(kitchenId, foodId, type, used, null, null, -1, -1);
	}

	@Override
	public List<Activity> findList(Integer kitchenId, Integer foodId, ActivityTypeEnum type, Boolean used, String sort, String order, int pageNo, int pageSize) {
		StringBuffer queryString = new StringBuffer("from Activity as activity where 1= 1");
		Map<String, Object> map = new HashMap<>();

		if (ValidateUtil.isPK(kitchenId)) {
			queryString.append(" and activity.kitchenId = :kitchenId");
			map.put("kitchenId", kitchenId);
		}
		if (ValidateUtil.isPK(foodId)) {
			queryString.append(" and activity.foodId = :foodId");
			map.put("foodId", foodId);
		}

		if (type != null) {
			queryString.append(" and activity.type = :type");
			map.put("type", type);
		}
		if (used != null) {
			queryString.append(" and activity.used = :used");
			map.put("used", used);
		}
		if (StringUtils.isNotBlank(sort) && StringUtils.isNotBlank(order)) {
			queryString.append(" order by activity." + sort + " " + order);
		}
		return super.findList(queryString.toString(), (pageNo - 1) * pageSize, pageSize, map);
	}

	@Override
	public List<Activity> findList(String kitchen, String food, ActivityTypeEnum type, Boolean used) {
		return this.findList(kitchen, food, type, used, null, null, -1, -1);
	}

	@Override
	public List<Activity> findList(String kitchen, String food, ActivityTypeEnum type, Boolean used, String sort, String order, int pageNo, int pageSize) {
		StringBuffer queryString = new StringBuffer("select activity from Activity as activity, Terminal as terminal, Food as food");
		queryString.append(" where activity.kitchenId = terminal.id and activity.foodId = food.id");

		Map<String, Object> map = new HashMap<>();

		if (StringUtils.isNotBlank(kitchen)) {
			queryString.append(" and terminal.terminalNo like :kitchen");
			map.put("kitchen", "%" + kitchen + "%");
		}
		if (StringUtils.isNotBlank(food)) {
			queryString.append(" and food.name like :food");
			map.put("food", "%" + food + "%");
		}
		if (type != null) {
			queryString.append(" and activity.type = :type");
			map.put("type", type);
		}
		if (used != null) {
			queryString.append(" and activity.used = :used");
			map.put("used", used);
		}
		if (StringUtils.isNotBlank(sort) && StringUtils.isNotBlank(order)) {
			queryString.append(" order by activity." + sort + " " + order);
		}

		return super.findList(queryString.toString(), (pageNo - 1) * pageSize, pageSize, map);
	}

	@Override
	public int count(String kitchen, String food, ActivityTypeEnum type, Boolean used) {
		StringBuffer queryString = new StringBuffer("select count(*) from Activity as activity, Terminal as terminal, Food as food");
		queryString.append(" where activity.kitchenId = terminal.id and activity.foodId = food.id");

		Map<String, Object> map = new HashMap<>();

		if (StringUtils.isNotBlank(kitchen)) {
			queryString.append(" and terminal.terminalNo like :kitchen");
			map.put("kitchen", "%" + kitchen + "%");
		}
		if (StringUtils.isNotBlank(food)) {
			queryString.append(" and food.name like :food");
			map.put("food", "%" + food + "%");
		}
		if (type != null) {
			queryString.append(" and activity.type = :type");
			map.put("type", type);
		}
		if (used != null) {
			queryString.append(" and activity.used = :used");
			map.put("used", used);
		}
		return super.count(queryString.toString(), map);
	}

	@Override
	public List<ActivityVO> findVOList() {
		return this.findVOList(null, null, null, null);
	}

	@Override
	public List<ActivityVO> findVOList(String kitchen, String food, ActivityTypeEnum type, Boolean used) {
		return this.findVOList(kitchen, food, type, used, null, null, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ActivityVO> findVOList(String kitchen, String food, ActivityTypeEnum type, Boolean used, String sort, String order, int pageNo, int pageSize) {
		StringBuffer queryString = new StringBuffer("select activity.*,");
		queryString.append(" terminal.terminalNo as kitchenNo, terminal.location,");
		queryString.append(" food.name as foodName, food.price");
		queryString.append(" from Activity as activity");
		queryString.append(" left join Terminal as terminal on activity.kitchenId = terminal.id");
		queryString.append(" left join Food as food on activity.foodId = food.id");
		queryString.append(" where 1 = 1");

		Map<String, Object> map = new HashMap<>();

		if (StringUtils.isNotBlank(kitchen)) {
			queryString.append(" and terminal.terminalNo like :kitchen");
			map.put("kitchen", "%" + kitchen + "%");
		}
		if (StringUtils.isNotBlank(food)) {
			queryString.append(" and food.name like :food");
			map.put("food", "%" + food + "%");
		}
		if (type != null) {
			queryString.append(" and activity.type = :type");
			map.put("type", type.name());
		}
		if (used != null) {
			queryString.append(" and activity.used = :used");
			map.put("used", used);
		}
		if (StringUtils.isNotBlank(sort) && StringUtils.isNotBlank(order)) {
			queryString.append(" order by activity." + sort + " " + order);
		}

		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query query = session.createSQLQuery(queryString.toString());
		query.setProperties(map);
		if (pageNo >= 0 && pageSize > 0) {
			query.setFirstResult((pageNo - 1) * pageSize).setMaxResults(pageSize);
		}
		query.setResultTransformer(Transformers.aliasToBean(ActivityVO.class));
		return query.list();
	}

	@Override
	public List<ActivityVO> findVOList(boolean extra) {
		return this.findVOList(null, null, null, null, extra);
	}

	@Override
	public List<ActivityVO> findVOList(String kitchen, String food, ActivityTypeEnum type, Boolean used, boolean extra) {
		return this.findVOList(kitchen, food, type, used, extra, null, null, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ActivityVO> findVOList(String kitchen, String food, ActivityTypeEnum type, Boolean used, boolean extra, String sort, String order, int pageNo, int pageSize) {
		if (!extra) {
			return this.findVOList(kitchen, food, type, used, sort, order, pageNo, pageSize);
		}

		StringBuffer queryString = new StringBuffer("select activity.id, activity.type,");
		queryString.append(" activity.unit, activity.discount, activity.percent, activity.begin, activity.end,");
		queryString.append(" activity.count, activity.send, activity.used, activity.userId, activity.createtime,");
		queryString.append(" temp.fid as foodId, temp.tid as kitchenId,");//
		queryString.append(" temp.terminalNo as kitchenNo, temp.location, temp.name as foodName, temp.price");
		queryString.append(" from Activity as activity right join");
		queryString.append(" (select terminal.id as tid, terminal.location, terminal.terminalNo, food.id as fid, food.name, food.price");
		queryString.append(" from Terminal as terminal cross join Food as food where terminal.type = :terminalType) as temp");
		queryString.append(" on activity.kitchenId = temp.tid and activity.foodId = temp.fid");
		queryString.append(" where 1 = 1");

		Map<String, Object> map = new HashMap<>();
		map.put("terminalType", TerminalTypeEnum.KITCHEN.name());

		if (StringUtils.isNotBlank(kitchen)) {
			queryString.append(" and temp.terminalNo like :kitchen");
			map.put("kitchen", "%" + kitchen + "%");
		}
		if (StringUtils.isNotBlank(food)) {
			queryString.append(" and temp.name like :food");
			map.put("food", "%" + food + "%");
		}
		if (type != null) {
			queryString.append(" and (activity.type = :type or activity.id is null)");
			map.put("type", type.name());
		}
		if (used != null) {
			queryString.append(" and (activity.used = :used" + (used ? "" : " or activity.id is null") + ")");
			map.put("used", used);
		}
		if (StringUtils.isNotBlank(sort) && StringUtils.isNotBlank(order)) {
			// used by temp because activity maybe null
			queryString.append(" order by temp." + sort + " " + order);
		}

		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query query = session.createSQLQuery(queryString.toString());
		// .addScalar("count", StringType.INSTANCE);

		query.setProperties(map);
		if (pageNo >= 0 && pageSize > 0) {
			query.setFirstResult((pageNo - 1) * pageSize).setMaxResults(pageSize);
		}
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		// query.setResultTransformer(Transformers.aliasToBean(ActivityVO.class));
		return query.list();
	}

}
