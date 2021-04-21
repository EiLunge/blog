package com.sx.blog.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sx.blog.common.SysResult;
import com.sx.blog.sys.service.UserPowerService;

/**
 * @author 邓宇阳
 * @date 2019年12月29日 用户登录后的一些权限,点赞.关注,举报,收藏
 */
@RequestMapping("/user/power")
@RestController
public class UserPowercontroller {

    @Autowired
    private UserPowerService userPowerService;

    /**
     * 实现点赞功能 1.判断当前用户是否点赞,根据前端传来的likeId判断 2.点赞可以取消赞 3.文章内点赞数+1 -1
     */
    @RequestMapping("/likes")
    public SysResult likesArticle(Integer articleId, Integer likeId, Integer userId) {
        Integer likeId2 = userPowerService.addLike(articleId, userId, likeId);
        return SysResult.success(likeId2);
    }

    /**
     * 实现用户关注和取消,前端传来followId 0未关注,添加关注 其他数据取消关注 userId 博主id
     */
    @RequestMapping("/follow")
    public SysResult follow(Integer isFollowId, Integer uid, Integer otherId) {
        if (uid == otherId) {
            return SysResult.fail("不能关注自己");
        }
        if (isFollowId == null) {
            isFollowId = -1;
        }
        Integer follow = userPowerService.addfollow(isFollowId, uid, otherId);
        // follow=0 取消用户 其他添加关注成功 关注用户
        return SysResult.success(follow);
    }

    /**
     * 实现博文收藏
     */
    @RequestMapping("collect")
    public SysResult collectArticle(Integer articleId, Integer collectId, Integer userId, Model model) {
        Integer collectId2 = userPowerService.addCollectArticle(articleId, userId, collectId);
        return SysResult.success(collectId2);
    }

    /**
     * 用户举报博文
     */
    @RequestMapping("report")
    public SysResult report(Integer articleId, String reportMsg, Integer userId) {
        if (reportMsg.length() > 50) {
            return SysResult.fail("举报内容过长!");
        }
        userPowerService.addReportArticle(articleId, reportMsg, userId);
        return SysResult.success();
    }
    /**
     * 用户点赞评论/取消评论
     */
    @RequestMapping("do-comment-like")
    @ResponseBody
    public SysResult doCommentLike(Integer commentId, Integer userId) {
        // 传给前端的改变值 1：点赞变红 2：取消点赞变灰
        Integer change = userPowerService.doCommentLike(commentId, userId);
        return SysResult.success(change);
    }
}
