package com.baiyi.order.action;

import java.util.Date;
import java.util.List;

import com.baiyi.order.model.Template;
import com.baiyi.order.util.EnumList.Effect;
import com.baiyi.order.util.EnumList.TemplateContentEnum;
import com.baiyi.order.util.EnumList.TemplateTypeEnum;
import com.baiyi.order.util.Feedback;
import com.baiyi.order.util.FormatUtil;
import com.baiyi.order.util.ValidateUtil;
import com.baiyi.order.vo.TemplateVO;

@SuppressWarnings("serial")
public class TemplateAction extends CommonsAction {

	public String save() {
		if (templateService.exist(null, name)) {
			jsonData.put("result", Feedback.EXIST.toString());
			return SUCCESS;
		}

		TemplateTypeEnum templateType = FormatUtil.getEnum(TemplateTypeEnum.class, type);
		TemplateContentEnum templateContent = FormatUtil.getEnum(TemplateContentEnum.class, content);
		if (templateType == null || templateContent == null) {
			jsonData.put("result", Feedback.ERROR.toString());
			return SUCCESS;
		}

		Template template = new Template();
		template.setName(name);
		template.setCreatetime(new Date());

		// type TODO E01暂未启用
		template.setType(templateType);
		switch (templateType) {
		case S01:
			rowcount = 0;
			colcount = 0;
			break;
		case S02:
			logo = null;
			break;
		case E01:
			break;
		}
		template.setRowcount(rowcount);
		template.setColcount(colcount);

		// content
		template.setContent(templateContent);
		Effect templateEffect = FormatUtil.getEnum(Effect.class, effect);
		switch (templateContent) {
		case NUMBER:
			if (!ValidateUtil.isPK(number)) {
				jsonData.put("result", Feedback.ERROR.toString());
				return SUCCESS;
			}
			pictures = null;
			interlude = 0;
			templateEffect = null;
			videos = null;
			break;
		case PICTURE:
			number = null;
			if (ValidateUtil.isEmpty(pictures) || interlude <= 0 || templateEffect == null) {
				jsonData.put("result", Feedback.ERROR.toString());
				return SUCCESS;
			}
			videos = null;
			break;
		case VIDEO:
			number = null;
			pictures = null;
			interlude = 0;
			templateEffect = null;
			if (ValidateUtil.isEmpty(videos)) {
				jsonData.put("result", Feedback.ERROR.toString());
				return SUCCESS;
			}
			break;
		}
		template.setInterlude(interlude);
		template.setEffect(templateEffect);

		templateService.save(template, foods, logo, number, videos, pictures, marquees);
		jsonData.put("result", Feedback.CREATE.toString());
		return SUCCESS;
	}

	public String delete() {
		if (templateService.relate(ids)) {
			jsonData.put("result", Feedback.RELATE.toString());
			return null;
		}
		templateService.delete(ids);
		jsonData.put("result", Feedback.DELETE.toString());
		return SUCCESS;
	}

	public String update() {
		if (templateService.exist(id, name)) {
			jsonData.put("result", Feedback.EXIST.toString());
			return null;
		}

		TemplateTypeEnum templateType = FormatUtil.getEnum(TemplateTypeEnum.class, type);
		if (templateType == null) {
			jsonData.put("result", "error");
			return null;
		}

		TemplateContentEnum templateContent = FormatUtil.getEnum(TemplateContentEnum.class, content);
		if (templateContent == null) {
			jsonData.put("result", "error");
			return null;
		}

		Template template = templateService.find(id);
		if (template == null) {
			return null;
		}

		template.setName(name);
		template.setUpdatetime(new Date());
		template.setUserId(userId);

		// type
		template.setType(templateType);
		switch (templateType) {
		case S01:
			rowcount = 0;
			colcount = 0;
			break;
		case S02:
			logo = null;
			break;
		case E01:
			break;
		}
		template.setRowcount(rowcount);
		template.setColcount(colcount);

		// content
		template.setContent(templateContent);
		switch (templateContent) {
		case NUMBER:
			videos = null;
			pictures = null;
			interlude = 0;
			effect = null;
			break;
		case PICTURE:
			number = null;
			videos = null;
			break;
		case VIDEO:
			number = null;
			pictures = null;
			interlude = 0;
			effect = null;
			break;
		}
		template.setInterlude(interlude);
		template.setEffect(FormatUtil.getEnum(Effect.class, effect));

		templateService.update(template, foods, logo, number, videos, pictures, marquees);
		jsonData.put("result", Feedback.UPDATE.toString());
		return SUCCESS;
	}

	public String find() {
		TemplateTypeEnum templateType = FormatUtil.getEnum(TemplateTypeEnum.class, type);

		List<TemplateVO> list = templateService.findVOList(name, templateType, userId, sort, order, pageNo, pageSize);
		int count = templateService.count(name, templateType, userId);
		jsonData.put("list", list);
		jsonData.put("count", count);
		jsonData.put("pageNo", pageNo);
		jsonData.put("pageSize", pageSize);
		return SUCCESS;
	}

	public String exist() {
		jsonData.put("exist", templateService.exist(id, name));
		return SUCCESS;
	}

	/* ===================== */
	private Integer id;

	private Integer[] ids;

	private String name;

	private Integer[] foods;// 餐点列表

	private Integer logo;

	/* 类型及相关设置 */
	private String type;
	private int rowcount;
	private int colcount;

	/* 内容及相关(当前只有图片)属性 */
	private String content;

	private Integer number;// 1.取号图片(横幅)

	private Integer[] videos;// 2.视频

	private Integer[] pictures;// 3.图片
	private int interlude;
	private String effect;

	private Integer[] marquees;// 跑马灯

	/*------- getter and setter--------*/
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer[] getIds() {
		return ids;
	}

	public void setIds(Integer[] ids) {
		this.ids = ids;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer[] getFoods() {
		return foods;
	}

	public void setFoods(Integer[] foods) {
		this.foods = foods;
	}

	public Integer getLogo() {
		return logo;
	}

	public void setLogo(Integer logo) {
		this.logo = logo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getRowcount() {
		return rowcount;
	}

	public void setRowcount(int rowcount) {
		this.rowcount = rowcount;
	}

	public int getColcount() {
		return colcount;
	}

	public void setColcount(int colcount) {
		this.colcount = colcount;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer[] getVideos() {
		return videos;
	}

	public void setVideos(Integer[] videos) {
		this.videos = videos;
	}

	public Integer[] getPictures() {
		return pictures;
	}

	public void setPictures(Integer[] pictures) {
		this.pictures = pictures;
	}

	public int getInterlude() {
		return interlude;
	}

	public void setInterlude(int interlude) {
		this.interlude = interlude;
	}

	public String getEffect() {
		return effect;
	}

	public void setEffect(String effect) {
		this.effect = effect;
	}

	public Integer[] getMarquees() {
		return marquees;
	}

	public void setMarquees(Integer[] marquees) {
		this.marquees = marquees;
	}

}
