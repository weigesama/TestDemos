package xyz.yuanwl.mapper;


import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;
import xyz.yuanwl.model.Role;
import xyz.yuanwl.model.User;

import java.util.List;

public interface UserMapper {

	/**
	 * 根据loginName查询用户信息，同时关联查询出用户的权限
	 * @param loginName
	 * @return xyz.yuanwl.model.User
	 * @author Yuanwl
	 * @date 2018-12-02 10:08:28
	 * @version v1.0.0
	 */
	@Select("select * from tb_user where login_name = #{loginName}")
	@Results({
			@Result(id = true, column = "id", property = "id"),
			@Result(column = "login_name", property = "loginName"),
			@Result(column = "password", property = "password"),
			@Result(column = "username", property = "username"),
			@Result(column = "locked", property = "locked"),
			@Result(column = "deleted", property = "deleted"),
			@Result(column = "id", property = "roles",
					many = @Many(select = "findRoleByUser",
							fetchType = FetchType.EAGER))
	})
	User findByLoginName(String loginName);

	/**
	 * 根据用户id关联查询用户的所有权限
	 * @param id
	 * @return java.util.List<xyz.yuanwl.model.Role>
	 * @author Yuanwl
	 * @date 2018-12-02 11:07:34
	 * @version v1.0.0
	 */
	@Select(" SELECT id,authority FROM tb_role r,tb_user_role ur "
			+ " WHERE r.id = ur.role_id AND user_id = #{id}")
	List<Role> findRoleByUser(Long id);

}
