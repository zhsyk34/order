package com.baiyi.order.dao;

import java.util.List;

import com.baiyi.order.model.Config;

public interface ConfigDao {

	public void save(Config config);

	public void delete(Integer id);

	public void delete(Config config);

	public void delete(Integer[] ids);

	public void delete(List<Config> configs);

	public void update(Config config);

	public void merge(Config config);

	public Config find(Integer id);

	public List<Config> findList();

	public int count();
}
