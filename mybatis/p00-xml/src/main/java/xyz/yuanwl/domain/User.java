package xyz.yuanwl.domain;

import lombok.Data;

/**
 * 用户
 * @author Yuanwl
 * @date 2018-11-16 12:37:05
 * @version v1.0.0
 */
@Data
public class User {
	//实体类的属性和表的字段名称一一对应
	private Integer id;
	private String name;
	private Integer age;
}
