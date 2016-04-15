package com.baiyi.order.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baiyi.order.dao.PaySettingDao;
import com.baiyi.order.model.PaySetting;
import com.baiyi.order.service.PaySettingService;
import com.baiyi.order.util.EnumList.PaymentEnum;

@Service
public class PaySettingServiceImpl implements PaySettingService {

	@Resource
	private PaySettingDao paySettingDao;

	@Override
	public void save(PaySetting paySetting) {
		paySettingDao.save(paySetting);
	}

	@Override
	public void delete(Integer id) {
		paySettingDao.delete(id);
	}

	@Override
	public void delete(PaySetting paySetting) {
		paySettingDao.delete(paySetting);
	}

	@Override
	public void delete(Integer[] ids) {
		paySettingDao.delete(ids);
	}

	@Override
	public void delete(List<PaySetting> paySettings) {
		paySettingDao.delete(paySettings);
	}

	@Override
	public void update(PaySetting paySetting) {
		paySettingDao.update(paySetting);
	}

	@Override
	public void merge(PaySetting paySetting) {
		paySettingDao.merge(paySetting);
	}

	@Override
	public PaySetting find(Integer id) {
		return paySettingDao.find(id);
	}

	@Override
	public PaySetting find(PaymentEnum name) {
		return paySettingDao.find(name);
	}

}
