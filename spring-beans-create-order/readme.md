> by Yuanwl

# 测试 bean 的创建顺序

## @Component 等注解和 @SpringBootConfiguration 等注解（不使用条件注解）

通过 Bean、BeanSun 和 BeanX 的测试，发现：

1. @SpringBootConfiguration 配置类的同名 bean 会创建，@Component 不会创建；
2. @SpringBootConfiguration 配置类的 bean 名和 @Component 的不一样时，后者才会创建；
3. 不管这两个注解在父项目还是子项目，规则都一样；
3. 和 @Component 同等级别的 @Service 等注解一样遵循这个规则；

## @Component 等注解和 @SpringBootConfiguration 等注解（使用条件注解 @ConditionalOnMissingBean）

通过 BeanY 和 BeanZ、BeanZSun 的测试，发现：

创建bean优先级：不用 @ConditionalOnMissingBean 的bean方法 > @Component 等注解 > 子项目使用 @ConditionalOnMissingBean 的bean方法 > 父项目使用 @ConditionalOnMissingBean 的bean方法。前面的同名bean创建后，后面的就不会创建。前面3种方式，不管这些注解在父项目还是子项目，都不影响这个规则。

所以，一个bean如果想要被后人覆盖，就在父项目里用条件注解创建，才能保证有机会被子项目覆盖。

其他条件注解大同小异，只是判断条件不一样罢了。
