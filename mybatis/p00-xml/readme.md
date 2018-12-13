> by Yuanwl

# springboot下使用mybatis步骤

1. pom.xml 文件引入 MySQL 和 mybatis 依赖；
2. 在 application.yml 或 application.properties 文件中定义配置；
3. 数据库中创建表：使用父项目脚本 doc/test.sql ，在数据库中执行；
4. 创建表对应的实体类：我这里把实体类和 Mapper 接口都放在 domain 包下面，方便管理；
5. 创建 Mapper 接口：有两种方式，1是在每个 Mapper 接口上面加 @Mapper 注解（洁癖推荐），2是在启动类上使用 @MapperScan 注解声明（懒蛇推荐）；
6. 在 resource/mapper 文件夹中创建接口对应的配置文件；
7. 写单元测试，或者 service 调用 mapper；

# mybatis基础知识点

## UserMapper.xml

- mapper 配置文件结构；
- namespace：注意是对应 Mapper 接口的全类名而不是包名，否则会报绑定错误；
- 单表增删改查；
- #{} 和 ${} 的区别；
- resultType；
- parameterType：https://blog.csdn.net/lixld/article/details/77980443 https://blog.csdn.net/programmer123455/article/details/78382974；
- 插入表并把生成id赋值到参数中的方式；
- <sql>片段定义和使用；

## OrderMapper.xml

- 如何解决表字段和属性名不对应导致查不到数据的问题；
- resultMap；

## PersonMapper.xml

- 一对一：两种方式，用 <association> 标签；
- 一对多：两种方式，用 <collection> 标签；
- 一对一和一对多结合使用；

## BlogMapper.xml

动态sql。


# 参考资料