package com.baiyi.order.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.baiyi.order.dao.ConfigDao;
import com.baiyi.order.model.Config;
import com.baiyi.order.service.ConfigService;

@Service
public class ConfigServiceImpl implements ConfigService {

	@Resource
	private ConfigDao configDao;

	@Override
	public void save(Config config) {
		configDao.save(config);
	}

	@Override
	public void delete(Integer id) {
		configDao.delete(id);
	}

	@Override
	public void delete(Config config) {
		configDao.delete(config);
	}

	@Override
	public void delete(Integer[] ids) {
		configDao.delete(ids);
	}

	@Override
	public void delete(List<Config> configs) {
		configDao.delete(configs);
	}

	@Override
	public void update(Config config) {
		configDao.update(config);
	}

	@Override
	public void merge(Config config) {
		configDao.merge(config);
	}

	@Override
	public Config find(Integer id) {
		return configDao.find(id);
	}

	@Override
	public Config find() {
		List<Config> list = configDao.findList();
		return CollectionUtils.isEmpty(list) ? null : list.get(0);
	}

	@Override
	public List<Config> findList() {
		return configDao.findList();
	}

	@Override
	public int count() {
		return configDao.count();
	}

}
