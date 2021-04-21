package com.sx.blog.admin.enetity;

import java.io.Serializable;

import lombok.Data;

/**
 * 删除博文实体类
 * @author 浩爸爸
 *
 */
@Data
public class DeleteArticle implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer  articleId;//文章id
	
}
