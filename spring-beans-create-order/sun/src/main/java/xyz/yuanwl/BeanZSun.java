package xyz.yuanwl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * @author Yuanwl
 * @date 2018/12/7 19:36
 */
//@Component
@Slf4j
public class BeanZSun extends BeanZ {
	public BeanZSun() {
		super();
		log.warn("BeanZSun");
	}

	public BeanZSun(String name) {
		super(name);
		log.warn("BeanZSun-{}", name);
	}
}
