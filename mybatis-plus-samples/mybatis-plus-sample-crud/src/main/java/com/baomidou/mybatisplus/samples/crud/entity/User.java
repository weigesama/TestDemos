package com.baomidou.mybatisplus.samples.crud.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户实体对应表 user
 * </p>
 *
 * @author hubin
 * @since 2018-08-11
 */
@Data
@Accessors(chain = true) // 链式调用
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
