package com.baomidou.mybatisplus.samples.logic;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.samples.logic.entity.User;
import com.baomidou.mybatisplus.samples.logic.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SampleTest {

	@Resource
	private UserMapper userMapper;

	//下面执行的都是逻辑删除而不是物理删除，可能是因为mp发现实体类里有@TableLogic属性的缘故

	@Test
	public void testLogicDeleteById() {
		log.warn("userMapper.selectById(1)={}", userMapper.selectById(1)); //有值
		log.warn("userMapper.deleteById(1)={}", userMapper.deleteById(1)); //删除成功返回1
		log.warn("userMapper.selectById(1)={}", userMapper.selectById(1)); //null，select会过滤掉被逻辑删除的数据
	}

	@Test
	public void testLogicDeleteBatchIds() {
		log.warn("userMapper.selectBatchIds(Arrays.asList(1, 2, 3))={}", userMapper.selectBatchIds(Arrays.asList(1, 2, 3)));
		log.warn("userMapper.deleteBatchIds(Arrays.asList(1, 2, 3))={}", userMapper.deleteBatchIds(Arrays.asList(1, 2, 3)));
		log.warn("userMapper.selectBatchIds(Arrays.asList(1, 2, 3))={}", userMapper.selectBatchIds(Arrays.asList(1, 2, 3)));
	}

	@Test
	public void testLogicDelete() {
		userMapper.delete(new QueryWrapper<User>().eq("age", 2));
	}

}
