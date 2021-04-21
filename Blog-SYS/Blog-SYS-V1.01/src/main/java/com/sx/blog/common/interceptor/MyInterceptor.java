package com.sx.blog.common.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import com.sx.blog.common.pojo.User;
import com.sx.blog.common.util.UserThreadLocalUtil;
import com.sx.blog.sys.service.UserService;
/**
 * 
 * @author 邓宇阳
 *      定义拦截器
 */
@Component
public class MyInterceptor implements HandlerInterceptor {
	@Autowired
	private UserService userService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//1.从cookie获取标识,获取token  userId
		Integer userId = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 1) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("userId")) {
					userId = Integer.valueOf(cookie.getValue());
				}
			}
		} else {
			HttpSession session = request.getSession();
			if (session.getAttribute("userId") != null)
				userId = Integer.valueOf((String) session.getAttribute("userId"));
		}

		//判断标识拦截ajax请求
		String homeUrl = request.getContextPath();
		if (userId == null) {
			if (request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) { //如果是ajax请求响应头会有x-requested-with
				System.out.println("ajax请求被拦截");
				System.out.println("ajax拦截器userId=="+request.getSession().getAttribute("userId"));
				//告诉ajax我是重定向
				response.setHeader("REDIRECT", "REDIRECT");
				//告诉ajax我重定向的路径
				response.setHeader("CONTENTPATH",homeUrl+ "/user/login");
				response.setStatus(HttpServletResponse.SC_FORBIDDEN);
				return false;
			}
			System.out.println("不是ajax已拦截");
			response.sendRedirect("/user/login");
			return false;
		}

		//3.从数据库获取用户身份
		User user = userService.getUserInfoByUserId(userId);
		//3.1判断该用户是否存在
		if(!StringUtils.isEmpty(user)) {
			//3.2用户存在
			user.setPassword("CouDD");
			//3.3controller使用数据
			UserThreadLocalUtil.set(user);
			return true;
		}
		//3.3用户不存在重定向登录页面
		response.sendRedirect("/user/login");
		return false;
	}
}
