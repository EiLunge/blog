package com.sx.blog.admin.service;

import com.sx.blog.admin.enetity.AdminUser;

/**
 * 管理员业务层接口
 * @author 浩爸爸
 *
 */
public interface IAdminUserService {
	   /*
	    *   处理管理员登录的业务层接口
	    */
	   AdminUser AdminLogin(String username,String password);

}
