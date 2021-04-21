package com.sx.blog.common.util;
import com.sx.blog.common.pojo.User;
public class UserThreadLocalUtil {

	//线程安全
	private static ThreadLocal<User> userThreadLocal = new ThreadLocal<>();
	
	//1.存放数据
	public static void set(User user) {
		userThreadLocal.set(user);
	}
	
	//2.取数据
	public static User get() {
		return userThreadLocal.get();
	}
	
	public static void remove() {
		//防止内存泄漏
		userThreadLocal.remove();
	}
}
