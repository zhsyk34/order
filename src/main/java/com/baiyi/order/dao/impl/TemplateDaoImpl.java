package com.baiyi.order.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.baiyi.order.dao.TemplateDao;
import com.baiyi.order.model.Food;
import com.baiyi.order.model.Marquee;
import com.baiyi.order.model.Material;
import com.baiyi.order.model.Template;
import com.baiyi.order.util.EnumList.TemplateMaterialEnum;
import com.baiyi.order.util.EnumList.TemplateTypeEnum;
import com.baiyi.order.util.ValidateUtil;
import com.baiyi.order.vo.FoodVO;
import com.baiyi.order.vo.TemplateVO;

@Repository
public class TemplateDaoImpl extends CommonsDaoImpl<Template> implements TemplateDao {

	@Override
	public void delete(List<Template> templates) {
		super.delete(templates);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Template find(String name) {
		List<Template> list = (List<Template>) hibernateTemplate.find("from Template as template where template.name = ?", name);
		return CollectionUtils.isEmpty(list) ? null : list.get(0);
	}

	@Override
	public List<Template> findList(String name, TemplateTypeEnum type, Integer userId) {
		return this.findList(name, type, userId, null, null, -1, -1);
	}

	@Override
	public List<Template> findList(String name, TemplateTypeEnum type, Integer userId, String sort, String order, int pageNo, int pageSize) {
		StringBuffer queryString = new StringBuffer("from Template as template where 1 = 1");
		Map<String, Object> map = new HashMap<>();

		if (StringUtils.isNotBlank(name)) {
			queryString.append(" and template.name like :name");
			map.put("name", "%" + name + "%");
		}
		if (type != null) {
			queryString.append(" and template.type = :type");
			map.put("type", type);
		}
		if (ValidateUtil.isPK(userId)) {
			queryString.append(" and template.userId = :userId");
			map.put("userId", userId);
		}
		if (StringUtils.isNotBlank(sort) && StringUtils.isNotBlank(order)) {
			queryString.append(" order by template." + sort + " " + order);
		}
		return super.findList(queryString.toString(), (pageNo - 1) * pageSize, pageSize, map);
	}

	@Override
	public int count(String name, TemplateTypeEnum type, Integer userId) {
		StringBuffer queryString = new StringBuffer("select count(*) from Template as template where 1 = 1");
		Map<String, Object> map = new HashMap<>();

		if (StringUtils.isNotBlank(name)) {
			queryString.append(" and template.name like :name");
			map.put("name", "%" + name + "%");
		}
		if (type != null) {
			queryString.append(" and template.type = :type");
			map.put("type", type);
		}
		return super.count(queryString.toString(), map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public TemplateVO findVO(Integer id) {
		Template template = this.find(id);
		if (template == null) {
			return null;
		}

		TemplateVO templateVO = new TemplateVO();
		BeanUtilsBean.getInstance().getConvertUtils().register(false, true, 0);
		try {
			BeanUtils.copyProperties(templateVO, template);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}

		// foodVO
		StringBuffer queryString = new StringBuffer("select food.*, type.name as typeName, material.path");
		queryString.append(" from Food as food left join Type as type on food.typeId = type.id");
		queryString.append(" left join Material as material on food.materialId = material.id");
		queryString.append(" where food.id in");
		queryString.append(" (select templateFood.foodId from TemplateFood where templateFood.templateId = :id)");

		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query query = session.createSQLQuery(queryString.toString());
		query.setResultTransformer(Transformers.aliasToBean(FoodVO.class));
		query.setParameter("id", id);

		List<FoodVO> foodVOList = (List<FoodVO>) query.list();
		templateVO.setFoodList(foodVOList);

		// TODO
		switch (template.getType()) {
		case S01:
			break;
		case S02:
			break;
		case E01:
			break;
		}
		switch (template.getContent()) {
		case NUMBER:
			break;
		case VIDEO:
			break;
		case PICTURE:
			break;
		}

		List<Material> logo = this.findMaterialList(id, TemplateMaterialEnum.LOGO);
		if (CollectionUtils.isNotEmpty(logo)) {
			templateVO.setLogo(logo.get(0));
		}

		List<Material> number = this.findMaterialList(id, TemplateMaterialEnum.NUMBER);
		if (CollectionUtils.isNotEmpty(number)) {
			templateVO.setNumber(number.get(0));
		}

		List<Material> videoList = this.findMaterialList(id, TemplateMaterialEnum.VIDEO);
		templateVO.setVideoList(videoList);

		List<Material> pictureList = this.findMaterialList(id, TemplateMaterialEnum.PICTURE);
		templateVO.setPictureList(pictureList);

		List<Marquee> marqueeList = this.findMarqueeList(id);
		templateVO.setMarqueeList(marqueeList);

		return templateVO;
	}

	@Override
	public List<TemplateVO> findVOList() {
		return this.findVOList(null, null, null);
	}

	@Override
	public List<TemplateVO> findVOList(String name, TemplateTypeEnum type, Integer userId) {
		return this.findVOList(name, type, userId, null, null, -1, -1);
	}

	@Override
	public List<TemplateVO> findVOList(String name, TemplateTypeEnum type, Integer userId, String sort, String order, int pageNo, int pageSize) {
		List<Template> templateList = this.findList(name, type, userId, sort, order, pageNo, pageSize);
		if (CollectionUtils.isEmpty(templateList)) {
			return null;
		}
		List<TemplateVO> templateVOList = new ArrayList<>();
		for (Template template : templateList) {
			TemplateVO templateVO = this.findVO(template.getId());
			templateVOList.add(templateVO);
		}
		return templateVOList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Food> findFoodList(Integer id) {
		StringBuffer queryString = new StringBuffer("select food from Food as food, TemplateFood as templateFood");
		queryString.append(" where food.id = templateFood.foodId and templateFood.templateId = ?");
		return (List<Food>) hibernateTemplate.find(queryString.toString(), id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Material> findMaterialList(Integer id, TemplateMaterialEnum type) {
		StringBuffer queryString = new StringBuffer("select material from Material as material, TemplateMaterial as templateMaterial");
		queryString.append(" where material.id = templateMaterial.materialId and templateMaterial.templateId = ? and templateMaterial.type = ?");
		return (List<Material>) hibernateTemplate.find(queryString.toString(), id, type);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Marquee> findMarqueeList(Integer id) {
		StringBuffer queryString = new StringBuffer("select marquee from Marquee as marquee, TemplateMarquee as templateMarquee");
		queryString.append(" where marquee.id = templateMarquee.marqueeId and templateMarquee.templateId = ?");
		return (List<Marquee>) hibernateTemplate.find(queryString.toString(), id);
	}
}
