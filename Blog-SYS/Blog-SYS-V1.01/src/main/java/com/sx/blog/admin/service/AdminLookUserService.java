package com.sx.blog.admin.service;

import java.util.List;

import com.sx.blog.admin.enetity.User;

public interface AdminLookUserService {
	/*
	 * 查看所有的用户信息
	 */
	List<User> selectUser();
	
	/*
	 * 修改用户信息
	 */
	Integer updateUser(User user);
	
}
