
package com.sx.blog.common.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class Collect {
	private Integer collectId;
	private Integer userId;
	private Integer articleId;
}



