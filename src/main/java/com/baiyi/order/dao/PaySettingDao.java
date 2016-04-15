package com.baiyi.order.dao;

import java.util.List;

import com.baiyi.order.model.PaySetting;
import com.baiyi.order.util.EnumList.PaymentEnum;

public interface PaySettingDao {

	public void save(PaySetting paySetting);

	public void delete(Integer id);

	public void delete(PaySetting paySetting);

	public void delete(Integer[] ids);

	public void delete(List<PaySetting> paySettings);

	public void update(PaySetting paySetting);

	public void merge(PaySetting paySetting);

	public PaySetting find(Integer id);

	public PaySetting find(PaymentEnum name);

}
