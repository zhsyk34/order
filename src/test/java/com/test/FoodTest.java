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
import com.baiyi.order.dao.MaterialDao;
import com.baiyi.order.dao.StyleDao;
import com.baiyi.order.dao.TasteDao;
import com.baiyi.order.dao.TypeDao;
import com.baiyi.order.model.Material;
import com.baiyi.order.model.Style;
import com.baiyi.order.model.Taste;
import com.baiyi.order.model.Type;
import com.baiyi.order.vo.FoodVO;
import com.baiyi.order.vo.TasteVO;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring2.xml")
public class FoodTest {

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

	@Test
	public void find1() {
		for (int i = 1; i < 8; i++) {
			Type type = foodDao.findType(i);
			Material material = foodDao.findMaterial(i);
			List<Style> styles = foodDao.findStyleList(i);
			List<Taste> tastes = foodDao.findTasteList(i);

			System.out.print("type: " + type.getName());
			System.out.print(" material: " + material.getName());
			System.out.print(" styles: " + JSONArray.fromObject(styles));
			System.out.println(" tastes: " + JSONArray.fromObject(tastes));
		}

		for (int i = 1; i < 8; i++) {
			FoodVO vo = foodDao.findVO(i);
			System.out.println(JSONObject.fromObject(vo));
		}
	}

	@Test
	public void find2() {
		List<TasteVO> taste = tasteDao.findVOList("咸", null, null);
		System.out.println(JSONArray.fromObject(taste));

		List<FoodVO> food = foodDao.findVOList();
		System.out.println(JSONArray.fromObject(food));

	}

}
