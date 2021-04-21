package com.sx.blog.sys.service.Impl;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.sx.blog.common.pojo.Article;
import com.sx.blog.common.pojo.Comment;
import com.sx.blog.common.pojo.User;
import com.sx.blog.sys.mapper.ArticleMapper;
import com.sx.blog.sys.mapper.CommentMapper;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ArticleServiceImplTest {

    @Autowired
    private ArticleMapper articleMapper;

    @Test
    void testGetArticleById() {
        ArticleServiceImpl s = new ArticleServiceImpl();
        Article art = s.getArticleById(1);
        // UserVo user = s.getUserByArticleId(1);
        System.out.println("art" + art);
        // System.out.println("user"+user);
    }

    @Test
    void testGetUserByArticleId() {
        Article a = articleMapper.getArticleById(1);
        System.out.println(a);
        User suer = articleMapper.getUserById(1);
        System.out.println(suer);
    }

    @Autowired
    private CommentMapper commentMapper;

 

    @Test
    void testBrouse() {
        Article a = articleMapper.getArticleById(1);
        articleMapper.addBrowse(0, 1);
        Article a2 = articleMapper.getArticleById(1);
        System.out.println(a);
        System.out.println(a2);
    }
}
