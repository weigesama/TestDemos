package c01函数式编程;

import java.util.function.Supplier;

public class s01接口的默认方法和静态方法 {

	private interface Defaulable {
		// 默认方法使得开发者可以在不破坏二进制兼容性的前提下，往现存接口中添加新的方法，即不强制那些实现了该接口的类也同时实现这个新加的方法。
		// 默认方法和抽象方法之间的区别在于抽象方法需要实现，而默认方法不需要。接口提供的默认方法会被接口的实现类继承或者覆写
		default String notRequired() { // 这个方法会被实现类继承，但是不要求强制覆写
			return "Default implementation";
		}
	}

	private static class DefaultableImpl implements Defaulable {
	}

	private static class OverridableImpl implements Defaulable {
		@Override
		public String notRequired() {
			return "Overridden implementation";
		}
	}

	private interface DefaulableFactory {
		// 接口允许定义静态方法了
		static Defaulable create(Supplier<Defaulable> supplier) {
			return supplier.get();
		}
	}

	public static void main(String[] args) {
		Defaulable defaulable = DefaulableFactory.create(DefaultableImpl::new);
		System.err.println(defaulable.notRequired());

		defaulable = DefaulableFactory.create(OverridableImpl::new);
		System.err.println(defaulable.notRequired());
	}

}
