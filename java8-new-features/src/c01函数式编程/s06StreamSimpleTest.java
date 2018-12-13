package c01函数式编程;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Stream（流）是一个来自数据源的元素队列并支持聚合操作，是Java 8 API添加的一个新的抽象，
 * 可以让你使用一种类似用 SQL 语句从数据库查询数据的直观方式来提供一种对 Java 集合运算和表达的高阶抽象。
 * <p>
 * 这种风格将要处理的元素集合看作一种流， 流在管道中传输， 并且可以在管道的节点上进行处理， 比如筛选， 排序，聚合等。
 * 元素流在管道中经过中间/中期操作（intermediate operation）的处理，最后由最终/晚期操作（terminal operation）得到前面处理的结果。
 * <br>
 * 中间操作会返回一个新的stream——执行一个中间操作（例如filter()、distinct()、sorted()、map()、flatMap()等，其一般是对数据集的整理（过滤、排序、匹配、抽取等）），
 * 并不会执行实际的过滤操作，而是创建一个新的stream，并将原stream中符合条件的元素放入新创建的stream。
 * <br>
 * 晚期操作往往是完成对数据集中处理，如forEach()，还有allMatch()、anyMatch()、findAny()、 findFirst()，数值计算类的方法有sum、max、min、average等等；
 * 也可以是对集合的处理，如reduce()、collect()等等。reduce()方法的处理方式一般是每次都产生新的数据集，而collect()方法是在原数据集的基础上进行更新，
 * 过程中不产生新的数据集。
 * <p>一些概念解析：
 * <ul>
 * <li>元素是特定类型的对象，形成一个队列。 Java中的Stream并不会存储元素，而是按需计算。</li>
 * <li>数据源 流的来源。 可以是集合，数组，I/O channel， 产生器generator 等。</li>
 * <li>聚合操作 类似SQL语句一样的操作， 比如filter, map, reduce, find, match, sorted等。</li>
 * </ul>
 * <p>和以前的Collection操作不同， Stream操作还有两个基础的特征：
 * <ul>
 * <li>Pipelining: 中间操作都会返回流对象本身。 这样多个操作可以串联成一个管道， 如同流式风格（fluent style）。 
 * 这样做可以对操作进行优化， 比如延迟执行(laziness)和短路( short-circuiting)。</li>
 * <li>内部迭代： 以前对集合遍历都是通过Iterator或者For-Each的方式, 显式的在集合外部进行迭代， 这叫做外部迭代。 
 * Stream提供了内部迭代的方式， 通过访问者模式(Visitor)实现。</li>
 * </ul>
 * 扩展阅读：<a href="http://www.runoob.com/java/java8-streams.html">Stream详细介绍</a>
 * <p>
 * 创建人：yuanwl <br>
 * 创建时间：2018年8月19日 下午11:36:56 <br>
 * <p>
 * 修改人： <br>
 * 修改时间： <br>
 * 修改备注： <br>
 * </p>
 */
public class s06StreamSimpleTest {
	public static void main(String args[]) {
		System.err.println("使用 Java 7 ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓");

		// 计算空字符串
		List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
		System.err.println("列表: " + strings);
		long count = getCountEmptyStringUsingJava7(strings);

		System.err.println("空字符数量为: " + count);
		count = getCountLength3UsingJava7(strings);

		System.err.println("字符串长度为 3 的数量为: " + count);

		// 删除空字符串
		List<String> filtered = deleteEmptyStringsUsingJava7(strings);
		System.err.println("筛选后的列表: " + filtered);

		// 删除空字符串，并使用逗号把它们合并起来
		String mergedString = getMergedStringUsingJava7(strings, ", ");
		System.err.println("合并字符串: " + mergedString);
		List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);

		// 获取列表元素平方数
		List<Integer> squaresList = getSquares(numbers);
		System.err.println("平方数列表: " + squaresList);
		List<Integer> integers = Arrays.asList(1, 2, 13, 4, 15, 6, 17, 8, 19);

		System.err.println("列表: " + integers);
		System.err.println("列表中最大的数 : " + getMax(integers));
		System.err.println("列表中最小的数 : " + getMin(integers));
		System.err.println("所有数之和 : " + getSum(integers));
		System.err.println("平均数 : " + getAverage(integers));
		System.err.println("随机数: ");

		// 输出10个随机数
		Random random = new Random();

		for (int i = 0; i < 10; i++) {
			System.err.println(random.nextInt());
		}

		
		
		System.err.println("使用 Java 8 ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓");
		System.err.println("strings：" + strings);

		count = strings
				.stream() // 第1种生成流的方法：stream() − 为集合创建串行流
				.filter(string -> string.isEmpty()) // filter 方法用于通过设置的条件过滤出元素（注意是找出而不是排除掉），左边代码片段使用 filter 方法找出空字符串
				.count(); // 统计过滤后保留的（也就是符合过滤条件的）元素个数
		System.err.println("空字符串数量为: " + count);

		count = strings.stream().filter(string -> string.length() == 3).count();
		System.err.println("字符串长度为 3 的数量为: " + count);

