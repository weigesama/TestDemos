package c03其他新特性;

import java.util.Optional;

/**
 * Optional 是个容器：它可以保存类型T的值，或者仅仅保存null。
 * <p>Optional提供很多有用的方法，这样我们就不用显式进行空值检测。Optional 类的引入很好的解决空指针异常。
 * <br><a href="http://www.runoob.com/java/java8-optional-class.html">Optional 类扩展阅读</a>
 * <p>
 * 创建人：yuanwl <br>
 * 创建时间：2018年8月20日 下午5:11:59 <br>
 * <p>
 * 修改人： <br>
 * 修改时间： <br>
 * 修改备注： <br>
 * </p>
 */
public class s01OptionalTest {
	public static void main(String args[]) {

		Integer value1 = null;
		Integer value2 = new Integer(10);

		Optional<Integer> a = Optional.ofNullable(value1); // ofNullable 允许 null 参数
		Optional<Integer> b = Optional.of(value2); // of 不允许 null 参数，如果传入 null，抛出异常 NullPointerException

		// Optional.isPresent - 判断值是否存在
		System.err.println("第一个参数值存在: " + a.isPresent());
		System.err.println("第二个参数值存在: " + b.isPresent());

		System.err.println(a.orElse(new Integer(0))); // orElse - 如果a值存在，返回它，否则返回默认值0，可以很好的避免空指针
		System.err.println(b.get()); // get - 获取值，值需要存在，否则报“java.util.NoSuchElementException: No value present”

		System.err.println("a: " + a.orElseGet(() -> 1)); // orElseGet - 如果a存在值，返回值，否则触发 () -> 1（表示返回1），并返回该 lambda 表达式的结果 1
		System.err.println("b: " + b.orElseGet(() -> 1));
		System.err.println(a.map(s -> "Hey a " + s + "!")); // map - 如果存在该值，返回 lambda 表达式执行结果（这里s代表a的值null，下面b是10），
																							// 如果s为null，返回返回 Optional.empty ；否则，返回一个 Optional 描述结果（下面是 Optional[Hey a 10!] ）
		System.err.println(b.map(s -> "Hey a " + s + "!"));
		System.err.println(a.map(s -> "Hey a " + s + "!").orElse("Hey null!"));
		System.err.println(b.map(s -> "Hey b " + s + "!").orElse("Hey null!"));
	}
}