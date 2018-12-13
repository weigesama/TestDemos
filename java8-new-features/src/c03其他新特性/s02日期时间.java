package c03其他新特性;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.junit.Test;

public class s02日期时间 {
//	public static void main(String args[]) {
//		testLocalDateTime();
//		testZonedDateTime();
//	}

	/**
	 * 本地化日期时间 API
	 * <p>
	 * 创建人：yuanwl <br>
	 * 创建时间：2018年8月20日 下午5:59:16 <br>
	 * <p>
	 * 修改人： <br>
	 * 修改时间： <br>
	 * 修改备注： <br>
	 * </p>
	 */
	@Test
	public void testLocalDateTime() {
		// 获取本地当前的日期时间，精确到毫秒
		LocalDateTime currentTime = LocalDateTime.now();
		System.err.println("当前时间: " + currentTime);

		LocalDate date1 = currentTime.toLocalDate();
		System.err.println("date1: " + date1);

		int month = currentTime.getMonthValue(); // 这个方法返回的是月份数值，getMonth 返回的是英文月份名称
		int day = currentTime.getDayOfMonth();
		int seconds = currentTime.getSecond();
		System.err.println(month + " 月 " + day + " 日 " + seconds + " 秒 ");

		LocalDateTime date2 = currentTime.withDayOfMonth(10).withYear(2012); // 把当前时间的月改为10，年改为2012
		System.err.println("date2: " + date2);

		LocalDate date3 = LocalDate.of(2014, Month.DECEMBER, 12); // 初始化日期
		System.err.println("date3: " + date3);

		LocalTime date4 = LocalTime.of(22, 15); // 初始化时间
		System.err.println("date4: " + date4);

		LocalTime date5 = LocalTime.parse("20:15:30"); // 转换字符串为时间类型
		System.err.println("date5: " + date5);

		// LocalTime date6 = LocalTime.parse("2018-08-20 20:15:30"); // 报错
		// System.err.println("date6: " + date6);
	}

	/**
	 * 使用时区的日期时间API
	 * <p>
	 * 创建人：yuanwl <br>
	 * 创建时间：2018年8月20日 下午5:59:08 <br>
	 * <p>
	 * 修改人： <br>
	 * 修改时间： <br>
	 * 修改备注： <br>
	 * </p>
	 */
	@Test
	public void testZonedDateTime() {

		// 获取当前时间日期
		ZonedDateTime date1 = ZonedDateTime.parse("2015-12-03T10:15:30+05:30[Asia/Shanghai]");
		System.err.println("date1: " + date1);

		ZoneId id = ZoneId.of("Europe/Paris");
		System.err.println("ZoneId: " + id);

		ZoneId currentZone = ZoneId.systemDefault();
		System.err.println("当期时区: " + currentZone);
	}
}