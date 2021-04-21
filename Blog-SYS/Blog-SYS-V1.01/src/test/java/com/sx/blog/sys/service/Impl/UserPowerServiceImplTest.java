package com.sx.blog.sys.service.Impl;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.sx.blog.sys.mapper.ArticleMapper;
import com.sx.blog.sys.mapper.UserPowerMapper;

/**
 * @author 邓宇阳
 * @date 2019年12月30日
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserPowerServiceImplTest {

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private UserPowerMapper userPowerMapper;

    // @Test
    // void testAddLike() {
    // Integer likeId = 50;
    // // 0.查询文章点赞数
    // Article article = articleMapper.getArticleById(42);
    // Integer likes = article.getLikes();
    // // 1.判断是第一次点赞,还是修改状态 0第一次点赞
    // if (likeId == 0) {
    // // 2.插入数据,like表插入
    // userPowerMapper.addLike(42, 1);
    // // 2.2文章点赞数+1
    // likes = likes + 1;
    // } else {
    // // 3.修改当前点赞状态
    // Like like = userPowerMapper.queryLikeByArticleIdAndUserId(42, 1);
    // Integer status = like.getStatus() == 0 ? 1 : 0;
    // likes = status == 1 ? likes + 1 : likes - 1;
    // // 3.2修改点赞状态
    // userPowerMapper.updateLikeStatus(status, like.getLikeId());
    // }
    // // 4.修改文章赞数量
    // articleMapper.updateLikes(likes, 42);
    // }

    @Test
    void testAddfollow() {
        Integer followId = 0;
        Integer uid = 1;
        Integer userId = 3;
        // 1.判断用户是否关注 0未关注
        if (followId == 0) {
            // 1.1添加关注表
            userPowerMapper.addFollow(userId, uid);
            // 1.2添加粉丝表
            userPowerMapper.addFans(userId, uid);
        } else {
            // 2.取消关注
            // 2.1删除关注表
            userPowerMapper.delFollow(followId);
            // 2.2删除粉丝表
            userPowerMapper.delFans(userId, uid);
        }
    }

    @Test
    void testAddCollectArticle() {
        fail("Not yet implemented");
    }

    @Test
    void testAddReportArticle() {
        fail("Not yet implemented");
    }
}
