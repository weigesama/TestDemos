package c02注解;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class s01重复注解 {
	@Target(ElementType.TYPE)
	@Retention(RetentionPolicy.RUNTIME)
	public @interface Filters {
		Filter[] value();
	}

	@Target(ElementType.TYPE)
	@Retention(RetentionPolicy.RUNTIME)
	@Repeatable(Filters.class) // 可重复注解 @Filter 必须要定义一个存放它的注解容器，比如上面的 @Filters ，然后在 @Repeatable 里面绑定之
	public @interface Filter {
		String value();
	};

	@Filter("filter1")
	@Filter("filter2")
	public interface Filterable {
	}

	public static void main(String[] args) {
		// getAnnotationsByType()是反射API提供的一个新方法，可以返回某个类型的重复注解
		for (Filter filter : Filterable.class.getAnnotationsByType(Filter.class)) {
			System.err.println(filter.value());
		}
	}

}
