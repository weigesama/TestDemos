package xyz.yuanwl.domain;

import com.github.pagehelper.Page;

import java.util.List;

/**
 * <p>
 *
 * @author Yuanwl
 * @date 2018/11/15 11:17
 */
public interface UserMapper {
	List<User> findUserList();
	Page<User> findUserPage();
}
