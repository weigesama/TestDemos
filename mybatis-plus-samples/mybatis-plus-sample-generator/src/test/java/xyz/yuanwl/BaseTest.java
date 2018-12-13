package xyz.yuanwl;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <p>
 * 单元测试基类
 * @author Yuanwl
 * @date 2018/11/21 15:36
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class BaseTest {

	@Test
	public void testSlf4j() throws Exception {
		log.info("输出info");
		log.warn("输出warn");
		log.error("输出error");
		try {
			int i = 1 / 0;
		} catch (Exception e) {
			log.error("测试异常", e);
		}
	}
}
