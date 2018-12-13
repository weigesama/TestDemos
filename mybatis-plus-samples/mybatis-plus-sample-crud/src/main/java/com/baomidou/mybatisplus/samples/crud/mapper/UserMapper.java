package com.baomidou.mybatisplus.samples.crud.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.samples.crud.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * MP 支持不需要 UserMapper.xml 这个模块演示内置 CRUD 咱们就不要 XML 部分了
 * </p>
 *
 * @author hubin
 * @since 2018-08-11
 */
//@Mapper
public interface UserMapper extends BaseMapper<User> {

}
