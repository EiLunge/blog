package com.sx.blog.admin.mapper;

import java.util.List;

import com.sx.blog.admin.enetity.HotArticle;
import com.sx.blog.admin.enetity.UpdateArticle;
import com.sx.blog.common.pojo.Article;

/**
 * 热门文章的持久层接口
 * @author 浩爸爸
 *
 */
public interface HotArticleMapper {
	/*
	 * 查询全部热门文章
	 */
	List<HotArticle> selectHotArticle();
	
	/*
	 * 文章管理中文章内容
	 */
	Article selectHotPcc(Integer articleId);
	
	/*
	 * 查询文章内容
	 */
	UpdateArticle updateArticle(Integer articleId);
}
