> by Yuanwl

# 代码生成器

我已经把代码生成器改成我们习惯所需的样子，一般直接执行单元测试方法 doGenerate
即可生成所需代码。

templates 目录下是我修改过的模板文件，主要给实体类加了几个 Lombok 注解，然后不生成接口和实现类，把 service 接口和实现类合二为一——这才是科学的规范。