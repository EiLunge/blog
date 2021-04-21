package com.sx.blog.admin.service;


/**
 * 根据用户id更改登录标识
 * @author 浩爸爸
 *
 */
public interface IUpdateUserStatusService {
	/*
	 * 修改登录标识变为禁用
	 */
	String UpdateStatus(String username);
	
	/*
	 * 修改登录标识变为可用
	 */
	String UpdateStatusLogin(String username);
	
}
