package xyz.yuanwl;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import xyz.yuanwl.domain.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *
 * @author Yuanwl
 * @date 2018/11/15 11:26
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
@Slf4j
public class AppTest {
	@Autowired
	private UserMapper userMapper;

	@Test
	@Rollback
	public void test1() {
		User user = new User();
		user.setName("李小龙");
		user.setAge(18);
//		log.warn("userMapper.addUser={}", userMapper.addUser(user));
		log.warn("userMapper.addUserAndSetId1={}", userMapper.addUserAndSetId1(user));
//		log.warn("userMapper.addUserAndSetId2={}", userMapper.addUserAndSetId2(user));
		log.warn("user.getId={}", user.getId());

		Map<String, Object> map = new HashMap<>();
		map.put("name", "周星驰");
		map.put("age", 18);
		log.warn("userMapper.addUserByMap={}", userMapper.addUserByMap(map));

		log.warn("userMapper.getUser={}", userMapper.getUser(1));
		log.warn("userMapper.getUserToMap={}", userMapper.getUserToMap(1));
		log.warn("userMapper.findUsersIn={}", userMapper.findUsersIn("1,2,3", "%孤%"));

		user.setAge(20);
		log.warn("userMapper.updateUser={}", userMapper.updateUser(user));

		log.warn("userMapper.deleteUser={}", userMapper.deleteUser(2));

		log.warn("userMapper.findUserList={}", userMapper.findUserList(user));
		log.warn("userMapper.findUserCount={}", userMapper.findUserCount(user));
	}

	@Autowired
	OrderMapper orderMapper;

	@Test
	@Rollback
	public void test2() {
		log.warn("orderMapper.getOrderErr={}", orderMapper.getOrderErr(1));
		log.warn("orderMapper.getOrderSucc={}", orderMapper.getOrderSucc(1));
		log.warn("orderMapper.getOrder2ResultMap={}", orderMapper.getOrder2ResultMap(1));
	}

	@Autowired
	private PersonMapper personMapper;

	@Test
	@Rollback
	public void test3() {
		log.warn("personMapper.getPersonWithIdCard={}", personMapper.getPersonWithIdCard(2));
		log.warn("personMapper.getPersonWithIdCard2={}", personMapper.getPersonWithIdCard2(2));

		log.warn("personMapper.getPersonWithBankCards={}", personMapper.getPersonWithBankCards(1));

		log.warn("personMapper.getPersonWithAllInfo={}", personMapper.getPersonWithAllInfo(1));
	}


	@Autowired
	BlogMapper blogMapper;

	@Test
	@Rollback
	public void test4(){
		Author author = new Author();
		author.setName("作者");
		log.warn("blogMapper.findBlogUsingIf={}", blogMapper.findBlogUsingIf("博客", author));
		log.warn("blogMapper.findBlogUsingChoose={}", blogMapper.findBlogUsingChoose("博客", author));

//		log.warn("blogMapper.updateBlogUsingTrim={}", blogMapper.updateBlogUsingTrim(1, "INCATIVE", "修改博客标题", author, 2));
		log.warn("blogMapper.updateBlogUsingSet={}", blogMapper.updateBlogUsingSet(1, "INCATIVE", "修改博客标题", author, 2));

		log.warn("blogMapper.findBlogUsingWhere={}", blogMapper.findBlogUsingWhere(null, "博客", author));
		log.warn("blogMapper.findBlogUsingTrim={}", blogMapper.findBlogUsingTrim("ACTIVE", "博客", author));

		log.warn("blogMapper.findBlogUsingForeach={}", blogMapper.findBlogUsingForeach(new Integer[]{1,2,3}));

		final List ids = new ArrayList();
		ids.add(1);
		ids.add(2);
		ids.add(3);
		Map params = new HashMap();
		params.put("ids", ids);
		params.put("title", "博客");
		log.warn("blogMapper.findBlogUsingForeachMap={}", blogMapper.findBlogUsingForeachMap(params));

		log.warn("blogMapper.findBlogUsingBind={}", blogMapper.findBlogUsingBind("博客"));
	}
}
