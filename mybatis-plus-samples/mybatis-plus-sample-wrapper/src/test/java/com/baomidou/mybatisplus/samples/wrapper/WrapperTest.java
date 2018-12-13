package com.baomidou.mybatisplus.samples.wrapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.samples.wrapper.entity.User;
import com.baomidou.mybatisplus.samples.wrapper.mapper.RoleMapper;
import com.baomidou.mybatisplus.samples.wrapper.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author miemie
 * @since 2018-08-10
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class WrapperTest {

	@Resource
	private UserMapper userMapper;
	@Resource
	private RoleMapper roleMapper;

	@Test
	public void query() {
		log.info("----- 普通查询 ------");
		List<User> plainUsers = userMapper.selectList(new QueryWrapper<User>().eq("role_id", 2L));
		List<User> lambdaUsers = userMapper.selectList(new QueryWrapper<User>().lambda().eq(User::getRoleId, 2L));
		Assert.assertEquals(plainUsers.size(), lambdaUsers.size());
		print(plainUsers);

		log.info("----- 带子查询(sql注入) ------");
		List<User> plainUsers2 = userMapper.selectList(new QueryWrapper<User>()
				.inSql("role_id", "select id from role where id = 2"));
		List<User> lambdaUsers2 = userMapper.selectList(new QueryWrapper<User>().lambda()
				.inSql(User::getRoleId, "select id from role where id = 2"));
		Assert.assertEquals(plainUsers2.size(), lambdaUsers2.size());
		print(plainUsers2);

		log.info("----- 带嵌套查询 ------");
		List<User> plainUsers3 = userMapper.selectList(new QueryWrapper<User>()
				.nested(i -> i.eq("role_id", 2L).or().eq("role_id", 3L))
				.and(i -> i.ge("age", 20)));
		List<User> lambdaUsers3 = userMapper.selectList(new QueryWrapper<User>().lambda()
				.nested(i -> i.eq(User::getRoleId, 2L).or().eq(User::getRoleId, 3L))
				.and(i -> i.ge(User::getAge, 20)));
		Assert.assertEquals(plainUsers3.size(), lambdaUsers3.size());
		print(plainUsers3);

		log.info("----- 自定义(sql注入) ------");
		List<User> plainUsers4 = userMapper.selectList(new QueryWrapper<User>()
				.apply("role_id = {0}", 2));
		print(plainUsers4);
	}

	@Test
	public void update() {
		log.info("----- 更新 ------");
		log.info("userMapper.selectById={}", userMapper.selectById(1));
		User user = new User().setName("李小龙").setAge(30);
		userMapper.update(user, new QueryWrapper<User>().eq("id", 1));
		log.info("userMapper.selectById={}", userMapper.selectById(1));

//		new UpdateWrapper<User>().set("name", "周星驰").set("age", 35).eq("id", 1); //UpdateWrapper怎么用？不太清楚
		log.info("userMapper.selectById={}", userMapper.selectById(1));
	}

	private <T> void print(List<T> list) {
		if (!CollectionUtils.isEmpty(list)) {
			list.forEach((T t) -> log.info(t.toString()));
		}
	}
}
