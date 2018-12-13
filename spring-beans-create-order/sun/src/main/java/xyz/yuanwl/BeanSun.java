package xyz.yuanwl;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * @author Yuanwl
 * @date 2018/12/7 19:36
 */
@Component
@Slf4j
public class BeanSun extends Bean {
	public BeanSun() {
		super();
		log.warn("BeanSun");
	}

	public BeanSun(String name) {
		super(name);
		log.warn("BeanSun-{}", name);
	}
}
