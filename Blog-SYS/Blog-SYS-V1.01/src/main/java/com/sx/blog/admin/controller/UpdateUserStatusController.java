package com.sx.blog.admin.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sx.blog.admin.service.IUpdateUserStatusService;
import com.sx.blog.common.SysResult;

/**
 * 更改登录信息控制器
 * @author 浩爸爸
 *
 */
@Controller
public class UpdateUserStatusController extends BaseController{
	
	@Autowired
	private IUpdateUserStatusService service;
	
	/*
	 * 禁止
	 */
	@RequestMapping("updatestatus")
	@ResponseBody
	public SysResult UpdateStatus(String username) {
		
		 //调用业务层方法
		 service.UpdateStatus(username);
	
		 return SysResult.success();
	}
	/*
	 * 允许
	 */
	@RequestMapping("updatelogin")
	@ResponseBody
	public SysResult UpdateStatuslogin(String username) {
			 
		 //调用业务层方法
		 service.UpdateStatusLogin(username);
	
		 return SysResult.success();
	}
}
