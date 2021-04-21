package com.sx.blog.admin.service.Impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sx.blog.admin.enetity.AdminUser;
import com.sx.blog.admin.mapper.AdminMapper;
import com.sx.blog.admin.service.IAdminService;
import com.sx.blog.admin.service.ex.NotFindAdminUserException;
import com.sx.blog.admin.service.ex.PasswordException;

/**
 * 管理员业务层
 * @author 浩爸爸
 *
 */
@Service
public class AdminServiceImpl implements IAdminService{
	
	@Autowired
	private AdminMapper mapper;
	
	@Override
	public AdminUser AdminLogin(String username,String password) {
		
		//创建管理员对象，在数据库中通过用户名查询用户信息
		AdminUser result=mapper.selectAdminUser(username);
		
		//判断昵称是否存在，存在下一步
		if(result==null) {
			throw new NotFindAdminUserException("管理员昵称不存在，请联系掘CSDN系统开发人员");
		}
		
		//判断密码是否和数据库中一致
		if(!result.getPassword().equals(password)) {
			throw new PasswordException("你个假管理员,密码都错了");
		}
		
		//创建新对象将查询结果封装
		AdminUser user=new AdminUser();
		
		String pass=result.getPassword();
		
		String name=result.getUserName();
		
		user.setPassword(pass);
		
		user.setUserName(name);
		
		return user;	
	}

}
