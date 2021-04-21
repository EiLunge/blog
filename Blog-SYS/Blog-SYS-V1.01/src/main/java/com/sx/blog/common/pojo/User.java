package com.sx.blog.common.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户实体类
 * @author 浩爸爸
 *
 */
@Data
@NoArgsConstructor
public class User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer userId;
	private String  username;
	private String password;
	private String eMail;
	private String icon;
	private Integer gender;
	private String job;
	private String introduce;
	private Integer status;
	private Timestamp createTime;
	private Timestamp updateTime;
	private String tags;
	
    public User(String username, String icon) {
        this.username = username;
        this.icon = icon;
    }
}





