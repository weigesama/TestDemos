package xyz.yuanwl.domain;

import java.util.List;

/**
 * <p>
 *
 * @author Yuanwl
 * @date 2018/11/15 11:17
 */
public interface UserMapper {
	int deleteUser(int id);
	List<User> getAllUsers();
}
