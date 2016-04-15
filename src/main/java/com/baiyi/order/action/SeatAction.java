package com.baiyi.order.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.baiyi.order.model.Seat;
import com.baiyi.order.util.Feedback;
import com.baiyi.order.util.ValidateUtil;

@SuppressWarnings("serial")
public class SeatAction extends CommonsAction {

	public String save() {
		if (batch) {
			List<Seat> seats = new ArrayList<>();
			int min = Integer.parseInt(begin);
			int max = Integer.parseInt(end);

			for (int i = min; i <= max; i++) {
				Seat seat = new Seat();

				if (seatService.exist(null, name + i)) {
					jsonData.put("result", Feedback.EXIST.toString());
					return SUCCESS;
				}
				seat.setName(name + i);
				seat.setCreatetime(new Date());
				seat.setUserId(loginId);
				seats.add(seat);
			}
			seatService.save(seats);
		} else {
			if (seatService.exist(null, name)) {
				jsonData.put("result", Feedback.EXIST.toString());
				return SUCCESS;
			}
			Seat seat = new Seat();
			seat.setName(name);
			seat.setCreatetime(new Date());
			seat.setUserId(loginId);
			seatService.save(seat);
		}
		jsonData.put("result", Feedback.CREATE.toString());
		return SUCCESS;
	}

	public String delete() {
		if (seatService.relate(ids)) {
			jsonData.put("result", Feedback.RELATE.toString());
			return null;
		}
		seatService.delete(ids);
		jsonData.put("result", Feedback.DELETE.toString());
		return SUCCESS;
	}

	public String update() {
		if (seatService.exist(id, name)) {
			jsonData.put("result", Feedback.EXIST.toString());
			return SUCCESS;
		}
		Seat seat = seatService.find(id);
		seat.setName(name);
		seat.setUpdatetime(new Date());
		seat.setUserId(loginId);
		seatService.update(seat);
		jsonData.put("result", Feedback.UPDATE.toString());
		return SUCCESS;
	}

	public String exist() {
		boolean exist = false;

		if (!ValidateUtil.isPK(id) && batch) {
			int min = Integer.parseInt(begin);
			int max = Integer.parseInt(end);
			for (int i = min; i <= max; i++) {
				exist = seatService.exist(null, name + i);
				if (exist) {
					break;
				}
			}
		} else {
			exist = seatService.exist(id, name);
		}
		jsonData.put("exist", exist);
		return SUCCESS;
	}

	public String find() {
		List<Seat> list = seatService.findList(name, userId, sort, order, pageNo, pageSize);
		int count = seatService.count(name, userId);
		jsonData.put("list", list);
		jsonData.put("count", count);
		jsonData.put("pageNo", pageNo);
		jsonData.put("pageSize", pageSize);
		return SUCCESS;
	}

	private Integer id;

	private Integer[] ids;

	private String name;

	private boolean batch;// 批量

	private String begin;

	private String end;

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

	public boolean isBatch() {
		return batch;
	}

	public void setBatch(boolean batch) {
		this.batch = batch;
	}

	public String getBegin() {
		return begin;
	}

	public void setBegin(String begin) {
		this.begin = begin;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

}
