package com.sx.blog.admin.controller;


import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.sx.blog.admin.service.ex.InsertTagException;
import com.sx.blog.admin.service.ex.NotFindAdminUserException;
import com.sx.blog.admin.service.ex.PasswordException;
import com.sx.blog.admin.service.ex.ServiceException;
import com.sx.blog.admin.service.ex.UpdateUserStatusException;
import com.sx.blog.common.SysResult;

/**
 * 控制器父类
 * @author 浩爸爸
 *
 */

@RestControllerAdvice
public class BaseController {
	/**
	 * 响应状态：成功
	 */
	protected static final int OK=200;
	/**
	 * 从session中获取uid
	 * @param session HttpSession对象
	 * @return
	 */
	protected Integer getFromSessionUid(HttpSession session) {
		return Integer.valueOf(session.getAttribute("username").toString());
	}
	/**
	 * 从session中获取uid
	 * @param session HttpSession对象
	 * @return
	 */
	protected String getFromSessionusername(HttpSession session) {
		return String.valueOf(session.getAttribute("password").toString());
	}
	@ExceptionHandler({ServiceException.class})
	public SysResult handleException(Throwable e){
		SysResult jr=new SysResult();
		if(e instanceof NotFindAdminUserException) {
			jr.setStatus(400);
		}else if(e instanceof PasswordException) {
			jr.setStatus(500);
		}else if(e instanceof UpdateUserStatusException) {
			jr.setStatus(600);
		}else if(e instanceof InsertTagException) {
			jr.setStatus(700);
		}
		
//		switch(e.getClass().getSimpleName()) {
//		case "UsernameDuplicateException":
//			jr.setState(4000);
//			break;
//		case "UserNotFoundException":
//			jr.setState(4001);
//			break;
//		}	
		return jr;
	}
}
