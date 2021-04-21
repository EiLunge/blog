package com.sx.blog.admin.enetity;

import java.io.Serializable;

import lombok.Data;

/**
 * 标签实体类
 * @author 浩爸爸
 *
 */
@Data
public class Tag implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String tagName;
	
	private Integer tagId;
		
}
