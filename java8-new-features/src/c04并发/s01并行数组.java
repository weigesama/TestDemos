package c04并发;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class s01并行数组 {
	public static void main(String[] args) {
		long[] arrayOfLong = new long[20000];
		
		System.err.println("初始数组前10个元素：");
		Arrays
			.stream(arrayOfLong) // 以数组为数据源生成一个流
			.limit(10).forEach(i -> System.err.print(i + " ")); // 打印前10个元素
		System.err.println();
		
		Arrays
			.parallelSetAll( // 并行设置数组所有元素
					arrayOfLong, 
					index -> ThreadLocalRandom.current().nextInt(1000000) // 用lambda表达式，把对应下标的元素设置为一个随机数
			);
		System.err.println("用随机数并行初始化所有元素后的数组前10个元素：");
		Arrays.stream(arrayOfLong).limit(10).forEach(i -> System.err.print(i + " "));
		System.err.println();
		
		Arrays.parallelSort(arrayOfLong); // 并行排序，默认增序
		System.err.println("对数组并行排序后前10个元素：");
		Arrays.stream(arrayOfLong).limit(10).forEach(i -> System.err.print(i + " "));

	}
}