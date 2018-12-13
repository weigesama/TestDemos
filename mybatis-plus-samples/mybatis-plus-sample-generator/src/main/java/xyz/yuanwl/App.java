package xyz.yuanwl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>
 *
 * @author Yuanwl
 * @date 2018/11/21 14:26
 */
@SpringBootApplication
@MapperScan("xyz.yuanwl.mapper")
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
