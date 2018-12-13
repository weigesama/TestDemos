package c01函数式编程;

/**
 * lambda 表达式类似于js6的箭头函数。js6用函数参数接收箭头函数，而java8用函数接口接收。
 * 所以在js6里，lambda是函数，而在Java里，lambda表达式实际上是函数接口的一个实现。
 * <p>
 * 创建人：yuanwl <br>
 * 创建时间：2018年8月19日 下午9:12:20 <br>
 * <p>
 * 修改人： <br>
 * 修改时间： <br>
 * 修改备注： <br>
 * </p>
 */
public class s04使用自定义函数接口定义lambda表达式 {
	public static void main(String args[]) {

		// 类型声明。后面的 lambda 表达式实现了函数接口MathOperation的唯一的普通方法operation，下同
		MathOperation addition = (int a, int b) -> a + b;

		// 不用类型声明
		MathOperation subtraction = (a, b) -> a - b;

		// 大括号中的返回语句
		MathOperation multiplication = (int a, int b) -> {
			return a * b;
		};

		// 没有大括号及返回语句
		MathOperation division = (int a, int b) -> a / b;

		// 把方法operate当成参数传入别的方法
		System.err.println("10 + 5 = " + operate(10, 5, addition));
		System.err.println("10 - 5 = " + operate(10, 5, subtraction));
		System.err.println("10 x 5 = " + operate(10, 5, multiplication));
		System.err.println("10 / 5 = " + operate(10, 5, division));

		// 不用括号
		GreetingService greetService1 = message -> System.err.println("Hello " + message);

		// 用括号
		GreetingService greetService2 = (message) -> System.err.println("Hello " + message);

		greetService1.sayMessage("Runoob");
		greetService2.sayMessage("Google");
	}

	private static int operate(int a, int b, MathOperation mathOperation) { // 这里把MathOperation函数式接口当成参数传给了operate方法
		return mathOperation.operation(a, b);
	}

	interface MathOperation {
		int operation(int a, int b);
	}

	interface GreetingService {
		void sayMessage(String message);
	}

}
