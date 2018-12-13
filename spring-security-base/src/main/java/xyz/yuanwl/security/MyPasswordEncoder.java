package xyz.yuanwl.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 自定义密码编码器
 * @author Yuanwl
 * @date 2018-12-01 17:40:21
 * @version v1.0.0
 */
public class MyPasswordEncoder implements PasswordEncoder {

	/**
	 * 一般在注册用户时调用此方法对原始密码加密，然后储存到数据库。通常，一个好的编码算法应用SHA-1 或者更大的哈希与一个8字节或更大的随机生成的盐
	 * @param arg0 储存中的密码
	 * @return java.lang.String
	 * @author Yuanwl
	 * @date 2018-12-01 17:40:33
	 * @version v1.0.0
	 */
	@Override
	public String encode(CharSequence arg0) {
		return arg0.toString(); //这里我直接原样返回了，一般情况下要实现自己的加密逻辑
	}

	/**
	 * 认证用户信息时，security 会调用此方法验证提交的原始密码经过与上面 encode 同样的算法加密后，是否与存储中获得的已编码密码匹配，
	 * 如果密码匹配则返回true，如果不匹配则返回false。存储的密码本身永远不会被解码
	 * @param arg0 提交的原始密码
	 * @param arg1 储存中的已编码密码
	 * @return boolean
	 * @author Yuanwl
	 * @date 2018-12-01 17:41:18
	 * @version v1.0.0
	 */
	@Override
	public boolean matches(CharSequence arg0, String arg1) {
		return arg1.equals(arg0.toString()); //一般先把提交的密码编码一次，再和储存中的密码匹配
	}

}
