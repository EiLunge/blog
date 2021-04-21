package com.sx.blog.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sx.blog.common.pojo.Comment;

/** 
* @author 邓宇阳
* @date 2019年12月28日
*/
public interface CommentMapper {
    // 根据文章id查一级评论列表
    @Select("SELECT * FROM public.comment WHERE public.comment.article_id =  #{articleId} AND public.comment.parent_id is null order by public.comment.likes desc,public.comment.create_time desc ")
    List<Comment> getCommentListByArticleId(Integer articleId);

    // 查询回复评论数(通过一级评论id查询)
    @Select("SELECT count(*) FROM public.comment WHERE public.comment.first_level_comment_id = #{commentId}")
    Integer getReplyNum(Integer commentId);
    
    // 根据userid查询评论列表
    List<Comment> getCommentListByUserId(Integer userId);

    // 查询回复评论
    @Select("select * from public.comment where parent_id=#{parentId} order by create_time desc")
    List<Comment> getReplyCommentListByCommentId(Integer parentId);

	//用户评论博文
	int addComment(Comment comment);

    // 根据评论id查询评论详情
    @Select("select * from public.comment where comment_id=#{commentId} ")
    Comment getCommmentByCommentId(Integer commentId);

    // 删除评论
    @Delete("DELETE FROM public.comment WHERE comment_id=#{commentId}")
    void delectCommentByCommentId(Integer commentId);

    // 查询用户与评论之间的点赞关系
    Integer getLikeIdByUserIdAndCommentId(@Param("userId") Integer userId, @Param("commentId") Integer commentId);

    // 删除评论点赞
    void delectLikeIdByUserIdAndCommentId(@Param("userId") Integer userId, @Param("commentId") Integer commentId);

    // 点赞
    void addLikeIdByUserIdAndCommentId(@Param("userId") Integer userId, @Param("commentId") Integer commentId);

    // 根据commentId查询评论详情
    @Select("select * from public.comment where comment_id=#{commentId}")
    Comment selectCommentById(Integer commentId);

    // 修改点赞数量
    @Update("update public.comment set likes=#{likes} where comment_id=#{commentId}")
    void updateCommentLikes(@Param("commentId") Integer commentId, @Param("likes") Integer likes);

    // 查询一级评论id
    @Select("select public.comment.first_level_comment_id from public.comment where comment_id=#{commentId}")
    Integer getFirstLevelCommentIdByCommentId(@Param("commentId") Integer commentId);

    // 根据文章id查评论总数
    @Select("SELECT count(*) FROM public.comment WHERE public.comment.article_id = #{articleId}")
    Integer getCommentsNumByArticleId(@Param("articleId") Integer articleId);

    // 根据文章id删除评论
    @Delete("delete from public.comment WHERE public.comment.article_id = #{articleId}")
    Integer delCommentsByArticleId(@Param("articleId") Integer articleId);
	
}
