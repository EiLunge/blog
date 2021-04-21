package com.sx.blog.sys.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sx.blog.common.pojo.Article;
import com.sx.blog.common.pojo.Collect;
import com.sx.blog.common.pojo.Follow;
import com.sx.blog.common.pojo.Like;
import com.sx.blog.sys.mapper.ArticleMapper;
import com.sx.blog.sys.mapper.CommentMapper;
import com.sx.blog.sys.mapper.UserPowerMapper;
import com.sx.blog.sys.service.UserPowerService;

/** 
 * @author 邓宇阳
 * @date 2019年12月29日
 */
@Service
public class UserPowerServiceImpl implements UserPowerService {

	@Autowired
	private UserPowerMapper userPowerMapper;
	@Autowired
	private ArticleMapper articleMapper;
    @Autowired
    private CommentMapper commentMapper;
	/**
	 * 实现点赞功能
	 * 1.第一次点赞加入数据库
	 * 2.第二次点赞修改状态
	 * 3.点赞文章点赞量+1
	 */
	@Override
	@Transactional //事务控制
	public Integer addLike(Integer articleId, Integer userId,Integer likeId) {
	     //列表操作取消点赞页面前没有去查likeId,直接传了-1。因此这里重新获取一遍collectId
        if(likeId!=null&&likeId==-1) {
            likeId=userPowerMapper.getLikeIdByArticleIdAndUserId(articleId,userId);
        }
		//0.查询文章点赞数
		Article article= articleMapper.getArticleById(articleId);
		Integer likes = article.getLikes();
		//1.是否已点赞 
		if(likeId==null) {
			//2.1未点赞,->like表插入返回id->点赞数+1
		    Like like=new Like(userId,articleId,null,1);
			userPowerMapper.addLike(like);
			likeId=like.getLikeId();
			likes+=1;
		}else {
			//2.2已点赞-> 取消点赞 删除->点赞数-1
			userPowerMapper.delLikeByLikeId(likeId);
			likeId=null;
			likes-=1;
		}
		//3.修改文章赞数量
		articleMapper.updateLikes(likes,articleId);	
		//4.返回点赞id
	    return likeId;
	}
	/**
     * @param articleId
     * @param userId
     */
    private void getLikeIdByArticleIdAndUserId(Integer articleId, Integer userId) {
         
    }
    /**
	 * 实现用户关注和取消,前端传来followId userId博主id
	 * 当followId为0时,未关注,需要添加关注  
	 * 为其他则取消关注
	 * uid为用户id
	 * userId为粉丝或者大佬id
	 */
	@Override
	@Transactional //事务控制
	public Integer addfollow(Integer isFollowId,Integer uid,Integer userId) {
		Integer followId;
		if(isFollowId!=null&&isFollowId==-1) {
			if(userPowerMapper.selectFollow(userId, uid)!=null) {
				isFollowId=1;
			}else {
				isFollowId=0;
			}
		}
		//1.判断用户是否关注  0未关注
		if(isFollowId==0) {
			//1.1添加关注表
			userPowerMapper.addFollow(userId,uid);
			//1.2添加粉丝表
			userPowerMapper.addFans(userId,uid);
			//1.3返回自增主键
			Follow follow = userPowerMapper.selectFollow(userId, uid);
			followId = follow.getFollowId();
		}else {
			//2.取消关注
			Follow follow = userPowerMapper.selectFollow(userId, uid);
			followId = follow.getFollowId();
			//2.1删除关注表
			userPowerMapper.delFollow(followId);
			//2.2删除粉丝表
			userPowerMapper.delFans(userId,uid);
			followId = 0;
		}
		return followId;
	}
	/**
	   * 添加收藏
	 * @return 
	 */
	@Override
	@Transactional
	public Integer addCollectArticle(Integer articleId, Integer userId,Integer collectId) {
	  //列表操作取消收藏时没有去查collectId,直接传了-1。因此这里重新获取一遍collectId
        if(collectId!=null&&collectId==-1) {
            collectId=userPowerMapper.getCollectIdByArticleIdAndUserId(articleId,userId);
        }
		if(collectId==null) {
		    /*添加收藏*/
		    Collect collect=new Collect();
		    collect.setArticleId(articleId).setUserId(userId);
			userPowerMapper.addCollectArticle(collect);
	        collectId=collect.getCollectId();
		}else {
		    /*取消收藏*/
			userPowerMapper.delCollectArticle(collectId);
			collectId=null;
		}	
		return collectId;
	}
	/**
	 * 博文举报
	 */
	@Override
	@Transactional
	public void addReportArticle(Integer articleId, String msg, Integer userId) {
		userPowerMapper.addReportArticle(articleId,msg,userId);
	}

    /**
     * 评论点赞
     * 
     * @return 1：点赞变红 2：取消点赞变灰
     */
    @Override
    public Integer doCommentLike(Integer commentId, Integer userId) {
        // 传给前端的改变值 1：点赞变红 2：取消点赞变灰
        Integer change = null;
        Integer status = commentMapper.getLikeIdByUserIdAndCommentId(userId, commentId);
        // 点赞
        if (status == null || status == 0) {
            commentMapper.addLikeIdByUserIdAndCommentId(userId, commentId);
            Integer likes = commentMapper.selectCommentById(commentId).getLikes();
            commentMapper.updateCommentLikes(commentId, ++likes);
            change = 1;
        } else {
            // 取消点赞
            commentMapper.delectLikeIdByUserIdAndCommentId(userId, commentId);
            Integer likes = commentMapper.selectCommentById(commentId).getLikes();
            commentMapper.updateCommentLikes(commentId, --likes);
            change = 0;
        }
        return change;
    }


}
