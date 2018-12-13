package xyz.yuanwl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * @author Yuanwl
 * @date 2018/12/7 19:41
 */
@Slf4j
public class BeanZ {
	public BeanZ(){
		log.warn("BeanZ");
	}
	public BeanZ(String name){
		log.warn("BeanZ-{}", name);
	}
}
