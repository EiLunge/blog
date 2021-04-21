package com.sx.blog.admin.mapper;

import org.apache.ibatis.annotations.Param;



/**
 * 修改用户登录状态持久层接口
 * @author 浩爸爸
 *
 */
public interface UpdateUserStatus {
	/*
	 * 根据用户名查询登录状态status
	 */
	Integer selectUsername(String username);
	
	/*
	 * 更改用户状态
	 */
	Integer updateUserStatus(
			@Param("status") Integer status,
			@Param("username") String username);
	
	/*
	 * 更改用户状态
	 */
	Integer updateUserStatusLogin(
			@Param("status") Integer status,
			@Param("username") String username);
}
