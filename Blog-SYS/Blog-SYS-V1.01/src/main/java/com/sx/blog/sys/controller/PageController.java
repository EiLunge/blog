package com.sx.blog.sys.controller;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sx.blog.common.pojo.Archiver;
import com.sx.blog.common.pojo.Article;
import com.sx.blog.common.pojo.QueryArticleParam;
import com.sx.blog.common.pojo.Tag;
import com.sx.blog.common.pojo.User;
import com.sx.blog.common.util.CookieUtils;
import com.sx.blog.common.vo.CommentVo;
import com.sx.blog.common.vo.TitleArticleVO;
import com.sx.blog.common.vo.UserVo;
import com.sx.blog.sys.service.ArticleService;
import com.sx.blog.sys.service.IShowService;
import com.sx.blog.sys.service.UserService;

@Controller
public class PageController {
	@Autowired
	UserService userService;
	@Autowired
	IShowService showService;
	@Autowired
	ArticleService articleService;
	
	@RequestMapping("/{page}")
	public String showPage(@PathVariable String page) {
		return "show/"+page;
	}
	@RequestMapping("/")
	public String showHomePage(Model model, HttpServletRequest request, HttpServletResponse response) {
        String userIdStr = CookieUtils.getCookieValue(request, "userId");
        Integer userId = StringUtils.isEmpty(userIdStr) ? null : Integer.valueOf(userIdStr);
        if (userId != null) {
            User user = userService.getUserInfoByUserId(userId);
            user.setPassword("CouDD");
            model.addAttribute("user", user);
        }
        System.out.println("==========进入==============");
        // 声明一个集合用于存储查询到的文章
        List<Article> articleList = showService.getArtilesByParam(new QueryArticleParam(null, null, 0));

        // 声明一个集合用于存储查询到的用户头像
        List<String> iconList = showService.getIconByArticleId(articleList);

        // 声明一个集合用于存储查询到的用户名
        List<String> nameList = showService.getUserNameByUserId(articleList);
        // 文章作者（将用户名与头像合到一起）
        List<User> articleAuthor = new LinkedList<>();
        for (int i = 0; i < iconList.size(); i++) {
            articleAuthor.add(new User(nameList.get(i), iconList.get(i)));
        }

        // 声明一个集合用于存储推荐文章
        List<Article> recommendList = showService.getRecommendArticle();
        List<TitleArticleVO> recommendTitleList = new LinkedList<>();
        for (int i = 0; i < recommendList.size() && i < 4; i++) {
            if (recommendList.get(i) != null)
                recommendTitleList
                    .add(new TitleArticleVO(recommendList.get(i).getTitle(), recommendList.get(i).getArticleId()));
        }
        // 声明一个集合用于存储查询到的热门排行的标题（最近7天）
        List<TitleArticleVO> commentList = showService.getTitleByCommentNum();

        // 声明一个集合用于存储查询到的游览量的标题（最近15天）
        List<TitleArticleVO> browseList = showService.getTitleByBrowse();

        // 声明一个集合用于存储所有的标签
        List<Tag> tagList = showService.getAllTagsWithTag();

        // 除了标签外的文章内容
        model.addAttribute("allArticles", articleList);
        // 文章作者
        model.addAttribute("articleAuthor", articleAuthor);
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
	@RequestMapping("test/otheruserinfo")
	public String test(HttpServletRequest request, HttpServletResponse response, Model model){
		// 从cookie中获取userId
		Integer userId = null;
		String identify;
		// 获取所有cookie
		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 1) {
			for (Cookie cookie : cookies) {
				// System.out.println(cookie.getName() + ":" + cookie.getValue());
				if (cookie.getName().equals("userId")) {
					userId = Integer.valueOf(cookie.getValue());
				}
				if (cookie.getName().equals("identify")) {
					identify = cookie.getValue();
				}
			}
		} else {
			HttpSession session = request.getSession();
			if (session.getAttribute("userId") != null)
				userId = Integer.valueOf((String) session.getAttribute("userId"));
		}
		if (userId != null) {
            User user = userService.getUserInfoByUserId(userId);
            user.setPassword("CouDD");
            model.addAttribute("user", user);
        }
        System.out.println("==========进入==============");
        //声明一个集合用于存储查询到的文章
        List<Article> articleList = showService.getArtilesByParam(new QueryArticleParam(null, null, 0));

        //声明一个集合用于存储查询到的用户头像
        List<String> iconList = showService.getIconByArticleId(articleList);

        //声明一个集合用于存储查询到的用户名
        List<String> nameList = showService.getUserNameByUserId(articleList);

        //声明一个集合用于存储查询到的热门排行的标题
        List<TitleArticleVO> commentList = showService.getTitleByCommentNum();

        //声明一个集合用于存储查询到的游览量的标题
        List<TitleArticleVO> browseList = showService.getTitleByBrowse();

        //声明一个集合用于存储所有的标签
        List<Tag> tagList = showService.getAllTagsWithTag();

        //除了标签外的文章内容
        model.addAttribute("allArticles", articleList);
        //文章对应的用户头像
        model.addAttribute("allIcons", iconList);
        //用户名内容
        model.addAttribute("allNames", nameList);
        //右侧热门排行内容
        model.addAttribute("hotArticles", commentList);
        //右侧游览最多内容
        model.addAttribute("browseArticles", browseList);
        //左侧标签导航内容
        model.addAttribute("tag", tagList);
        
        // 根据文章id查询other用户信息,在查询用户信息
        UserVo userVo = articleService.getUserByArticleId(2);
        model.addAttribute("other", userVo);

        // 根据otherId查询最新的文章列表
        List<Article> userArticles = userService.getArticleByUserId(userVo.getUser().getUserId());
        model.addAttribute("userArticles", userArticles);

        // 根据otherId查询用户最新评论内容
        List<CommentVo> userComments = userService.getCommnetVoByUserId(userVo.getUser().getUserId());
        //System.out.println(userComments);
        model.addAttribute("userComments", userComments);
        //根据otherId查询归档信息
        List<Archiver> archiver=userService.getArchiverByUserId(userVo.getUser().getUserId());
        model.addAttribute("userArchivers", archiver);
		return "users/other_userinfo";
	}


}
