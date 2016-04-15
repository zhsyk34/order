package com.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.baiyi.order.dao.ActivityDao;
import com.baiyi.order.dao.FoodStyleDao;
import com.baiyi.order.dao.FoodTasteDao;
import com.baiyi.order.dao.MaterialDao;
import com.baiyi.order.dao.StyleDao;
import com.baiyi.order.dao.TasteDao;
import com.baiyi.order.dao.TypeDao;
import com.baiyi.order.model.Activity;
import com.baiyi.order.util.EnumList.ActivityTypeEnum;

import net.sf.json.JSONArray;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring2.xml")
public class ActivityTest {

	@Resource
	private ActivityDao activityDao;
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

	@Test
	public void find1() {
		List<Activity> list = activityDao.findList();
		System.out.println(JSONArray.fromObject(list));

		Integer tid = null;
		list = activityDao.findList(tid, null, null, true);
		System.out.println(JSONArray.fromObject(list));

		tid = 1;
		list = activityDao.findList(tid, null, ActivityTypeEnum.STOP, true);
		System.out.println(JSONArray.fromObject(list));
	}

	@Test
	public void find2() {

	}

}
