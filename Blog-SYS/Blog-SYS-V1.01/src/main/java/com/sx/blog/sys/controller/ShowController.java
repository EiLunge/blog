package com.sx.blog.sys.controller;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sx.blog.common.pojo.Article;
import com.sx.blog.common.pojo.QueryArticleParam;
import com.sx.blog.common.pojo.Tag;
import com.sx.blog.common.pojo.User;
import com.sx.blog.common.util.CookieUtils;
import com.sx.blog.common.vo.TitleArticleVO;
import com.sx.blog.sys.service.IShowService;
import com.sx.blog.sys.service.UserService;
/**
 * 用于博文展示的控制器层
 * @author King-LYH
 *
 */
@Controller
@RequestMapping("/")
public class ShowController {

	@Autowired
	private IShowService showService;
	@Autowired
	private UserService userService;

	//关注人博文查看
	@RequestMapping(value = "followList")
    public String getFollowList(HttpServletRequest request, HttpServletResponse response, Model model) {
        //cookie中获取userId
        String  userIdStr =CookieUtils.getCookieValue(request,"userId");
        Integer userId = StringUtils.isEmpty(userIdStr)?null:Integer.valueOf(userIdStr);

		//声明一个集合用于存储查询到的文章
		List<Article> articleList = showService.getFollowArticleByUserId(userId);

		//声明一个集合用于存储查询到的用户名
		List<String> nameList = showService.getUserNameByUserId(articleList);

		//声明一个集合用于存储查询到的用户头像
		List<String> iconList = showService.getIconByArticleId(articleList);

        // 文章作者（将用户名与头像合到一起）
        List<User> articleAuthor = new LinkedList<>();
        for (int i = 0; i < iconList.size(); i++) {
            articleAuthor.add(new User(nameList.get(i), iconList.get(i)));
        }

        // 文章作者
        model.addAttribute("articleAuthor", articleAuthor);
        // 文章内容
        model.addAttribute("allArticles", articleList);
        return "show/article-list";
	}

	//点击左侧推荐，查询推荐文章列表
    @RequestMapping("/recomendarticle")
    public String showRecommendArticle(Model model) {
	    // 声明一个集合用于存储推荐文章
        List<Article> recommendList = showService.getRecommendArticle();
        // 声明一个集合用于存储查询到的用户头像
        List<String> iconList = showService.getIconByArticleId(recommendList);

        // 声明一个集合用于存储查询到的用户名
        List<String> nameList = showService.getUserNameByUserId(recommendList);

        // 除了标签外的文章内容
        model.addAttribute("allArticles", recommendList);
        // 文章作者（将用户名与头像合到一起）
        List<User> articleAuthor = new LinkedList<>();
        for (int i = 0; i < iconList.size(); i++) {
            articleAuthor.add(new User(nameList.get(i), iconList.get(i)));
        }

        // 文章作者
        model.addAttribute("articleAuthor", articleAuthor);
        return "show/article-list";
	}


    // 首页查询文章
    @RequestMapping(value = "search-article")
    public String searchArticle(String title, String tagId, Integer index, Model model) {
        // 文章查询
        List<Article> articleList = showService.getArtilesByParam(new QueryArticleParam(title, tagId, index * 8));
        // 声明一个集合用于存储查询到的用户名
        List<String> nameList = showService.getUserNameByUserId(articleList);

        // 声明一个集合用于存储查询到的用户头像
        List<String> iconList = showService.getIconByArticleId(articleList);

        // 除了标签外的文章内容
        model.addAttribute("allArticles", articleList);
        // 文章作者（将用户名与头像合到一起）
        List<User> articleAuthor = new LinkedList<>();
        for (int i = 0; i < iconList.size(); i++) {
            articleAuthor.add(new User(nameList.get(i), iconList.get(i)));
        }

        // 文章作者
        model.addAttribute("articleAuthor", articleAuthor);
        return "show/article-list";
    }

    // 非首页查询文章（需要跳转到首页）（csdn直接写了一个新页面，我们只做首页文章列表调整）
    @RequestMapping("search")
    public String searchArticle(String q, HttpServletRequest request, HttpServletResponse response, Model model) {
        // cookie中获取userId
        String userIdStr = CookieUtils.getCookieValue(request, "userId");
        Integer userId = StringUtils.isEmpty(userIdStr) ? null : Integer.valueOf(userIdStr);
        if (userId != null) {
            User user = userService.getUserInfoByUserId(userId);
            user.setPassword("CouDD");
            model.addAttribute("user", user);
        }
        // 文章查询
        List<Article> articleList = showService.getArtilesByParam(new QueryArticleParam(q, null, 0));

        // 声明一个集合用于存储查询到的用户名
        List<String> nameList = showService.getUserNameByUserId(articleList);

        // 声明一个集合用于存储查询到的用户头像
        List<String> iconList = showService.getIconByArticleId(articleList);

        // 除了标签外的文章内容
        model.addAttribute("allArticles", articleList);
        // 文章作者（将用户名与头像合到一起）
        List<User> articleAuthor = new LinkedList<>();
        for (int i = 0; i < iconList.size(); i++) {
            articleAuthor.add(new User(nameList.get(i), iconList.get(i)));
        }

        // 文章作者
        model.addAttribute("articleAuthor", articleAuthor);

        // 声明一个集合用于存储推荐文章
        List<Article> recommendList = showService.getRecommendArticle();
        List<TitleArticleVO> recommendTitleList = new LinkedList<>();
        for (int i = 0; i < recommendList.size() && i < 4; i++) {
            if (recommendList.get(i) != null)
                recommendTitleList
                    .add(new TitleArticleVO(recommendList.get(i).getTitle(), recommendList.get(i).getArticleId()));
        }

        // 声明一个集合用于存储查询到的热门排行的标题
        List<TitleArticleVO> commentList = showService.getTitleByCommentNum();

        // 声明一个集合用于存储查询到的游览量的标题
        List<TitleArticleVO> browseList = showService.getTitleByBrowse();

        // 声明一个集合用于存储所有的标签
        List<Tag> tagList = showService.getAllTagsWithTag();
        // 右侧推荐文章
        model.addAttribute("recommendArticleTitle", recommendTitleList);
        // 右侧热门排行内容
        model.addAttribute("hotArticles", commentList);
        // 右侧游览最多内容
        model.addAttribute("browseArticles", browseList);
        // 左侧标签导航内容
        model.addAttribute("tag", tagList);

        // 返回到初始页面
        return "/show/home_page";
    }

}

















