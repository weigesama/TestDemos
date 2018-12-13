//package xyz.yuanwl;
//
//import lombok.extern.slf4j.Slf4j;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.transaction.annotation.Transactional;
//import xyz.yuanwl.entity.Order;
//import xyz.yuanwl.entity.User;
//import xyz.yuanwl.mapper.BlogMapper;
//import xyz.yuanwl.mapper.OrderMapper;
//import xyz.yuanwl.mapper.PersonMapper;
//import xyz.yuanwl.mapper.UserMapper;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * <p>
// *
// * @author Yuanwl
// * @date 2018/11/15 11:26
// */
//@Transactional
//@Slf4j
//public class CRUDTest extends BaseTest{
//
//	@Autowired
//	OrderMapper orderMapper;
//
//	@Test
//	@Rollback
//	public void c() {
//		Order order = new Order().setOrderNo("007").setOrderPrice(7f);
//		log.warn("orderMapper.insert={}", orderMapper.insert(order));
//		log.warn("order.getId={}", order.getOrderId());
//	}
//
//	@Test
//	@Rollback
//	public void r() {
//		log.warn("orderMapper.selectById={}", orderMapper.selectById(1));
//	}
//
//	@Test
//	@Rollback
//	public void u() {
//		Order order = new Order().setOrderId(1).setOrderNo("xxx");
//		log.warn("personMapper.updateById={}", orderMapper.updateById(order));
//		log.warn("orderMapper.selectById={}", orderMapper.selectById(1));
//	}
//
//	@Test
//	@Rollback
//	public void d() {
//		log.warn("orderMapper.selectById={}", orderMapper.selectById(1));
//		log.warn("personMapper.deleteById={}", orderMapper.deleteById(1));
//		log.warn("orderMapper.selectById={}", orderMapper.selectById(1));
//	}
//
//}
