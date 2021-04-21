package com.sx.blog.admin.service;

import java.util.List;

import com.sx.blog.admin.enetity.HotArticle;
import com.sx.blog.admin.enetity.UpdateArticle;
import com.sx.blog.common.pojo.Article;

/**
 * 热门文章业务层接口
 * @author 浩爸爸
 *
 */
public interface IHotArticleService {
	
	/*
	 * 查前三热门文章
	 */
	List<HotArticle> selectHotArticle();
	/*
	 * 查询出文章管理中文章信息
	 */
	Article selectArticle(Integer mapper);
	
	/*
	 * 根据文章id查看文章内容
	 */
	UpdateArticle updateArticle(Integer articleId);
	
}
