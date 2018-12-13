package com.baomidou.mybatisplus.samples.wrapper.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author miemie
 * @since 2018-08-10
 */
@Data
@Accessors(chain = true)
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
    private Long roleId;
}
