package xyz.yuanwl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import xyz.yuanwl.mapper.UserMapper;
import xyz.yuanwl.model.User;

/**
 * 这里实现了 UserDetailsService 接口，可以在登录认证时，被 Spring Security 调用 loadUserByUsername 方法获取 UserDetails 进行认证
 *
 * @author Yuanwl
 * @version v1.0.0
 * @date 2018-12-03 09:17:45
 */
@Service
public class UserService implements UserDetailsService {

	@Autowired
	UserMapper userMapper;

	/**
	 * 实现接口中的 loadUserByUsername 方法，通过该方法查询到对应的用户
	 *
	 * @param username 前端传过来的用户名
	 * @return org.springframework.security.core.userdetails.UserDetails
	 * @author Yuanwl
	 * @date 2018-12-02 10:14:52
	 * @version v1.0.0
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// 调用持久层接口findByLoginName方法查找用户
		User user = userMapper.findByLoginName(username);
		System.err.println("user = " + user);
		if (user == null) {
			throw new UsernameNotFoundException("用户名不存在");
		}
		return user;
	}

//	/**
//	 * 实现接口中的 loadUserByUsername 方法，通过该方法查询到对应的用户
//	 *
//	 * @param username 前端传过来的用户名
//	 * @return org.springframework.security.core.userdetails.UserDetails
//	 * @author Yuanwl
//	 * @date 2018-12-02 10:14:52
//	 * @version v1.0.0
//	 */
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		// 调用持久层接口findByLoginName方法查找用户
//		User user = userMapper.findByLoginName(username);
//		System.err.println("user = " + user);
//		if (user == null) {
//			throw new UsernameNotFoundException("用户名不存在");
//		}
//		// 创建List集合，用来保存用户权限，GrantedAuthority对象代表赋予给当前用户的权限
//		List<GrantedAuthority> authorities = new ArrayList<>();
//		// 获得当前用户权限集合
//		List<Role> roles = user.getRoles();
//		for (Role role : roles) {
//			// 将关联对象Role的authority属性保存为用户的认证权限
//			authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
//		}
//		// 此处返回的是 org.springframework.security.core.userdetails.User 类，该类是 security 内 UserDetails 的实现类
//		return new org.springframework.security.core.userdetails.User(user.getLoginName(), user.getPassword(), false, true, true, true, authorities);
//	}

}
