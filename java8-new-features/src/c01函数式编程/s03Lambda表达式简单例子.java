package c01函数式编程;

import java.util.Arrays;

public class s03Lambda表达式简单例子 {
	static String S = "》";

	public static void main(String[] args) {
		String s = ">>";

		Arrays.asList("a", "b", "c").forEach(e -> System.err.println(e));

		Arrays.asList("1", "2", "3").forEach(e -> {
			S = "";
			System.err.println(S + e); /* 表达式内部访问属性不会导致属性变成final类型。至于Java为什么这么设计lambda，
															可以看这篇文章【https://www.cnblogs.com/invoker-/p/7709052.html】的“关于lambda的限制”了解 */
		});

		S = "";

		Arrays.asList("1", "2", "3").forEach(e -> {
			// s = ""; // 报错：Local variable s defined in an enclosing scope must be final or
			// effectively final，在表达式内访问局部变量，会隐式把它转换为final变量，不可修改！
			System.err.println(s + e); // *
		});

		// s = ""; // 把这里放开，上面*报错，原因一样
	}

}
