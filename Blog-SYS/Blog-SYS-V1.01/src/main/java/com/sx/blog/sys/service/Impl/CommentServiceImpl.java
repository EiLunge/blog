package com.sx.blog.sys.service.Impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sx.blog.common.pojo.Comment;
import com.sx.blog.common.pojo.User;
import com.sx.blog.common.vo.CommentVo;
import com.sx.blog.sys.mapper.ArticleMapper;
import com.sx.blog.sys.mapper.CommentMapper;
import com.sx.blog.sys.mapper.UserMapper;
import com.sx.blog.sys.service.CommentService;

/** 
 * @author 邓宇阳: 
 * @date 创建时间：2019年12月28日 
 * @version d18379690589@163.com
 * 评论实体类
 */
@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentMapper commentMapper;
	@Autowired
	private ArticleMapper articleMapper;
    @Autowired
    private UserMapper userMapper;
	/**
	 * 根据文章id查询评论信息,根据likes数量排序
	 * 1.在comment集合中添加用户信息和用户头像信息
	 * 2.每条评论下找到他的子评选
	 */
	@Override
	public List<CommentVo> getCommentListByArticleId(Integer articleId) {
        // 1.查询所有一级评论
        List<Comment> commentList = commentMapper.getCommentListByArticleId(articleId);
		List<CommentVo> commentVoList = new LinkedList<CommentVo>();
		//2.填充commentVo
        //comment username userIcon sonCommentNum
		for (Comment comment : commentList) {
			CommentVo commentVo = new CommentVo();
			User user=userMapper.getUserByUserId(comment.getUserId());
            Integer sonCommentNum = commentMapper.getReplyNum(comment.getCommentId());
			commentVoList.add(commentVo.setComment(comment).setUsername(user.getUsername()).setUserIcon(user.getIcon()).setSonCommentNum(sonCommentNum));
		}
        // 一级评论的集合
        return commentVoList;
	}

    // 显示回复列表
    @Override
    public List<CommentVo> getReplyCommentListByCommentId(Integer parentId) {
        List<Comment> ReplyCommentList = queryReplyCommet(new LinkedList<>(), parentId);
        // 2.填充
        List<CommentVo> ReplyCommentVoList = new LinkedList<CommentVo>();
        for (Comment ReplyComment : ReplyCommentList) {
            CommentVo commentVo = new CommentVo();
            User user = userMapper.getUserByUserId(ReplyComment.getUserId());
            Integer sonCommentNum = commentMapper.getReplyNum(ReplyComment.getCommentId());
            String parentName =
                userMapper.getUserByUserId(commentMapper.getCommmentByCommentId(ReplyComment.getParentId()).getUserId())
                    .getUsername();
                ReplyCommentVoList.add(commentVo.setComment(ReplyComment).setUsername(user.getUsername())
                .setUserIcon(user.getIcon()).setSonCommentNum(sonCommentNum).setParentName(parentName));
        }
        return ReplyCommentVoList;
    }


	/**
     * 添加评论
     */
	@Override
	@Transactional //事务控制
    public Integer addComment(Integer articleId, String commentText, Integer userId, Integer parentId) {
        Comment comment = new Comment();
        Integer firstLevelCommentId = null;
        // 不是一级评论
        if (parentId != null) {
            // 二级评论=parentId
            firstLevelCommentId = commentMapper.getFirstLevelCommentIdByCommentId(parentId);
            if (firstLevelCommentId == null) {
                firstLevelCommentId = parentId;
            }
            // >=3级评论= firstLevelCommentId
        }
        comment.setArticleId(articleId).setCommentText(commentText).setUserId(userId)
            .setCreateTime(new Timestamp(new Date().getTime())).setParentId(parentId)
            .setFirstLevelCommentId(firstLevelCommentId);
        commentMapper.addComment(comment);
        return comment.getCommentId();
	}

    /**
     * 删除评论
     */
    @Override
    public void delectComment(Integer commentId) {
        delectReplyComment(commentId);
    }

    /**
     * 查询用户与评论间的点赞关系
     */
    @Override
    public Integer getLikeIdByUserIdAndCommentId(Integer userId, Integer commentId) {
        Integer commentLikeId = commentMapper.getLikeIdByUserIdAndCommentId(userId, commentId);
        return commentLikeId;
    }

	/**
	 * 评论点赞
	 */
	@Override
	public void addLikesComment(Integer commentId) {
		//1.根据commentId查询评论
		Comment comment = commentMapper.selectCommentById(commentId);
		Integer likes = comment.getLikes();
		likes += 1;
		commentMapper.updateCommentLikes(commentId,likes);
	}

	/**
	 * 评论取消赞
	 */
	@Override
	public void delLikesComment(Integer commentId) {
		//1.根据commentId查询评论
		Comment comment = commentMapper.selectCommentById(commentId);
		Integer likes = comment.getLikes();
		likes -= 1;
		commentMapper.updateCommentLikes(commentId,likes);

	}

    // 递归查询 回复评论
    private List<Comment> queryReplyCommet(List<Comment> replyComment, Integer pid) {
        List<Comment> replyCommentList = commentMapper.getReplyCommentListByCommentId(pid);
        for (int i = 0; i < replyCommentList.size(); i++) {
            replyComment.add(replyCommentList.get(i));
            int cid = replyCommentList.get(i).getCommentId();// cid充当pid
            queryReplyCommet(replyComment, cid);
        }
        return replyComment;
    }

    // 递归删除
    private void delectReplyComment(Integer pid) {
        List<Comment> replyCommentList = commentMapper.getReplyCommentListByCommentId(pid);
        commentMapper.delectCommentByCommentId(pid);
        for (int i = 0; i < replyCommentList.size(); i++) {
            commentMapper.delectCommentByCommentId(replyCommentList.get(i).getCommentId());
            int cid = replyCommentList.get(i).getCommentId();// cid充当pid
            delectReplyComment(cid);
        }
    }



}
