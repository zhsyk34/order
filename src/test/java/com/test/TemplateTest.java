package com.test;

import java.util.List;

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
import com.baiyi.order.dao.StyleDao;
import com.baiyi.order.dao.TasteDao;
import com.baiyi.order.dao.TemplateDao;
import com.baiyi.order.dao.TemplateFoodDao;
import com.baiyi.order.dao.TemplateMarqueeDao;
import com.baiyi.order.dao.TemplateMaterialDao;
import com.baiyi.order.dao.TypeDao;
import com.baiyi.order.vo.TemplateVO;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring2.xml")
public class TemplateTest {

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

	@Test
	public void find() {
		TemplateVO tv = templateDao.findVO(1);
		System.out.println(JSONObject.fromObject(tv));
		
		List<TemplateVO> tvs = templateDao.findVOList();		
		System.out.println(JSONArray.fromObject(tvs));
	}
	

}
