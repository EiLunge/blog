package com.sx.blog.admin.enetity;

import java.io.Serializable;

import lombok.Data;
/**
 * 文章实体
 * @author 浩爸爸
 *
 */
@Data
public class Article implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String title;//文章标题
	
	private String  pcContent;//内容
	
}
