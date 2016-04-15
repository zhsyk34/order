package com.test;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.baiyi.order.dao.FoodDao;
import com.baiyi.order.dao.FoodStyleDao;
import com.baiyi.order.dao.FoodTasteDao;
import com.baiyi.order.dao.MarqueeDao;
import com.baiyi.order.dao.MaterialDao;
import com.baiyi.order.dao.RefundDao;
import com.baiyi.order.dao.StyleDao;
import com.baiyi.order.dao.TasteDao;
import com.baiyi.order.dao.TemplateDao;
import com.baiyi.order.dao.TemplateFoodDao;
import com.baiyi.order.dao.TemplateMarqueeDao;
import com.baiyi.order.dao.TemplateMaterialDao;
import com.baiyi.order.dao.TypeDao;
import com.baiyi.order.model.Refund;
import com.baiyi.order.util.EnumList.RefundReasonEnum;
import com.baiyi.order.util.EnumList.RefundTypeEnum;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring2.xml")
public class RefundTest {

	@Resource
	private FoodDao foodDao;
	@Resource
	private FoodTasteDao foodTasteDao;
	@Resource
	private FoodStyleDao foodStyleDao;
	@Resource
	private TypeDao typeDao;
	@Resource
	private TasteDao tasteDao;
	@Resource
	private StyleDao styleDao;
	@Resource
	private MaterialDao materialDao;
	@Resource
	private MarqueeDao marqueeDao;
	@Resource
	private TemplateDao templateDao;
	@Resource
	private TemplateFoodDao templateFoodDao;
	@Resource
	private TemplateMaterialDao templateMaterialDao;
	@Resource
	private TemplateMarqueeDao templateMarqueeDao;
	@Resource
	private RefundDao refundDao;

	@Test
	public void init() {

		for (int i = 0; i < 5; i++) {
			Refund r = new Refund();
			r.setTerminalNo("tno" + i + 1);
			r.setAuthenticode("xyz");
			r.setAmount(10);
			r.setHappentime(new Date());
			r.setOrderNo("order:110" + i);
			r.setReason(RefundReasonEnum.values()[i % 2]);
			r.setType(RefundTypeEnum.values()[i % 2]);

			refundDao.save(r);
		}

	}

}
