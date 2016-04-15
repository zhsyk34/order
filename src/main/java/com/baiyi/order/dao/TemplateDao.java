package com.baiyi.order.dao;

import java.util.List;

import com.baiyi.order.model.Food;
import com.baiyi.order.model.Marquee;
import com.baiyi.order.model.Material;
import com.baiyi.order.model.Template;
import com.baiyi.order.util.EnumList.TemplateMaterialEnum;
import com.baiyi.order.util.EnumList.TemplateTypeEnum;
import com.baiyi.order.vo.TemplateVO;

public interface TemplateDao {

	public void save(Template template);

	public void delete(Integer id);

	public void delete(Template template);

	public void delete(Integer[] ids);

	public void delete(List<Template> templates);

	public void update(Template template);

	public void merge(Template template);

	public Template find(Integer id);

	public Template find(String name);

	public List<Template> findList();

	public List<Template> findList(String name, TemplateTypeEnum type, Integer userId);

	public List<Template> findList(String name, TemplateTypeEnum type, Integer userId, String sort, String order, int pageNo, int pageSize);

	public int count(String name, TemplateTypeEnum type, Integer userId);

	/* VO */
	public TemplateVO findVO(Integer id);

	public List<TemplateVO> findVOList();

	public List<TemplateVO> findVOList(String name, TemplateTypeEnum type, Integer userId);

	public List<TemplateVO> findVOList(String name, TemplateTypeEnum type, Integer userId, String sort, String order, int pageNo, int pageSize);

	/* join search */
	public List<Food> findFoodList(Integer id);

	public List<Material> findMaterialList(Integer id, TemplateMaterialEnum type);

	public List<Marquee> findMarqueeList(Integer id);

}
