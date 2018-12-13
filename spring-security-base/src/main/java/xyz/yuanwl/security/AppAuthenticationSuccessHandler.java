package xyz.yuanwl.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * 自定义认证成功处理类，验证用户成功后处理不同用户跳转到不同的页面
 *
 * @author Yuanwl
 * @version v1.0.0
 * @date 2018-12-03 09:56:32
 */
@Component
public class AppAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	/**
	 * Spring Security 通过 RedirectStrategy 对象负责所有重定向事务
	 */
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	/**
	 * 重写此方法，方法中通过 RedirectStrategy 对象重定向到指定的url
	 *
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @param authentication
	 * @author Yuanwl
	 * @date 2018-12-03 19:25:21
	 * @version v1.0.0
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
		// 通过determineTargetUrl方法返回需要跳转的url
		String targetUrl = determineTargetUrl(authentication);
		// 重定向请求到指定的url
		redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, targetUrl);
	}

	/**
	 * 从 Authentication 对象中提取当前登录用户的角色，并根据其角色返回适当的 URL
	 *
	 * @param authentication
	 * @return java.lang.String
	 * @author Yuanwl
	 * @date 2018-12-03 09:30:22
	 * @version v1.0.0
	 */
	protected String determineTargetUrl(Authentication authentication) {
		String url = "";

		// 获取当前登录用户的角色权限集合
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

		List<String> roles = new ArrayList<>();

		// 将角色名称添加到List集合
		for (GrantedAuthority a : authorities) {
			roles.add(a.getAuthority());
		}

		// 判断不同角色跳转到不同的url
		if (isAdmin(roles)) {
			url = "/admin";
		} else if (isUser(roles)) {
			url = "/home";
		} else {
			url = "/accessDenied";
		}
		System.err.println("url = " + url);
		return url;
	}

	private boolean isUser(List<String> roles) {
		if (roles.contains("ROLE_USER")) {
			return true;
		}
		return false;
	}

	private boolean isAdmin(List<String> roles) {
		if (roles.contains("ROLE_ADMIN")) {
			return true;
		}
		return false;
	}

}
