package xyz.yuanwl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

import java.util.List;

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
		// 开始分页查询，页码从1开始（传<=0会自动转为1）。注意：只有下面第一个mybatis的查询方法会分页，后面的不会！
		PageHelper.startPage(2, 2);
		List<User> list1 = userMapper.findUserList();
		log.warn("list1={}", list1);
		for (Object obj: list1){
			if (obj instanceof User){
				User user = (User) obj;
				log.warn("user={}", user);
			}
		}

		// 用PageInfo对结果进行包装，PageInfo包含了非常全面的分页属性
		PageInfo pageInfo = new PageInfo(list1);
		log.warn("pageInfo={}", pageInfo);
		List<User> listu = pageInfo.getList();
		for (Object obj: listu){
			if (obj instanceof User){
				User user = (User) obj;
				log.warn("user={}", user);
			}
		}

		// 下面这个查询不会分页，会查出所有数据，除非查询前再调用一次 PageHelper.startPage
		List<User> list2 = userMapper.findUserList();
		log.warn("list2={}", list2);
	}

	@Test
	@Rollback
	public void test2() {
		PageHelper.startPage(2, 2);
		// Page比PageInfo粗略些
		Page<User> page = userMapper.findUserPage();
		log.warn("page={}", page);
		log.warn("page.getClass().getName()={}", page.getClass().getName());
		for (Object obj: page.getResult()){
			if (obj instanceof User){
				User user = (User) obj;
				log.warn("user={}", user);
			}
		}
	}
}
