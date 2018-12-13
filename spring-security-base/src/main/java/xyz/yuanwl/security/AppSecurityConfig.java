package xyz.yuanwl.security;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import xyz.yuanwl.service.UserService;

/**
 * 自定义 Spring Security 配置类，继承自 WebSecurityConfigurerAdapter，相关配置重写对应方法即可
 *
 * @author Yuanwl
 * @version v1.0.0
 * @date 2018-12-03 09:32:04
 */
@Configuration
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * 认证提供者接口
	 */
	@Autowired
	private AuthenticationProvider authenticationProvider;

	@Autowired
	AppAuthenticationSuccessHandler appAuthenticationSuccessHandler;

	@Autowired
	AppAuthenticationFailureHandler appAuthenticationFailureHandler;

	@Bean
	public PasswordEncoder passwordEncoder() {
//		return new MyPasswordEncoder(); //自定义密码编码器
		return new BCryptPasswordEncoder(); //BCryptPasswordEncoder 是 Spring Security 推荐的 PasswordEncoder 接口的实现类，用来加密密码、验证密码
	}

	/**
	 * DaoAuthenticationProvider 是 Spring Security 提供的 AuthenticationProvider 实现，在这个 bean 里指定了自定义 userService、passwordEncoder 等
	 *
	 * @return org.springframework.security.authentication.AuthenticationProvider
	 * @author Yuanwl
	 * @date 2018-12-03 09:38:39
	 * @version v1.0.0
	 */
	@Bean
	public AuthenticationProvider authenticationProvider() {
		// 创建DaoAuthenticationProvider对象
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		// 不要隐藏"用户未找到"的异常
		provider.setHideUserNotFoundExceptions(false);
		// 通过重写configure方法添加自定义的认证方式。
		provider.setUserDetailsService(userService);
		// 设置密码加密程序
		provider.setPasswordEncoder(passwordEncoder);
		return provider;
	}

//	/**
//	 * 这个方法不是重写父类的，但是也会被父类调用，实现的效果和下面的 configure(AuthenticationManagerBuilder auth) 差不多
//	 * @param auth
//	 * @author Yuanwl
//	 * @date 2018-12-04 10:11:07
//	 * @version v1.0.0
//	 */
//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		System.err.println("AppSecurityConfig configureGlobal() 调用......");
//		// 设置两个仅存在于内存的用户，这个方便做简单应用不需要数据库时用
//		// spring-security 5.0 之后需要密码编码器，否则会抛出异常：There is no PasswordEncoder mapped for the id "null"
//		auth.inMemoryAuthentication().passwordEncoder(passwordEncoder).withUser("test").password("111111").roles("USER");
//		auth.inMemoryAuthentication().passwordEncoder(passwordEncoder).withUser("admin").password("111111").roles("ADMIN", "DBA");
//
////		auth.authenticationProvider(authenticationProvider); //也可以放在下面的方法里
//	}

	/**
	 * 在这里面可以设置认证提供者
	 * @param auth
	 * @author Yuanwl
	 * @date 2018-12-04 10:27:03
	 * @version v1.0.0
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		System.err.println("AppSecurityConfig configure auth......");
		auth.authenticationProvider(authenticationProvider);
	}

	/**
	 * 设置各种拦截、放行规则
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		System.err.println("AppSecurityConfig configure http......");
		http.authorizeRequests()
				//定义放行规则
				.antMatchers("/login", "/css/**", "/js/**", "/image/*").permitAll() // 配置这些路径可以随意访问，注意 spring-security5.0 之后需要手动放行静态资源
				.antMatchers("/", "/home").hasRole("USER") //设置访问首页需要有“USER”角色
				.antMatchers("/admin/**").hasAnyRole("ADMIN", "DBA") //设置访问 /admin/ 及其任意子目录需要有“ADMIN”或“DBA”角色
				.anyRequest().authenticated() //其他任何请求都要认证后才能访问

				//定义表单登录规则
				.and()
				.formLogin() //使用表单登录，这样会把默认的 basic 登录禁用，换成表单登录
				.loginPage("/login") //自定义登录页（默认值是 /login ）
				.loginProcessingUrl("/auth") //自定义登录认证接口（默认值是 /login ）
				.usernameParameter("loginName").passwordParameter("password") //设置登录时接收的 用户名、密码 的参数名，默认是 username、password
				//.successForwardUrl("") //默认登录成功后的跳转页
				//.defaultSuccessUrl("") //同上
				.successHandler(appAuthenticationSuccessHandler) //设置登录成功后的处理程序，这个比起 shiro 的设置登录成功后跳转页功能更强大
				.failureHandler(appAuthenticationFailureHandler) //登录失败处理

				//定义退出接口的放行规则
				.and()
				.logout().permitAll() //设置退出接口随意访问

				//定义异常、拒绝访问时的跳转页面
				.and()
				.exceptionHandling().accessDeniedPage("/accessDenied");
	}

}
