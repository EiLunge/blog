package com.sx.blog.sys.service;

import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Timestamp;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.sx.blog.common.pojo.Article;
import com.sx.blog.common.pojo.Comment;
import com.sx.blog.sys.mapper.ArticleMapper;
import com.sx.blog.sys.mapper.CommentMapper;

/**
 * @author 邓宇阳
 * @date 2019年12月31日
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
class CommentServiceTest {

    @Test
    void testGetCommentListById() {
        fail("Not yet implemented");
    }

    @Autowired
    private CommentMapper commentMapper;

    @Test
    void testAddComment() {

    }

    @Autowired
    private ArticleMapper articleMapper;

	/*
	 * @Test void testReplyComment() { Article article =
	 * articleMapper.getArticleById(1); Integer commentNum =
	 * article.getCommentNum(); commentNum += 1;
	 * commentMapper.updateArticleCommentNum(1, commentNum); }
	 */
    @Test
    void testAddLikesComment() {
        fail("Not yet implemented");
    }
	/*
	 * @Test void testDelLikesComment() { Comment comment = new Comment(); Timestamp
	 * t = new Timestamp(new Date().getTime());
	 * comment.setArticleId(1).setUsername("nihao").setIcon("nihao").setCommentText(
	 * "nihao").setUserId(1) .setParentId(0) .setCreateTime(t).setUpdateTime(t);
	 * Integer com = commentMapper.addComment(comment);
	 * System.out.println("---------------------" + com);
	 * System.out.println("---------------------" + comment);
	 * System.out.println("---------------------" + comment.getCommentId()); }
	 */

}
