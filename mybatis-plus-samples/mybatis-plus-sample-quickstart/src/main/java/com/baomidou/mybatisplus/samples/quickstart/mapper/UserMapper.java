package com.baomidou.mybatisplus.samples.quickstart.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.samples.quickstart.entity.User;

/**
 * Mapper需要基础BaseMapper，里面有基本的CRUD操作
 * @author Yuanwl
 * @date 2018-11-20 09:44:44
 * @version v1.0.0
 */
public interface UserMapper extends BaseMapper<User> {

}
