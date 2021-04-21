package com.sx.blog.admin.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sx.blog.admin.service.IAdminUserService;
import com.sx.blog.common.SysResult;


/**
 * 管理员控制器层
 * @author 浩爸爸
 *
 */
@Controller
@RequestMapping("/admin")
public class AdminUserController extends BaseController{
	
	@Autowired
	private IAdminUserService mapper;
	
	@RequestMapping("/adminlogin")
	public ModelAndView showlogin() {
		
		ModelAndView modle=new ModelAndView();
		
		//这里封装的是templates.pages.admin里面的登录页面
		modle.setViewName("/admins/adminlogin");
		
		return modle;
	}
	
	@RequestMapping("/doadminlogin")
	public SysResult login(String username,String password,HttpSession session) {
		
		//调用业务层方法执行登录
		mapper.AdminLogin(username, password);
		
		//将登录信息放入session中
		//session.setAttribute("username", data.getUserName());
		//session.setAttribute("password", data.getPassword());
		return SysResult.success("登入成功");
	}

}
