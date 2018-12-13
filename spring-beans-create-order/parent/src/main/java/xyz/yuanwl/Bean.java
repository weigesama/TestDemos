package xyz.yuanwl;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 *
 * @author Yuanwl
 * @date 2018/12/7 19:41
 */
@Slf4j
public class Bean {
	public Bean(){
		log.warn("Bean");
	}

	public Bean(String name){
		log.warn("Bean-{}", name);
	}
}
