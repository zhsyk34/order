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
import com.baiyi.order.dao.StyleDao;
import com.baiyi.order.dao.TasteDao;
import com.baiyi.order.dao.TemplateDao;
import com.baiyi.order.dao.TemplateFoodDao;
import com.baiyi.order.dao.TemplateMarqueeDao;
import com.baiyi.order.dao.TemplateMaterialDao;
import com.baiyi.order.dao.TerminalDao;
import com.baiyi.order.dao.TypeDao;
import com.baiyi.order.model.Food;
import com.baiyi.order.model.FoodStyle;
import com.baiyi.order.model.FoodTaste;
import com.baiyi.order.model.Marquee;
import com.baiyi.order.model.Material;
import com.baiyi.order.model.Style;
import com.baiyi.order.model.Taste;
import com.baiyi.order.model.Template;
import com.baiyi.order.model.TemplateFood;
import com.baiyi.order.model.TemplateMarquee;
import com.baiyi.order.model.TemplateMaterial;
import com.baiyi.order.model.Terminal;
import com.baiyi.order.model.Type;
import com.baiyi.order.util.EnumList.Effect;
import com.baiyi.order.util.EnumList.MarqueeDirectionEnum;
import com.baiyi.order.util.EnumList.MaterialTypeEnum;
import com.baiyi.order.util.EnumList.TemplateContentEnum;
import com.baiyi.order.util.EnumList.TemplateMaterialEnum;
import com.baiyi.order.util.EnumList.TemplateTypeEnum;
import com.baiyi.order.util.EnumList.TerminalTypeEnum;
import com.baiyi.order.util.FormatUtil;
import com.baiyi.order.util.RandomUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring2.xml")
public class InitTest {

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
	private TerminalDao terminalDao;

	@Test
	public void init() {
		// type
		String[] types = { "饭", "面", "饮料", "甜点", "水果" };
		for (int i = 0; i < types.length; i++) {
			Type type = new Type();
			type.setName(types[i]);
			typeDao.save(type);
		}

		// style
		String[] styles = { "酸", "甜", "苦", "辣", "咸" };
		for (int i = 0; i < styles.length; i++) {
			Style style = new Style();
			style.setName(styles[i]);
			styleDao.save(style);
		}

		// taste
		int tastes = 15;
		for (int i = 0; i < tastes; i++) {
			Taste taste = new Taste();
			taste.setPrice(RandomUtil.randomInteger(3, 25));
			int index = RandomUtil.randomInteger(1, styles.length - 1);
			taste.setStyleId(index + 1);
			taste.setName(styles[index] + (i + 1));
			tasteDao.save(taste);
		}

		// material
		int materials = 10;
		for (int i = 1; i <= materials; i++) {
			Material material = new Material();
			material.setName("素材" + i);
			material.setPath("20160304" + RandomUtil.randomString(30) + ".jpg");
			material.setType(MaterialTypeEnum.values()[RandomUtil.randomInteger(0, 1)]);
			materialDao.save(material);
		}

		// food
		int foods = 9;
		for (int i = 1; i <= foods; i++) {
			Food food = new Food();
			food.setName("food" + i);
			food.setPrice(RandomUtil.randomInteger(10, 100));
			food.setMaterialId(RandomUtil.randomInteger(1, materials));
			food.setTypeId(RandomUtil.randomInteger(1, types.length));
			foodDao.save(food);

			for (int k = 1; k <= styles.length;) {
				FoodStyle fs = new FoodStyle();
				fs.setFoodId(i);
				fs.setStyleId(k);
				foodStyleDao.save(fs);

				k += RandomUtil.randomInteger(1, 3);
			}
			for (int k = 1; k <= tastes;) {
				FoodTaste ft = new FoodTaste();
				ft.setFoodId(i);
				ft.setTasteId(k);
				foodTasteDao.save(ft);
				k += RandomUtil.randomInteger(1, 5);
			}
		}

		// marquee
		String[] marquees = { "欢迎光临", "天气预报", "廉价促销", "古诗十九首", "咖啡屋" };
		for (int i = 0; i < marquees.length; i++) {
			Marquee marquee = new Marquee();
			marquee.setTitle(marquees[i]);
			marquee.setContent(RandomUtil.randomString(30));
			marquee.setDirection(MarqueeDirectionEnum.values()[RandomUtil.randomInteger(0, 1)]);
			marquee.setSpeed(RandomUtil.randomInteger(20, 80));
			marquee.setFont("KaiTi");
			marquee.setSize(RandomUtil.randomInteger(12, 60) + "px");
			marquee.setBackground("#" + RandomUtil.randomString(6));
			marquee.setColor("#" + RandomUtil.randomString(6));

			marqueeDao.save(marquee);
		}

		// terminal
		int terminals = 5;
		for (int i = 1; i <= terminals; i++) {
			Terminal terminal = new Terminal();
			terminal.setTerminalNo("tno1000" + i);
			terminal.setType(FormatUtil.getEnum(TerminalTypeEnum.class, RandomUtil.randomInteger(0, 1)));
			terminal.setLocation("xm000" + i);
			terminal.setCreatetime(new Date());
			terminal.setUserId(1);
			terminalDao.save(terminal);
		}

		//
		int templates = 6;
		for (int i = 1; i <= templates; i++) {
			Template template = new Template();
			template.setName("模板" + i);
			template.setType(TemplateTypeEnum.values()[RandomUtil.randomInteger(0, 2)]);

			template.setContent(TemplateContentEnum.values()[RandomUtil.randomInteger(0, 2)]);
			template.setEffect(Effect.values()[RandomUtil.randomInteger(0, 4)]);
			template.setInterlude(RandomUtil.randomInteger(10, 60));

			templateDao.save(template);

			for (int k = 1; k <= foods;) {
				TemplateFood tf = new TemplateFood();
				tf.setTemplateId(i);
				tf.setFoodId(k);
				templateFoodDao.save(tf);

				k += RandomUtil.randomInteger(1, 2);
			}
			for (int k = 1; k <= marquees.length;) {
				TemplateMarquee tm = new TemplateMarquee();
				tm.setTemplateId(i);
				tm.setMarqueeId(k);
				templateMarqueeDao.save(tm);

				k += RandomUtil.randomInteger(1, 2);
			}

			for (int k = 1; k <= materials;) {
				TemplateMaterial tm = new TemplateMaterial();
				tm.setTemplateId(i);
				tm.setMaterialId(k);
				tm.setType(TemplateMaterialEnum.values()[RandomUtil.randomInteger(0, 3)]);
				templateMaterialDao.save(tm);

				k += RandomUtil.randomInteger(1, 2);
			}
		}
	}
}
