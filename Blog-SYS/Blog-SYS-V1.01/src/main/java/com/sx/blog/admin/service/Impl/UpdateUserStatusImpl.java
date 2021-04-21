package com.sx.blog.admin.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sx.blog.admin.mapper.UpdateUserStatus;
import com.sx.blog.admin.service.IUpdateUserStatusService;
import com.sx.blog.admin.service.ex.UpdateUserStatusException;


/**
 * 修改用户登录状态业务层
 * @author 浩爸爸
 *
 */
@Service
public class UpdateUserStatusImpl implements IUpdateUserStatusService{
	
	@Autowired
	private UpdateUserStatus mapper;
	
	@Override
	public String UpdateStatus(String username) {
		
		//通过用户名获取状态信息,判断是否为0并且不大于1
		//System.out.println("获取的用户名:"+username);
		
		//用正则替换掉“”
		String name = username.replace("\"", "");
		
		Integer status = mapper.selectUsername(name);
		
		//查看是否已经被封
		if(status==1) { 
			
			  throw new UpdateUserStatusException("该账号已经被封停"); 
			  
		}else {
			//将该用户的登录状态变为1
		
			 mapper.updateUserStatus(1, name);

		}
		return null;
	}

	@Override
	public String UpdateStatusLogin(String username) {
		
			//通过用户名获取状态信息,判断是否为0并且不大于1
		//	System.out.println("允许登录获取的用户名:"+username);
			
			//用正则替换掉“”
			String name = username.replace("\"", "");
		
			Integer status = mapper.selectUsername(name);
		
			//查看是否已经被封
			if(status == 0) { 
				  throw new UpdateUserStatusException("该账号状态正常"); 
			} else {
				
				//将该用户的登录状态变为0
				mapper.updateUserStatusLogin(0, name);
			
			}
		return null;
	}

}
