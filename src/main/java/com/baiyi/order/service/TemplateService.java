package com.baiyi.order.service;

import java.util.List;

import com.baiyi.order.model.Template;
import com.baiyi.order.util.EnumList.TemplateTypeEnum;
import com.baiyi.order.vo.TemplateVO;

public interface TemplateService {

	public void save(Template template);

	public void save(Template template, Integer[] foodIds, Integer logoId, Integer numberId, Integer[] videoIds, Integer[] pictureIds, Integer[] marqueeIds);

	public void delete(Integer id);

	public void delete(Template template);

	public void delete(Integer[] ids);

	public void delete(List<Template> templates);

	public void update(Template template);

	public void update(Template template, Integer[] foodIds, Integer logoId, Integer numberId, Integer[] videoIds, Integer[] pictureIds, Integer[] marqueeIds);

	public void merge(Template template);

	public void merge(Template template, Integer[] foodIds, Integer logoId, Integer numberId, Integer[] videoIds, Integer[] pictureIds, Integer[] marqueeIds);

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

	/**/
	public boolean exist(Integer id, String name);

	public boolean relate(Integer id);

	public boolean relate(Integer[] ids);
}
