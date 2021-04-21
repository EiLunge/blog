package com.sx.blog.sys.service;

/** 
* @author 邓宇阳
* @date 2019年12月29日
*/
public interface UserPowerService {

	Integer addLike(Integer articleId, Integer userId,Integer likeId);

	Integer addfollow(Integer followId, Integer userId, Integer uid);

	Integer addCollectArticle(Integer articleId, Integer userId,Integer collectId);

	void addReportArticle(Integer articleId, String msg, Integer userId);

    Integer doCommentLike(Integer commentId, Integer userId);

}
