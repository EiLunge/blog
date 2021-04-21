package com.sx.blog.admin.enetity;

import java.io.Serializable;

import lombok.Data;

/**
 * 管理员登录实体类
 * @author 浩爸爸
 *
 */
@Data
public class AdminUser implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String userName;
	
	public String password;
	
}
