package com.sx.blog.mapper;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.sx.blog.common.pojo.Article;
import com.sx.blog.common.pojo.Comment;
import com.sx.blog.common.vo.TitleArticleVO;
import com.sx.blog.sys.mapper.CommentMapper;
import com.sx.blog.sys.mapper.ShowMapper;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ShowMapperTests {

    @Autowired
    ShowMapper showMapper;
    @Autowired
    CommentMapper commentMapper;

	/*
	 * @Test public void getCommentListByCommentId() { Integer commentId = 4;
	 * List<Comment> list = commentMapper.getCommentListByCommentId(commentId);
	 * System.out.println("list:" + list); }
	 * 
	 * @Test public void findUsernameByCommentId() { Integer commentId = 4; String
	 * name = commentMapper.findUsernameByCommentId(commentId);
	 * System.out.println(name); }
	 */
    /**
     * 写入文章信息
     */
    @Test
    public void insertArticle() {
        Article article = new Article();
        article.setUserId(4);
        article.setTagId("10,11");
        article.setTitle("大河之剑天上来");
        article.setPcContent("哈哈哈哈哈哈哈哈哈哈哈");
        article.setLikes(0);
        article.setCommentNum(0);
        article.setStatus(1);
        article.setBrowse(0);
        Timestamp t = new Timestamp(new Date().getTime());
        System.err.println("t:" + t);
        article.setCreateTime(t);
        Integer row = showMapper.insertArticle(article);
        System.err.println("row:" + row);
    }

    // List<Article> findByDescTime();
    @Test
    public void findByDescTime() {
        // 已删方法，最新查询文章方法为List<Article> getArtilesByParam(QueryArticleParam param); List lists =
        // showMapper.findByDescTime();
        // System.err.println("lists:" + lists);
    }

    // String findTagNameByTagId(Integer tagId);
    @Test
    public void findTagNameByTagId() {
        String tagName = showMapper.findTagNameByTagId(1);
        System.out.println("tagName:" + tagName);
    }

    // 根据用户id查询用户名 String findUserNameByUserId(Integer userId)
    @Test
    public void findUserNameByUserId() {
        Integer userId = 1;
        String username = showMapper.findUserNameByUserId(userId);
        System.out.println("username:" + username);
    }

    // 根据文章标题查询相关的文章 List<Article> findByTitle(String title)
    @Test
    public void findByTitle() {
        String keyword = "是否有";
        String title = "%" + keyword + "%";
        List<Article> list = showMapper.findByTitle(title);
        System.err.println("list:" + list);
    }

    // 查询评论最多的五篇文章的标题 List<String> findTitleByCommentNum()
	/*
	 * @Test public void findTitleByCommentNum() { List<TitleArticleVO> list =
	 * showMapper.findTitleByCommentNum(); System.err.println("list:" + list); }
	 */

    // 查询游览量最多的五篇文章标题（15天内）List<String> findTitleByBrowse()
    @Test
    public void findTitleByBrowse() {
        List<TitleArticleVO> list = showMapper.findTitleByBrowse();
        System.err.println("list:" + list);
    }

    // 查询文章表中所有的标签 List<String> findAllTagId()
    @Test
    public void findAllTagId() {
        List<String> list = showMapper.findAllTagId();
        for (String tagId : list) {
            System.out.println("tagId:" + tagId);
        }
        System.out.println("list:" + list);
    }

    // 通过用户id获取关注人的id List<Integer> findFollowUserByUserId(Integer userId)
    @Test
    public void findFollowUserByUserId() {
        Integer userId = 1;
        List<Integer> list = showMapper.findFollowUserByUserId(userId);
        System.out.println(list);
    }

    @Test
    public void findFollowArticleByFollowUser() {
        List<Article> articles = showMapper.findFollowArticleByUserId(1);
        for (Article article : articles) {
            System.err.println("article:" + article);
        }
    }

    // <!-- 通过标签名查询标签id Integer findTagIdByTagName(String tagName) -->
    @Test
    public void findTagIdByTagName() {
        Integer tagId = showMapper.findTagIdByTagName("java");
        System.out.println("tagId:" + tagId);
    }

    // <!-- 查询所有文章 List<Article> findAllArticles() -->
    @Test
    public void findAllArticles() {
        /*  已删方法，最新查询文章方法为List<Article> getArtilesByParam(QueryArticleParam param);       List<Article> list = showMapper.findAllArticles();
        for (Article article : list) {
            System.out.println(article);
        }*/
    }
    
    //<!-- 从标签表中查询所有标签名 List<String> findAllTagsWithTag();  -->
    @Test
    public void findAllTagsWithTag() {
        /*  已删方法，最新查询文章方法为List<Article> getArtilesByParam(QueryArticleParam param);           List<String> list = showMapper.findAllTagsWithTag();
        System.out.println(list);*/
       
    }
    
    // 根据文章id查询用户头像 List<String> findIconByArticleId(Integer articleId); 
    @Test
    public void findIconByArticleId() {
        String list = showMapper.findIconByArticleId(11);
        System.out.println(list);
       
    }
    
}










