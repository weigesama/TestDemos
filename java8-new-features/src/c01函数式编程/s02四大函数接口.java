package c01函数式编程;

/**
 * 函数式接口，指的是只有一个函数（方法）的接口，这样的接口可以被Lambda表达式实现。
 * <p>
 * 为了方便开发者使用函数接口，Java8定义了4大类主要的函数接口，分别在不同情况下使用：
 * <ul>
 * <li>Function<T,R>接口：函数接口，接受一个T类型参数，返回一个R类型值，使用 R apply(T t) 方法执行实现，即 T -> R；</li>
 * <li>Consumer<T>接口：消费者，接收一个T类型参数，没有返回值，使用 void accept(T t) 方法执行实现，即 T -> void；</li>
 * <li>Supplier<R>接口：提供者，不接受参数，返回一个R类型值，使用 R get() 方法执行实现，即 () -> R；</li>
 * <li>Predicate<T,Boolean>接口：判断，接收一个T类型参数，返回一个Boolean类型值，多用于判断与过滤，可以把它理解成特殊的 Function<T,R>，
 * 使用 Boolean test(T) 方法执行实现，即 T -> Boolean；</li>
 * </ul>
 * Java8还定义了几十种函数接口，其实都是上面这4大类接口的延伸，有规律可循，<a href="https://www.cnblogs.com/invoker-/p/7709052.html">详情点此文了解</a> 。
 * <p>
 * 创建人：yuanwl <br>
 * 创建时间：2018年8月16日 下午8:45:11 <br>
 * <p>
 * 修改人： <br>
 * 修改时间： <br>
 * 修改备注： <br>
 * </p>
 */
public class s02四大函数接口 {

}
