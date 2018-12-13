package com.baomidou.samples.metainfo.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 * 填充器
 *
 * @author nieqiurong 2018-08-10 22:59:23.
 */
@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {

	/**
	 * 插入实体时，会触发这个方法
	 *
	 * @param metaObject 实体对象
	 * @author Yuanwl
	 * @date 2018-11-20 16:16:37
	 * @version v1.0.0
	 */
	@Override
	public void insertFill(MetaObject metaObject) {
		log.warn("start insert fill ....");
		//设置实体对象的属性值
		metaObject.setValue("operator", "Jerry");
	}

	/**
	 * 更新实体时，会触发这个方法
	 *
	 * @param metaObject
	 * @author Yuanwl
	 * @date 2018-11-20 16:16:57
	 * @version v1.0.0
	 */
	@Override
	public void updateFill(MetaObject metaObject) {
		log.warn("start update fill ....");
//        metaObject.setValue("operator","Tom");
		//更新是用下面这个方法，不再是上面的方法
		this.setFieldValByName("operator", "Tom", metaObject);
	}
}
