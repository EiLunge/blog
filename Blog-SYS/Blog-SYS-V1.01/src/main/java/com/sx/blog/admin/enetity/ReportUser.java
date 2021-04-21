package com.sx.blog.admin.enetity;

import java.io.Serializable;

import lombok.Data;

/**
 * 举报实体类
 * @author 浩爸爸
 *
 */
@Data
public class ReportUser implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String msg;//举报信息
	
	private String username;//用户名
	
	private String title;//文章标题
	
	private Integer articleId;//文章id
	
	
	
	
	
}
