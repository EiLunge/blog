package com.sx.blog.admin.enetity;

import java.io.Serializable;

import lombok.Data;

/**
 * 文章标题实体
 * @author 浩爸爸
 *
 */
@Data
public class Word implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String title;//分词标题
}
