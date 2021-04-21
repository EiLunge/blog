/*
 * package com.sx.blog.service;
 * 
 * import java.util.ArrayList; import java.util.List;
 * 
 * import org.junit.jupiter.api.Test; import
 * org.junit.jupiter.api.extension.ExtendWith; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.boot.test.context.SpringBootTest; import
 * org.springframework.test.context.junit.jupiter.SpringExtension;
 * 
 * import com.sx.blog.common.pojo.Article; import
 * com.sx.blog.sys.service.CommentService; import
 * com.sx.blog.sys.service.IShowService;
 * 
 * @ExtendWith(SpringExtension.class)
 * 
 * @SpringBootTest public class ShowServiceImplTests {
 * 
 * @Autowired IShowService showService;
 * 
 * @Autowired private CommentService commentService;
 * 
 * @Test public void getByDescTime() { List<Article> list = new
 * ArrayList<Article>(); // 已删方法，最新查询文章方法为List<Article>
 * getArtilesByParam(QueryArticleParam param); list =
 * showService.getByDescTime(); System.err.println(list); }
 * 
 * @Test public void getArticlesBytagName() { String tagName = "c++";
 * 已删方法，最新查询文章方法为List<Article> getArtilesByParam(QueryArticleParam param);
 * List<Article> list = showService.getArticlesBytagName(tagName); for (Article
 * article : list) { System.out.println(article); } }
 * 
 * @Test public void getCommentListById() { Integer articleId = 18; List list =
 * commentService.getCommentListById(articleId); }
 * 
 * }
 */