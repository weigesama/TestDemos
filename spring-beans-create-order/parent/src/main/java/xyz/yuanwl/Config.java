package xyz.yuanwl;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

/**
 * <p>
 *
 * @author Yuanwl
 * @date 2018/12/7 20:14
 */
@SpringBootConfiguration
public class Config {

	@Bean
	@ConditionalOnMissingBean(BeanY.class)
	public BeanY beanY(){
		return new BeanY("beanY()");
	}

	@Bean
	@ConditionalOnMissingBean(BeanZ.class)
	public BeanZ beanZ(){
		return new BeanZ("beanZ()");
	}

}
