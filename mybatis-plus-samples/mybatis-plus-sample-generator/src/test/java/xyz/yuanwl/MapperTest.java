//package xyz.yuanwl;
//
//import lombok.extern.slf4j.Slf4j;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import xyz.yuanwl.mapper.ResourceMapper;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * <p>
// *
// * @author Yuanwl
// * @date 2018/11/27 16:53
// */
//@Slf4j
//public class MapperTest extends BaseTest {
//	@Autowired
//	private ResourceMapper resourceMapper;
//	@Test
//	public void test(){
//		Map<String, Object> map = new HashMap<>();
//		map.put("type", 1);
//		map.put("userId", 1);
//		log.warn("findAll={}", resourceMapper.findAll());
//		log.warn("selectById={}", resourceMapper.selectById(1));
//	}
//}
