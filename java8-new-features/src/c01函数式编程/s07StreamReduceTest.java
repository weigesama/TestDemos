package c01函数式编程;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.Test;

/**
 * reduce，返回单个的结果值，并且reduce操作每处理一个元素总是创建一个新值。常用的方法有average, sum, min, max, count，使用reduce方法都可实现。
 * <br>扩展阅读：<a href="https://blog.csdn.net/piglite/article/details/53823584">reduce方法详解</a>
 * <p>
 * 创建人：yuanwl <br>
 * 创建时间：2018年8月20日 上午11:58:27 <br>
 * <p>
 * 修改人： <br>
 * 修改时间： <br>
 * 修改备注： <br>
 * </p>
 */
public class s07StreamReduceTest {

	@Test
	public void testReduce() {
		
		List<Integer> is = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8 );

		// 求集合元素之和
		Integer result = is.stream()
				.reduce(0, Integer::sum); // 一参 0 是初始值，将会传给第一次调用二参时的1参；二参传入某个方法或lambda表达式：1参是上次计算的结果，2参是stream元素。
				// 这里传入sum，整个reduce的意思就是对stream元素求和。传入下面的lambda表达式结果一样。
				// .reduce( 0, (sum, item) -> sum + item );
		System.err.println(result);

		// 求和
		is.stream()
			.reduce((i, j) -> i + j)
			.ifPresent(System.err::println); // 因为reduce没传初始值，如果数组为空，那求和结果可能就为空。ifPresent 是 Optional 的方法，当前面的值非空时，
																// 才执行括号里的方法，从而避免为空时打印空指针
		
		// 求最大值
		is.stream().reduce(Integer::max).ifPresent(System.err::println);

		// 求最小值
		is.stream().reduce(Integer::min).ifPresent(System.err::println);

		// 做逻辑
		is.stream()
			.reduce((i, j) -> i > j ? j : i) // lambda 表达式效果和 Integer::min 一样，都是求最小值
			.ifPresent(System.err::println);

		// 求逻辑求乘机
		int result2 = is.stream().filter(i -> i % 2 == 0).reduce(1, (i, j) -> i * j);

		Optional.of(result2).ifPresent(System.err::println);
	}

}
