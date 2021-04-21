package com.sx.blog.admin.enetity;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.Data;

/**
 * 管理员查询用户实体类
 * @author 浩爸爸
 *
 */
@Data
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	
	private String eMail;
	
	private Integer gender;
	
	private String password;
	
	private String job;
	
	private Timestamp createTime;


}
