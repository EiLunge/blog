package com.sx.blog.common.vo;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sx.blog.common.pojo.Article;
import com.sx.blog.common.pojo.Tag;

import lombok.Data;
import lombok.experimental.Accessors;

/**
   * 返回文章草稿类
 * @author 邓宇阳
 *
 */
@Data
@JsonIgnoreProperties(ignoreUnknown=true) //表示JSON转化时忽略未知属性
@Accessors(chain=true)
public class ArticleDraftVo {

	private Article article;
	private List<Tag> tag;
	private List<Tag> tagArticle; //草稿回显分类标签信息
}
