package xyz.yuanwl.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 用户实体类，这里实现了 UserDetails
 * @author Yuanwl
 * @date 2018-12-03 17:05:04
 * @version v1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class User implements Serializable, UserDetails {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String loginName;
	private String username;
	private String password;
	private Boolean locked;
	private Boolean deleted;

	private List<Role> roles;

	/** 权限列表，GrantedAuthority 对象代表赋予给当前用户的权限 */
	private List<GrantedAuthority> authorities;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if (authorities == null){
			authorities = new ArrayList<>();
			if (!CollectionUtils.isEmpty(roles)) {
				for (Role role : roles) {
					// 将关联对象Role的authority属性保存为用户的认证权限
					authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
				}
			}
		}
		return authorities;
	}

	/**
	 * 账号没过期？可以在数据库里用一个有效期字段表示，如果当前时间超过了这个字段，那就表示账号过期了
	 * @return boolean
	 * @author Yuanwl
	 * @date 2018-12-03 17:05:54
	 * @version v1.0.0
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/**
	 * 账号没被锁/禁用？禁用时，用户还没被逻辑删除掉，只是因为某些原因暂时禁用，后续还可以解禁
	 * @return boolean
	 * @author Yuanwl
	 * @date 2018-12-03 17:07:23
	 * @version v1.0.0
	 */
	@Override
	public boolean isAccountNonLocked() {
		return !locked;
	}

	/**
	 * 账号可用？不可用表示该用户被删除了（一般是逻辑删除，系统不会真的删除用户数据），是不可恢复的
	 * @return boolean
	 * @author Yuanwl
	 * @date 2018-12-03 17:08:41
	 * @version v1.0.0
	 */
	@Override
	public boolean isEnabled() {
		return !deleted;
	}

	/**
	 * 密码没过期？有些安全性高的系统，会要求用户一段时间换一次密码，所以给密码设置过期时间
	 * @return boolean
	 * @author Yuanwl
	 * @date 2018-12-03 17:07:50
	 * @version v1.0.0
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

}
