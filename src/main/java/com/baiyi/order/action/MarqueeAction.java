package com.baiyi.order.action;

import java.util.Date;
import java.util.List;

import com.baiyi.order.model.Marquee;
import com.baiyi.order.util.EnumList.MarqueeDirectionEnum;
import com.baiyi.order.util.Feedback;
import com.baiyi.order.util.FormatUtil;

@SuppressWarnings("serial")
public class MarqueeAction extends CommonsAction {

	public String save() {
		if (marqueeService.exist(null, title)) {
			jsonData.put("result", Feedback.EXIST.toString());
			return null;
		}

		Marquee marquee = new Marquee();
		marquee.setTitle(title);
		marquee.setContent(content);
		marquee.setDirection(FormatUtil.getEnum(MarqueeDirectionEnum.class, direction));
		marquee.setSpeed(speed);

		marquee.setFont(font);
		marquee.setSize(size);
		marquee.setColor(color);
		marquee.setBackground(background);

		marquee.setCreatetime(new Date());
		marquee.setUserId(userId);
		marqueeService.save(marquee);
		jsonData.put("result", Feedback.CREATE.toString());
		return SUCCESS;
	}

	public String delete() {
		if (marqueeService.relate(ids)) {
			jsonData.put("result", Feedback.RELATE.toString());
			return null;
		}
		marqueeService.delete(ids);
		jsonData.put("result", Feedback.DELETE.toString());
		return SUCCESS;
	}

	public String update() {
		if (marqueeService.exist(id, title)) {
			jsonData.put("result", Feedback.EXIST.toString());
			return null;
		}
		Marquee marquee = marqueeService.find(id);
		if (marquee == null) {
			jsonData.put("result", Feedback.NOTEXIST.toString());
			return null;
		}

		marquee.setTitle(title);
		marquee.setContent(content);
		marquee.setDirection(FormatUtil.getEnum(MarqueeDirectionEnum.class, direction));
		marquee.setSpeed(speed);

		marquee.setFont(font);
		marquee.setSize(size);
		marquee.setColor(color);
		marquee.setBackground(background);

		marquee.setUpdatetime(new Date());
		marquee.setUserId(userId);
		marqueeService.update(marquee);
		jsonData.put("result", Feedback.UPDATE.toString());
		return SUCCESS;
	}

	public String find() {
		List<Marquee> list = marqueeService.findList(title, content, userId, sort, order, pageNo, pageSize);
		int count = marqueeService.count(title, content, userId);
		jsonData.put("list", list);
		jsonData.put("count", count);
		jsonData.put("pageNo", pageNo);
		jsonData.put("pageSize", pageSize);
		return SUCCESS;
	}

	public String exist() {
		jsonData.put("exist", marqueeService.exist(id, title));
		return SUCCESS;
	}

	private Integer id;
	private Integer[] ids;

	private String title;
	private String content;
	private String direction;
	private int speed;

	private String font;
	private String size;
	private String color;
	private String background;

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public String getFont() {
		return font;
	}

	public void setFont(String font) {
		this.font = font;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getBackground() {
		return background;
	}

	public void setBackground(String background) {
		this.background = background;
	}

}
