package com.sx.blog.common.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
@Data
@AllArgsConstructor
@Accessors(chain = true)
public class TitleArticleVO implements Serializable {

	private static final long serialVersionUID = 8279241469404479124L;
	private String title;
	private Integer articleId;
}

