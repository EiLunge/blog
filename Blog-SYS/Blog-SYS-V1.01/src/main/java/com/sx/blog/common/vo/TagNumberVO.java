package com.sx.blog.common.vo;

import java.io.Serializable;

import lombok.Data;
@Data
public class TagNumberVO implements Serializable {

	private static final long serialVersionUID = -8462990499285752802L;
	
	private String tagName;
	private Integer tagSum;
}
