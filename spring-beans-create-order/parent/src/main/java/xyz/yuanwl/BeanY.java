package xyz.yuanwl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * @author Yuanwl
 * @date 2018/12/7 19:41
 */
@Slf4j
@Component
//@ConditionalOnMissingBean(BeanY.class) //这个注解不能用在这个地方
public class BeanY {
	public BeanY(){
		log.warn("BeanY");
	}
	public BeanY(String name){
		log.warn("BeanY-{}", name);
	}
}
