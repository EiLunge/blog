package com.sx.blog.admin.mapper;

/**
 * 删除博文持久层接口
 * @author 浩爸爸
 *
 */
public interface DeleteArticleMapper {
    /*
     * 通过文章id删除文章
     */
	Integer deleteArticleById(Integer articleId);
	/*
	 * 删除举报表中文章
	 */
	Integer deleteReport(Integer articleId);
}
