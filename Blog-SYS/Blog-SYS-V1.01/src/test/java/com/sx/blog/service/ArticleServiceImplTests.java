package com.sx.blog.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.sx.blog.common.pojo.Article;
import com.sx.blog.common.vo.UserVo;
import com.sx.blog.sys.service.ArticleService;
import com.sx.blog.sys.service.IShowService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ArticleServiceImplTests {

    @Autowired
    ArticleService articleService;

    @Test
    public void getTagIdByTagName() {
    	List<String> tag = new ArrayList<>();
    	tag.add("插件");
    	tag.add("框架");
    	tag.add("人文");
    	System.out.println("tag:"+tag);
    	String a = articleService.getTagIdByTagName(tag);
    	System.out.println("a:"+a);
    }
    
    @Test  //Integer articleId
    public void getUserByArticleId() {
    	UserVo userVo = articleService.getUserByArticleId(20);
    
    }
} 