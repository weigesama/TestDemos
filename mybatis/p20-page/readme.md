> by Yuanwl

# mybatis分页插件使用

步骤：
1. pom.xml 引入依赖；
2. 配置 application.yaml （分页其实可以不用配置）；
3. mapper 配置文件、Mapper 接口，原来怎么写就怎么写，分页是通过拦截实现的，对 sql 语句不影响；
4. 看你想返回 Page 对象还是 PageInfo 对象，有不同操作，具体看单元测试；


# 参考资料

- https://blog.csdn.net/qq_24005787/article/details/81612811
- https://blog.csdn.net/it18911008884/article/details/78520043
- https://blog.csdn.net/xiaolyuh123/article/details/73506189