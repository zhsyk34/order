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
import com.baiyi.order.dao.OrderDetailDao;
import com.baiyi.order.dao.OrderDetailTasteDao;
import com.baiyi.order.dao.OrderInfoDao;
import com.baiyi.order.dao.StyleDao;
import com.baiyi.order.dao.TasteDao;
import com.baiyi.order.dao.TerminalDao;
import com.baiyi.order.dao.TypeDao;
import com.baiyi.order.model.Food;
import com.baiyi.order.model.OrderDetail;
import com.baiyi.order.model.OrderDetailTaste;
import com.baiyi.order.model.OrderInfo;
import com.baiyi.order.model.Taste;
import com.baiyi.order.model.Terminal;
import com.baiyi.order.util.EnumList.OrderStatus;
import com.baiyi.order.util.EnumList.TerminalTypeEnum;
import com.baiyi.order.util.RandomUtil;
import com.baiyi.order.vo.FoodVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring2.xml")
public class OrderTest {

	@Resource
	private FoodDao foodDao;
	@Resource
	private FoodTasteDao foodTasteDao;
	@Resource
	private FoodStyleDao foodStyleDao;
	@Resource
	private TypeDao typeDao;
	@Resource
	private TerminalDao terminalDao;
	@Resource
	private TasteDao tasteDao;
	@Resource
	private StyleDao styleDao;
	@Resource
	private MaterialDao materialDao;
	@Resource
	private OrderInfoDao orderInfoDao;
	@Resource
	private OrderDetailDao orderDetailDao;
	@Resource
	private OrderDetailTasteDao orderDetailTasteDao;

	@Test
	public void find() {

	}

	@Test
	public void save() {
		List<Terminal> kitchens = terminalDao.findList(null, TerminalTypeEnum.KITCHEN, null);
		List<Terminal> shops = terminalDao.findList(null, TerminalTypeEnum.SHOP, null);
		List<FoodVO> foods = foodDao.findVOList();
		List<Taste> tastes = tasteDao.findList();

		int ks = kitchens.size();
		int ss = shops.size();
		int fs = foods.size();
		int ts = tastes.size();

		for (int i = 1; i <= 5; i++) {
			OrderInfo oi = new OrderInfo();

			oi.setId(i);
			oi.setOrderNo("orderno");
			oi.setShop(shops.get(RandomUtil.randomInteger(0, ss - 1)).getTerminalNo());
			oi.setKitchen(kitchens.get(RandomUtil.randomInteger(0, ks - 1)).getTerminalNo());
			oi.setStatus(OrderStatus.NORMAL);

			int all = 0;
			for (int j = 1; j <= fs;) {// foods
				OrderDetail od = new OrderDetail();
				od.setOrderId(i);
				// food
				od.setFoodId(j);
				Food food = foodDao.find(j);
				od.setName(food.getName());
				od.setPrice(food.getPrice());
				// count
				int count = RandomUtil.randomInteger(1, 5);
				od.setCount(count);
				// total
				double total = food.getPrice() * count;
				od.setTotal(total);

				orderDetailDao.save(od);

				int oiid = od.getId();
				for (int k = 0; k < ts;) {// tastes
					OrderDetailTaste odt = new OrderDetailTaste();
					odt.setOrderDetailId(oiid);
					odt.setName(tastes.get(k).getName());

					orderDetailTasteDao.save(odt);
					k += RandomUtil.randomInteger(2, 7);
				}

				// ...
				all += total;
				j += RandomUtil.randomInteger(2, 4);
			}

			double income = Math.ceil(all * 1.1 / 100) * 100;
			oi.setIncome(income);
			oi.setTotal(all);
			oi.setExpense(income - all);

			orderInfoDao.save(oi);
		}
	}

	@Test
	public void temp() {
		System.out.println(Math.ceil(1.74) * 100);
	}

}
