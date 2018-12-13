package xyz.yuanwl.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义认证失败处理类，验证用户失败后，带上异常信息跳转到登录页
 *
 * @author Yuanwl
 * @version v1.0.0
 * @date 2018-12-03 09:56:32
 */
@Component
public class AppAuthenticationFailureHandler implements AuthenticationFailureHandler {

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
		System.err.println(e);
		redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, "/login?error=" + e.getMessage());
	}
}
