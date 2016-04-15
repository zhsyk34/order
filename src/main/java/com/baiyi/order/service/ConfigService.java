package com.baiyi.order.service;

import java.util.List;

import com.baiyi.order.model.Config;

public interface ConfigService {

	public void save(Config config);

	public void delete(Integer id);

	public void delete(Config config);

	public void delete(Integer[] ids);

	public void delete(List<Config> configs);

	public void update(Config config);

	public void merge(Config config);

	public Config find(Integer id);

	public Config find();// 默认配置

	public List<Config> findList();

	public int count();

}
