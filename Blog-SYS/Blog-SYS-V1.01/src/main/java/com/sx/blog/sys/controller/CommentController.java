package com.sx.blog.sys.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.sx.blog.common.SysResult;
import com.sx.blog.common.pojo.Comment;
import com.sx.blog.common.util.CookieUtils;
import com.sx.blog.common.vo.CommentVo;
import com.sx.blog.sys.service.CommentService;

/** 
 * @author 邓宇阳
 * @date 2019年12月29日
 * 博文评论
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

	@Autowired
	private CommentService commentService;
	
    /**
     * 发布评论
     */
	@RequestMapping("")
    @ResponseBody
    public SysResult comment(Integer articleId, String commentText, Integer userId, Integer parentId, Model model) {
        if (commentText.length() > 150) {
            return SysResult.fail("请输入150个字以下的评论");
		}
        Integer NewCommentId = commentService.addComment(articleId, commentText, userId, parentId);
        if (NewCommentId != 0) {
            return SysResult.success(NewCommentId);
        } else {
            return SysResult.fail(500, "服务器错误，评论失败");
        }
    }

    /**
     * 评论后展示新评论
     */
    @RequestMapping("show-new-comment")
    public ModelAndView showNewComment(Integer parentId, Integer articleId, String commentText, Integer userId,
        Integer commentId, String username, String userIcon, Model model) {
        CommentVo commentVo = new CommentVo()
            .setComment(new Comment().setArticleId(articleId).setCommentText(commentText).setUserId(userId)
                .setCreateTime(new Timestamp(new Date().getTime())).setParentId(parentId).setCommentId(commentId))
            .setUsername(username).setUserIcon(userIcon).setSonCommentNum(0);

        model.addAttribute("newComment", commentVo);
        model.addAttribute("userId", userId);
        ModelAndView a = new ModelAndView("/article/new_comment");
        return a;
    }

    /**
     * 评论子评论后刷新评论区
     */
    @RequestMapping("refresh-comment")
    public ModelAndView refreshComment(Integer articleId, Integer userId, Model model) {
        // 查询全部评论信息,
        List<CommentVo> articleComments = commentService.getCommentListByArticleId(articleId);
        model.addAttribute("articleComments", articleComments);
        // 一级评论默认点赞标识
        List<Integer> likeCommentIdList = new LinkedList<>();
        for (CommentVo comment : articleComments) {
            Integer currentCommentId = comment.getComment().getCommentId();
            Integer id = commentService.getLikeIdByUserIdAndCommentId(userId, currentCommentId);
            if (id != null) {
                likeCommentIdList.add(currentCommentId);
            }
        }
        model.addAttribute("userId", userId);
        model.addAttribute("likeCommentIdList", likeCommentIdList);
        ModelAndView a = new ModelAndView("/article/comment_list");
        return a;
    }

    /**
     * 查询多级评论(回复)
     */
    @RequestMapping("show-reply-comment")
    public ModelAndView showReplyComment(Integer commentId, Model model, HttpServletRequest request) {
        List<CommentVo> replyCommentVoList = commentService.getReplyCommentListByCommentId(commentId);
        model.addAttribute("replyComment", replyCommentVoList);
        // 从cookie中获取userId
        String userIdStr = CookieUtils.getCookieValue(request, "userId");
        Integer userId2 = StringUtils.isEmpty(userIdStr) ? null : Integer.valueOf(userIdStr);
        model.addAttribute("userId", userId2);
        // 多级评论默认点赞标识
        List<Integer> moreLikeCommmentIdList = new LinkedList<>();
        for (CommentVo comment : replyCommentVoList) {
            Integer currentCommentId = comment.getComment().getCommentId();
            Integer id = commentService.getLikeIdByUserIdAndCommentId(userId2, currentCommentId);
            if (id != null) {
                moreLikeCommmentIdList.add(currentCommentId);
            }
        }
        model.addAttribute("moreLikeCommmentIdList", moreLikeCommmentIdList);
        ModelAndView a = new ModelAndView("/article/reply_comment_list");
        return a;
    }

    /**
     * 删除评论
     */
    @RequestMapping("delect-comment")
    @ResponseBody
    public SysResult delectComment(Integer commentId) {
        commentService.delectComment(commentId);
        return SysResult.success("删除成功");
    }

}
