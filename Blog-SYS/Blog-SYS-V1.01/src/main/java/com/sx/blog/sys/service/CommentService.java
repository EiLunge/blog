package com.sx.blog.sys.service;

import java.util.List;

import com.sx.blog.common.vo.CommentVo;

public interface CommentService {

	List<CommentVo> getCommentListByArticleId(Integer articleId);

    Integer addComment(Integer articleId, String commentText, Integer userId, Integer parentId);

    List<CommentVo> getReplyCommentListByCommentId(Integer parentId);

    void delectComment(Integer commentId);

	void addLikesComment(Integer commentId);

	void delLikesComment(Integer commentId);
	
    Integer getLikeIdByUserIdAndCommentId(Integer userId, Integer commentId);

}
