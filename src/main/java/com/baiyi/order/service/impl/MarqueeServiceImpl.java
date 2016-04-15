package com.baiyi.order.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.baiyi.order.dao.MarqueeDao;
import com.baiyi.order.dao.TemplateMarqueeDao;
import com.baiyi.order.model.Marquee;
import com.baiyi.order.model.TemplateMarquee;
import com.baiyi.order.service.MarqueeService;

@Service
public class MarqueeServiceImpl implements MarqueeService {

	@Resource
	private MarqueeDao marqueeDao;
	@Resource
	private TemplateMarqueeDao templateMarqueeDao;

	@Override
	public void save(Marquee marquee) {
		marqueeDao.save(marquee);
	}

	@Override
	public void delete(Integer id) {
		marqueeDao.delete(id);
	}

	@Override
	public void delete(Marquee marquee) {
		marqueeDao.delete(marquee);
	}

	@Override
	public void delete(Integer[] ids) {
		marqueeDao.delete(ids);
	}

	@Override
	public void delete(List<Marquee> marquees) {
		marqueeDao.delete(marquees);
	}

	@Override
	public void update(Marquee marquee) {
		marqueeDao.update(marquee);
	}

	@Override
	public void merge(Marquee marquee) {
		marqueeDao.merge(marquee);
	}

	@Override
	public Marquee find(Integer id) {
		return marqueeDao.find(id);
	}

	@Override
	public Marquee find(String title) {
		return marqueeDao.find(title);
	}

	@Override
	public List<Marquee> findList() {
		return marqueeDao.findList();
	}

	@Override
	public List<Marquee> findList(String title, String content, Integer userId) {
		return marqueeDao.findList(title, content, userId);
	}

	@Override
	public List<Marquee> findList(String title, String content, Integer userId, String sort, String order, int pageNo, int pageSize) {
		return marqueeDao.findList(title, content, userId, sort, order, pageNo, pageSize);
	}

	@Override
	public int count(String title, String content, Integer userId) {
		return marqueeDao.count(title, content, userId);
	}

	@Override
	public boolean exist(Integer id, String title) {
		Marquee marquee = this.find(title);
		return marquee == null ? false : !marquee.getId().equals(id);
	}

	@Override
	public boolean relate(Integer id) {
		List<TemplateMarquee> list = templateMarqueeDao.findList(null, id);
		return CollectionUtils.isNotEmpty(list);
	}

	@Override
	public boolean relate(Integer[] ids) {
		for (Integer id : ids) {
			if (this.relate(id)) {
				return true;
			}
		}
		return false;
	}
}
