package com.sx.blog.sys.mapper;

import java.util.List;

import com.sx.blog.common.pojo.Article;
import com.sx.blog.common.pojo.QueryArticleParam;
import com.sx.blog.common.pojo.Tag;
import com.sx.blog.common.vo.TitleArticleVO;

/**
 * 博文展现的持久层接口
 * @author King-LYH
 */
public interface ShowMapper {
	/**
	 * 写入文章信息
	 */
	Integer insertArticle(Article article);

	/**
	 * 根据标签id查询标签名
	 */
	String findTagNameByTagId(Integer tagId);
	/**
	 * 从标签表中查询所有标签名
	 * @return 标签的集合
	 */
    List<Tag> findAllTagsWithTag();
	
	/**
	 * 通过文章id查询用户id
	 * @param articleId
	 * @return 用户id
	 */
	Integer findUserIdByArticleId(Integer articleId);
	/**
	 * 根据用户id查询用户名
	 * @param userId
	 * @return 查询的用户名
	 */
	String findUserNameByUserId(Integer userId);
	
	/**
	 *  根据文章标题查询相关的文章
	 * @param title
	 * @return 存放查询到文章的集合
	 */
	List<Article> findByTitle(String title);
	
    /**
     * 查询最近7天文章
     * 
     * @return 标题的集合
     */
    List<Article> findArticlesByTime();
	/**
	 * 查询游览量最多的五篇文章标题（15天内）
	 * @return 标题的集合
	 */
	List<TitleArticleVO> findTitleByBrowse();
	/**
	 * 查询文章表中所有的标签
	 * @return 标签的集合
	 */
	List<String> findAllTagId();
	/**
	 * 通过用户id获取关注人的id
	 * @param userId
	 * @return 关注人的id的集合
	 */
	List<Integer> findFollowUserByUserId(Integer userId);
	/**
	 * 通过用户id获取关注人的id=>通过关注人的id获取关注人的博文
	 * @param 
	 * @return 关注人的博文的集合（时间倒序取前八条）    Integer followUser
	 */
	List<Article> findFollowArticleByUserId(Integer userId);
	/**
	 * 通过标签名查询标签id
	 *@param tagName
	 *@return 标签id 
	 */
	Integer findTagIdByTagName(String tagName);

	/**
	 * 根据文章id查询用户头像
	 * @param articleId
	 * @return
	 */
	String findIconByArticleId(Integer articleId);

    /**
     * 通过用户评论查询文章详情
     * 
     * @return List<Article>
     */
    List<Article> findArticleByRecommend();
	
    /**
     * 首页查询文章
     * 
     * @return List<Article>
     */
    List<Article> getArtilesByParam(QueryArticleParam param);
	
	





	
}












