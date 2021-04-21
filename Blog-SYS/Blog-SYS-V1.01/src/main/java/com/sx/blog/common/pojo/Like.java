
package com.sx.blog.common.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain=true)
public class Like {
	private Integer userId;
	private Integer articleId;
	private Integer likeId;
	private Integer status;
}





