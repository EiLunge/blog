package com.sx.blog.admin.enetity;

import java.io.Serializable;

import lombok.Data;

/**
 * 最后文章实体
 * @author 浩爸爸
 *
 */
@Data
public class HotArticle implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer likes;//点赞数
	
	private String title;//文章标题
	
	
	
}
