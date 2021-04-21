package com.sx.blog.admin.mapper;

import com.sx.blog.admin.enetity.AdminUser;

/**
 * 管理员登录接口
 * @author 浩爸爸 
 *
 */
public interface AdminMapper {
	/*
	 * 管理员登录持久层接口
	 */
	AdminUser selectAdminUser(String userName);
	
}
