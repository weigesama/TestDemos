package xyz.yuanwl.domain;

import lombok.Data;

/**
 * <p>
 *
 * @author Yuanwl
 * @date 2018/11/16 17:04
 */
@Data
public class Blog {
	private Integer id;
	private String state;
	private String title;
	private String authorName;
	private Integer featured;
}
