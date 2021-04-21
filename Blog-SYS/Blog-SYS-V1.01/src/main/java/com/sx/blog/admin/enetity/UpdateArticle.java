package com.sx.blog.admin.enetity;

import java.io.Serializable;

import lombok.Data;

@Data
public class UpdateArticle implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer articleId;
	
	private String pcContent;
}
