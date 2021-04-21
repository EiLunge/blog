package com.sx.blog.admin.service.Impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sx.blog.admin.enetity.User;
import com.sx.blog.admin.mapper.AdminUserMapper;
import com.sx.blog.admin.service.AdminLookUserService;

@Service
public class AdminLookUserImpl implements AdminLookUserService{
	
	@Autowired
	private AdminUserMapper service;
	
	private String name;
	private Integer gender;	
	private Timestamp time;
	private String eMail;
	private String job;
	private String password;
	
	@Override
	public List<User> selectUser() {
		
		//调用持久层接口将数据遍历
		List<User> list = service.selectAllUser();
		
		List<User> lists =new ArrayList<User>();
		
		for (User user : list) {
		//	System.out.println(user);
			//创建新对象将数据封装
			User use = new User();
			name =user.getUsername();
			gender =user.getGender();
			time = user.getCreateTime();
			eMail = user.getEMail();
			password = user.getPassword();
			job = user.getJob();
			use.setCreateTime(time);
	        use.setPassword(password);
			use.setUsername(name);
			use.setEMail(eMail);
			use.setGender(gender);
			use.setJob(job);
			//讲对象放入新的集合
			lists.add(use);
		}
		
		return lists;
	}

	@Override
	public Integer updateUser(User user) {
		
		//从参数中获取用户名 邮箱 工作等
		String name = user.getUsername();
		String email = user.getEMail();
		Integer gender = user.getGender();
		
		String job = user.getJob();
		//调用持久层方法
		Integer cow =service.updateUser(email, gender, job, name);
		
		return cow;
	}

	
}
