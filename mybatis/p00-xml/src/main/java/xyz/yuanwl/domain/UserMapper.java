package xyz.yuanwl.domain;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *
 * @author Yuanwl
 * @date 2018/11/15 11:17
 */
//@Mapper//声明成mybatis Dao层的Bean，也可以在启动类上使用@MapperScan注解声明
public interface UserMapper {
	User getUser(int id);
	Map  getUserToMap(int id);

	List<User> findUsersIn(@Param("ids") String ids, @Param("name") String name); // 这里必要要加 @Param 注解，否则会报错

	int addUser(User user);
	int addUserAndSetId1(User user);
	int addUserAndSetId2(User user);
	int addUserAndSetId3(User user);
	int addUserByMap(Map<String, Object> map);

	int deleteUser(int id);

	int updateUser(User user);

	List<User> findUserList(@Param("userCustomer") User user);
	Integer findUserCount(@Param("userCustomer") User user);
}
