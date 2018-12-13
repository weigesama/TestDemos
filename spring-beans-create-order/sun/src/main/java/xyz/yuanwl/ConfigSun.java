package xyz.yuanwl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

/**
 * <p>
 *
 * @author Yuanwl
 * @date 2018/12/7 19:47
 */
@SpringBootConfiguration
@Slf4j
public class ConfigSun {

	@Bean
	public BeanSun beanSun(){
		return new BeanSun("beanSun()");
	}

	@Bean
	public BeanSun beanSun1(){
		return new BeanSun("beanSun1()");
	}

	@Bean
	public BeanX beanX(){
		return new BeanX("beanX()");
	}

	@Bean
	public BeanY beanY0(){
		return new BeanY("ConfigSun.beanY0()");
	}

	@Bean
	@ConditionalOnMissingBean(BeanY.class)
	public BeanY beanY(){
		return new BeanY("ConfigSun.beanY()");
	}

	@Bean
	@ConditionalOnMissingBean(BeanZ.class)
	public BeanZ beanZ(){
		return new BeanZ("ConfigSun.beanZ()");
	}

}
