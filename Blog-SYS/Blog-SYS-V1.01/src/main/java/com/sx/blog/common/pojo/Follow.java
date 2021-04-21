
package com.sx.blog.common.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
public class Follow {
	private Integer followId;
	private Integer userId;
	private Integer followUser;
	private Integer status;
}




