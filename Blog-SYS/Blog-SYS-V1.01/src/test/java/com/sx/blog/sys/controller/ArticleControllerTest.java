package com.sx.blog.sys.controller;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.sx.blog.common.pojo.Article;
import com.sx.blog.common.pojo.Tag;
import com.sx.blog.sys.mapper.ArticleMapper;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ArticleControllerTest {

    @Autowired
    private ArticleMapper articleMapper;

    @Test
    void test() {
        fail("Not yet implemented");
    }

    @Test
    void testSelectArticle() {
        Article a = articleMapper.selectArticle(2);
        System.out.println(a);
    }

    @Test
    void testSelectTag() {
        List<Tag> s = articleMapper.selectTagName();
        System.out.println(s);
    }

    @Test
    void testSelectArticleTagName() {
        Tag s = articleMapper.selectArticleTagName(1);
        System.out.println(s);
    }
}
