package com.sx.blog.common.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class HotWord implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4719019086572087536L;
	private String wordName;
	private Integer wordSum; 
}
