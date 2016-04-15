package com.baiyi.order.dao.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;

import com.baiyi.order.dao.PaySettingDao;
import com.baiyi.order.model.PaySetting;
import com.baiyi.order.util.EnumList.PaymentEnum;

@Repository
public class PaySettingDaoImpl extends CommonsDaoImpl<PaySetting> implements PaySettingDao {

	@Override
	public void delete(List<PaySetting> paySettings) {
		super.delete(paySettings);
	}

	@SuppressWarnings("unchecked")
	@Override
	public PaySetting find(PaymentEnum name) {
		String queryString = "from PaySetting as paySetting where paySetting.name = ?";
		List<PaySetting> list = (List<PaySetting>) hibernateTemplate.find(queryString, name);
		return CollectionUtils.isEmpty(list) ? null : list.get(0);
	}

}
