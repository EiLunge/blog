package com.sx.blog.sys.service;

import java.util.List;

import com.sx.blog.common.pojo.Article;
import com.sx.blog.common.pojo.QueryArticleParam;
import com.sx.blog.common.pojo.Tag;
import com.sx.blog.common.vo.TitleArticleVO;


/**
 * 博文展现的业务层接口
 * @author King-LYH
 *
 */
public interface IShowService {

    /**
     * 分页查询文章
     * 
     * @param 参数对象
     *            QueryArticleParam
     * @return 文章列表
     */
    List<Article> getArtilesByParam(QueryArticleParam param);
	
	/**
	 * 根据文章集合查询查询标签名集合
	 * @param tagId
	 * @return tagName
	 */
	List<List<String>> getTagNameByTagId(List<Article> list);
	
	/**
	 * 根据文章集合查询用户名的集合
	 * @return 查询的用户名的集合
	 */
	List<String> getUserNameByUserId(List<Article> list);
	
	/**
	 * 从标签表中查询所有标签名
	 * @return 标签的集合
	 */
    List<Tag> getAllTagsWithTag();
	

	/**
	   * 查询评论最多的五篇文章的标题
	   * @return 标题的集合
	   */
    List<TitleArticleVO> getTitleByCommentNum();
	/**
	 * 查询游览量最多的五篇文章标题（15天内）
	 * @return 标题的集合
	 */
	List<TitleArticleVO> getTitleByBrowse();
	/**
	 * 通过用户id获取关注人博文
	 * @param 
	 * @return 关注人的博文的集合（时间倒序取前八条）   
	 */
	List<Article> getFollowArticleByUserId(Integer userId);

	/**
	 * 根据文章id查询用户头像
	 * @param articleId
	 * @return
	 */
	List<String> getIconByArticleId(List<Article> list);
	/**
	 * 根据文章id查询用户名
	 */
	String getUsernameByArticleId(Integer articleId);

    /**
     * 查询被推荐文章
     * 
     * @return list
     */
    List<Article> getRecommendArticle();
	
}













