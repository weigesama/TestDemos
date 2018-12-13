package c01函数式编程;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 * 函数引用比lambda表达式更进一步，实现普通方法也可以作为参数传给其他方法。
 * <p>
 * 创建人：yuanwl <br>
 * 创建时间：2018年8月19日 下午9:22:23 <br>
 * <p>
 * 修改人： <br>
 * 修改时间： <br>
 * 修改备注： <br>
 * </p>
 */
public class s05方法引用 {
	public static void main(String[] args) {
		// 引用构造器：它的语法是Class::new，或者更一般的Class< T >::new，示例如下：
		// final Car car = Car.create(Car::new);
		final List<Car> cars = Arrays.asList(Car.create(Car::new), Car.create(Car::new));

		// 引用静态方法：它的语法是Class::static_method，示例如下：
		cars.forEach(Car::collide);

		// 引用某个类的成员方法（此方法是非静态方法，但是这里引用起来很像静态方法）：它的语法是Class::method，示例如下：
		cars.forEach(Car::repair);

		final Car police = Car.create(Car::new);
		// 引用某个实例对象的成员方法：它的语法是instance::method，示例如下：
		cars.forEach(police::follow);
	}
}

class Car {
	// 传入一个Supplier的lambda实现，返回这个表达式的执行结果
	public static Car create(final Supplier<Car> supplier) {
		return supplier.get();
	}

	public static void collide(final Car car) {
		System.err.println("Collided " + car.toString());
	}

	public void repair() {
		System.err.println("Repaired " + this.toString());
	}

	public void follow(final Car another) {
		System.err.println("Following the " + another.toString());
	}

}