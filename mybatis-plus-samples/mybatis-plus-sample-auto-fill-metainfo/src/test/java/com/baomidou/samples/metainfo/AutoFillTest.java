package com.baomidou.samples.metainfo;

import com.baomidou.samples.metainfo.entity.User;
import com.baomidou.samples.metainfo.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 自动填充测试
 *
 * @author nieqiurong 2018-08-10 23:47:02.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class AutoFillTest {

	@Autowired
	private UserMapper userMapper;

	@Test
	public void test() {
		User user = new User(null, "Tom", 1, "tom@qq.com", null);
		userMapper.insert(user);
		Long id = user.getId();
		log.warn("query user:{}", userMapper.selectById(id));

		User beforeUser = userMapper.selectById(id);
		log.warn("before user:{}", beforeUser);
		beforeUser.setAge(12);
		userMapper.updateById(beforeUser);
		log.warn("query user:{}", userMapper.selectById(id));
	}
}
