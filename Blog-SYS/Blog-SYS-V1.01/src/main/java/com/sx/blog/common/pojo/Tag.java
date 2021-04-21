package com.sx.blog.common.pojo;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;
@Data
@Accessors(chain=true)
public class Tag implements Serializable{

	private static final long serialVersionUID = 1500352874472511821L;
	
	private Integer tagId;  //标签id
	private String tagName; //标签名
}


