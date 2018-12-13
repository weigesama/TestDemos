package com.baomidou.samples.injector.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * 学生实体
 * @author nieqiurong 2018/8/11 20:20.
 */
@Data
@TableName(value = "student") //如果不用这个注解，mp获取到的表名将是类名的全大写
public class Student {

    private Long id;

    private String name;

    private Integer age;

}
