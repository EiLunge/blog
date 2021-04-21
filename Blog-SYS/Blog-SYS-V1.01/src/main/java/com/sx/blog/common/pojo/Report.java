
package com.sx.blog.common.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
public class Report {
	private Integer reportId;
	private Integer userId;
	private Integer articleId;
	private String msg;
}






