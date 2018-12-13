> by Yuanwl

# MyBatis 默认缓存介绍

正如大多数持久层框架一样，MyBatis 同样提供了一级缓存和二级缓存的支持。

对于缓存数据更新机制，当某一个作用域(一级缓存Session/二级缓存Namespaces)的进行了 C/U/D 操作后，默认该作用域下所有 select 中的缓存将被clear。

## 一级缓存

基于 PerpetualCache 的 HashMap 本地缓存，其存储作用域为 Session，当 Session flush 或 close 之后，该Session中的所有 Cache 就将清空。

一级缓存默认开启。看 AppTest.test1() 。

## 二级缓存

与一级缓存其机制相同，默认也是采用 PerpetualCache，HashMap存储，不同在于其存储作用域为 Mapper(Namespace)，并且可自定义存储源，如 Ehcache。二级缓存需要手动开启，默认的二级缓存在 XxxMapper.xml 文件中添加如下配置即可：

```xml
<mapper namespace="...">
<!-- 开启二级缓存 -->
<cache/>
```

cache 标签常用属性：

```xml
<cache
eviction="FIFO"  <!--回收策略为先进先出-->
flushInterval="60000" <!--自动刷新时间60s，默认不设置,也就是没有刷新间隔,缓存仅仅调用语句时刷新-->
size="512" <!--最多缓存512个引用对象-->
readOnly="true"/> <!--返回的对象被认为是只读的,在不同线程中的调用者之间修改它们会导致冲突。只读的缓存会给所有调用者返回缓存对象的相同实例。因此这些对象不能被修改。这提供了很重要的性能优势。可读写的缓存会返回缓存对象的拷贝(通过序列化)。这会慢一些,但是安全,因此默认是false。-->
```

上面可用的收回策略有:

- LRU – （默认的）最近最少使用的：移除最长时间不被使用的对象。
- FIFO – 先进先出：按对象进入缓存的顺序来移除它们。
- SOFT – 软引用：移除基于垃圾回收器状态和软引用规则的对象。
- WEAK – 弱引用：更积极地移除基于垃圾收集器状态和弱引用规则的对象。


二级缓存补充说明

- 映射文件中的所有select语句将会被缓存。
- 映射文件中的所有insert，update和delete语句会刷新缓存。
- 缓存默认使用Least Recently Used（LRU，最近最少使用的）算法来收回。
- 缓存会根据指定的时间间隔来刷新。
- 缓存默认存储列表集合或对象(无论查询方法返回什么)的 1024 个引用。
- 缓存会被视为是 read/write(可读/可写)的缓存,意味着对象检索不是共享的,而且可以安全地被调用者修改,而不干扰其他调用者或线程所做的潜在修改。


# 使用自定义缓存

除了这些自定义缓存的方式, 你也可以通过实现你自己的缓存或为其他第三方缓存方案创建适配器来完全覆盖缓存行为：

```xml
<cache type="com.domain.something.MyCustomCache"/>
```

这个示例展示了如何使用一个自定义的缓存实现。type属性指定的类必须实现 org.mybatis.cache.Cache 接口。这个接口是 MyBatis 框架中很多复杂的接口之一,但是简单 给定它做什么就行：

```java
public interface Cache {
  String getId();
  int getSize();
  void putObject(Object key, Object value);
  Object getObject(Object key);
  boolean hasKey(Object key);
  Object removeObject(Object key);
  void clear();
}
```

要配置你的缓存, 简单和公有的 JavaBeans 属性来配置你的缓存实现, 而且是通过 cache 元素来传递属性, 比如, 下面代码会在你的缓存实现中调用一个称为 “setCacheFile(String file)” 的方法:

```xml
<cache type="com.domain.something.MyCustomCache">
  <property name="cacheFile" value="/tmp/my-custom-cache.tmp"/>
</cache>
```

你可以使用所有简单类型作为 JavaBeans 的属性,MyBatis 会进行转换。 And you can specify a placeholder(e.g. ${cache.file}) to replace value defined at configuration properties.

Since 3.4.2, the MyBatis has been supported to call an initialization method after it's set all properties. If you want to use this feature, please implements the org.apache.ibatis.builder.InitializingObject interface on your custom cache class.

```java
public interface InitializingObject {
  void initialize() throws Exception;
}
```


# 参考资料

- https://www.cnblogs.com/xdp-gacl/p/4270403.html
- https://blog.csdn.net/xiaolyuh123/article/details/73771818