		filtered = strings.stream().filter(string -> !string.isEmpty()).collect(
				Collectors.toList() // 将筛选后的流转换成集合，Collectors 类还实现了很多归约操作，例如将流转换成聚合元素
				);
		System.err.println("筛掉空字符串后的列表: " + filtered);

		mergedString = strings.stream().filter(string -> !string.isEmpty()).collect(
				Collectors.joining(", ") // 将筛选剩的元素用逗号拼接成字符串
				);
		System.err.println("合并字符串: " + mergedString);

		squaresList = numbers.stream()
				.map(i -> i * i) // map 方法用于依次映射（转换）每个原来的元素到新的流元素，左边代码片段使用 map 映射每个元素为对应的平方数
				.distinct() // 去重，类似于SQL
				.collect(Collectors.toList());
		System.err.println("Squares List: " + squaresList);
		
		System.err.println("integers：" + integers);

		IntSummaryStatistics stats = integers.stream()
				.mapToInt((x) -> x) // 将元素映射为整形，里面的lambda表达式是必须的，用于把元素作为函数结果输出。测试发现 (x) 的 () 可以省略
//				.map(x -> x) // 用这个方法不行！当最终操作是统计方法时，不能用 map，只能用上面的 mapToInt 或其他类似的方法。
				.summaryStatistics(); // 返回一个可以产生统计结果的收集器
		
		System.err.println("列表中最大的数 : " + stats.getMax());
		System.err.println("列表中最小的数 : " + stats.getMin());
		System.err.println("所有数之和 : " + stats.getSum());
		System.err.println("平均数 : " + stats.getAverage());
		
		// 另一种求和方式
		final Integer totalPoints = integers
				   .stream()
				   .parallel() // 上面创建的是串行流，调用这个方法后就变成并行流了
				   .map((x) -> x)
				   .reduce( 0, Integer::sum ); // 对元素求和，下面的效果一样。对于reduce方法详解，请参考【s07StreamReduceTest.java】。
					// .reduce( 0, (sum, item) -> sum + item );
		System.err.println("另一种求和方式：" + totalPoints);
		
		System.err.println("随机数: ");

		random
			.ints() // ??
			.limit(10) // limit 方法用于获取指定数量的流，左边代码片段使用 limit 方法输出 10 条数据
			.sorted() // sorted 方法用于对流进行排序，左边代码片段使用 sorted 方法对输出的 10 个随机数进行排序，默认增序
			.forEach(System.err::println); // forEach 相当于 js 的 forEach，这里 println 方法引用相当于 js 的回调函数，可以接收 forEach 内部传入的元素作为参数从而实现迭代打印元素

		// 并行处理
		count = strings
				.parallelStream() // 第2种生成流的方法：parallelStream() − 为集合创建并行流
				.filter(string -> string.isEmpty()).count();
		System.err.println("空字符串的数量为: " + count);
	}

	// 下面是Java7用到的各种方法 ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	
	private static int getCountEmptyStringUsingJava7(List<String> strings) {
		int count = 0;

		for (String string : strings) {

			if (string.isEmpty()) {
				count++;
			}
		}
		return count;
	}

	private static int getCountLength3UsingJava7(List<String> strings) {
		int count = 0;

		for (String string : strings) {

			if (string.length() == 3) {
				count++;
			}
		}
		return count;
	}

	private static List<String> deleteEmptyStringsUsingJava7(List<String> strings) {
		List<String> filteredList = new ArrayList<String>();

		for (String string : strings) {

			if (!string.isEmpty()) {
				filteredList.add(string);
			}
		}
		return filteredList;
	}

	private static String getMergedStringUsingJava7(List<String> strings, String separator) {
		StringBuilder stringBuilder = new StringBuilder();

		for (String string : strings) {

			if (!string.isEmpty()) {
				stringBuilder.append(string);
				stringBuilder.append(separator);
			}
		}
		String mergedString = stringBuilder.toString();
		return mergedString.substring(0, mergedString.length() - 2);
	}

	private static List<Integer> getSquares(List<Integer> numbers) {
		List<Integer> squaresList = new ArrayList<Integer>();

		for (Integer number : numbers) {
			Integer square = new Integer(number.intValue() * number.intValue());

			if (!squaresList.contains(square)) {
				squaresList.add(square);
			}
		}
		return squaresList;
	}

	private static int getMax(List<Integer> numbers) {
		int max = numbers.get(0);

		for (int i = 1; i < numbers.size(); i++) {

			Integer number = numbers.get(i);

			if (number.intValue() > max) {
				max = number.intValue();
			}
		}
		return max;
	}

	private static int getMin(List<Integer> numbers) {
		int min = numbers.get(0);

		for (int i = 1; i < numbers.size(); i++) {
			Integer number = numbers.get(i);

			if (number.intValue() < min) {
				min = number.intValue();
			}
		}
		return min;
	}

	private static int getSum(List numbers) {
		int sum = (int) (numbers.get(0));

		for (int i = 1; i < numbers.size(); i++) {
			sum += (int) numbers.get(i);
		}
		return sum;
	}

	private static int getAverage(List<Integer> numbers) {
		return getSum(numbers) / numbers.size();
	}
}