package xyz.yuanwl;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import xyz.yuanwl.config.SpringContextHolder;
import xyz.yuanwl.domain.User;
import xyz.yuanwl.domain.UserMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *
 * @author Yuanwl
 * @date 2018/11/15 11:26
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@Slf4j
public class AppTest {
	@Autowired
	private UserMapper userMapper;

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	private User user;
	@Test
	@Rollback
	public void test1() {
		log.warn("userMapper.getUser={}", userMapper.getUser(1)); //执行sql
		log.warn("userMapper.getUser={}", userMapper.getUser(1)); //不执行sql，虽然mapper配置文件里手动配置了不使用二级缓存，但是不影响一级缓存，这里用的是一级缓存，仍然有效

		log.warn("userMapper.getAllUsers={}", userMapper.getAllUsers()); //执行sql，查询结果会插入一级缓存
		log.warn("userMapper.getAllUsers={}", userMapper.getAllUsers()); //不执行sql，直接从一级缓存拿数据
		log.warn("userMapper.deleteUser={}", userMapper.deleteUser(1)); //执行sql，删除数据，增删改操作都会触发缓存清空
		log.warn("userMapper.getAllUsers={}", userMapper.getAllUsers()); //执行sql，因为缓存被清空了，要重新查并且更新缓存
		log.warn("userMapper.getAllUsers={}", userMapper.getAllUsers()); //不执行sql，直接从一级缓存拿数据
	}

	// 测试Redis存储和获取一个List
	@Test
	public void testRedisCacheSetList() {
		List<User> users = new ArrayList<>();
		user = userMapper.getUser(1);
		users.add(user);
		users.add(user);
		users.add(user);
		redisTemplate.opsForValue().set(user.getId() + "", users, 10, TimeUnit.MINUTES);
		users = (List<User>) redisTemplate.opsForValue().get(user.getId() + "");
		System.err.println(JSON.toJSONString(users));
	}

	// 测试Redis存储和获取一个Object
	@Test
	public void testRedisCacheSetObject() {
		System.err.println(redisTemplate.opsForValue().get("1"));
	}

	// 测试 通过Spring Aware获取Spring容器中的额Bean
	@Test
	public void testApplicationContextAware() {
		RedisTemplate redisTemplate = SpringContextHolder.getBean("redisTemplate");
		System.err.println(redisTemplate);
	}
}
