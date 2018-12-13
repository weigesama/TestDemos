package xyz.yuanwl;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import xyz.yuanwl.domain.User;
import xyz.yuanwl.domain.UserMapper;

import java.util.HashMap;
import java.util.Map;

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
}
