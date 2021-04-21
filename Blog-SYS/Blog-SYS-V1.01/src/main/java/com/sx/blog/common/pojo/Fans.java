
package com.sx.blog.common.pojo;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;
@Data
@Accessors(chain=true)
public class Fans implements Serializable{

	private static final long serialVersionUID = -6420401111238031655L;

	private Integer fansId;  //主键
	private Integer userId;  //用户id
	private Integer  userFans;//粉丝id
	private Integer  status;  //是否相互关注  0 未关注   1已关注

}



