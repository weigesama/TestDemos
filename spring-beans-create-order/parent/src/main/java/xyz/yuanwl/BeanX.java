package xyz.yuanwl;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * <p>
 *
 * @author Yuanwl
 * @date 2018/12/7 19:41
 */
@Slf4j
@Component
//@Service
public class BeanX {
	public BeanX(){
		log.warn("BeanX");
	}
	public BeanX(String name){
		log.warn("BeanX-{}", name);
	}
}
