package com.baiyi.order.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.baiyi.order.dao.ConfigDao;
import com.baiyi.order.model.Config;

@Repository
public class ConfigDaoImpl extends CommonsDaoImpl<Config> implements ConfigDao {

	@Override
	public void delete(List<Config> configs) {
		super.delete(configs);
	}

	@Override
	public int count() {
		return super.count("select count(*) from Config", null);
	}

}
