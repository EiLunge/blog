package com.sx.blog.sys.controller;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sx.blog.common.pojo.Archiver;
import com.sx.blog.common.pojo.Article;
import com.sx.blog.common.pojo.Follow;
import com.sx.blog.common.pojo.Like;
import com.sx.blog.common.pojo.User;
import com.sx.blog.common.util.CookieUtils;
import com.sx.blog.common.vo.CommentVo;
import com.sx.blog.common.vo.TitleArticleVO;
import com.sx.blog.common.vo.UserVo;
import com.sx.blog.sys.mapper.UserPowerMapper;
import com.sx.blog.sys.service.ArticleService;
import com.sx.blog.sys.service.CommentService;
import com.sx.blog.sys.service.IShowService;
import com.sx.blog.sys.service.UserService;

@Controller
@RequestMapping("user/article")
public class UserArticleController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserPowerMapper userPowerMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private IShowService showService;

    /**
     * 根据文章id查询详情
     * 
     * @param articleId
     *            文章id
     * @return 1.显示文章信息 2.显示文章作者信息 3.显示评论信息 4.增加点击量
     */
    @RequestMapping("/detail/{articleId}")
    public String articleDeteil(@PathVariable Integer articleId, Model model, HttpServletRequest request,
        HttpServletResponse response) {
        // 根据文章id查询文章,并且增加点击量
        Article article = articleService.getArticleById(articleId);
        String content = article.getPcContent();
        article.setPcContent(/*MDTool.markdown2Html(content)*/content);
        model.addAttribute("article", article);

        // 声明一个集合用于存储查询到的热门排行的标题
        List<TitleArticleVO> hotList = showService.getTitleByCommentNum();
        // 右侧热门排行内容
        model.addAttribute("hotArticles", hotList);

        // 声明一个集合用于存储推荐文章
        List<Article> recommendList = showService.getRecommendArticle();
        List<TitleArticleVO> recommendTitleList = new LinkedList<>();
        for (int i = 0; i < recommendList.size() && i < 4; i++) {
            if (recommendList.get(i) != null)
                recommendTitleList
                    .add(new TitleArticleVO(recommendList.get(i).getTitle(), recommendList.get(i).getArticleId()));
        }
        // 右侧推荐文章
        model.addAttribute("recommendArticleTitle", recommendTitleList);


        // 声明一个集合用于存储查询到的游览量的标题
        List<TitleArticleVO> browseList = showService.getTitleByBrowse();
        // 右侧游览最多内容
        model.addAttribute("browseArticles", browseList);

        // 根据文章id查询other.username
        String username = showService.getUsernameByArticleId(articleId);
        model.addAttribute("username", username);

        // 根据文章id查询other用户信息,在查询用户信息
        UserVo userVo = articleService.getUserByArticleId(articleId);
        model.addAttribute("other", userVo);

        // 根据otherId查询最新的文章列表
        List<Article> userArticles = userService.getArticleByUserId(userVo.getUser().getUserId());
        model.addAttribute("userArticles", userArticles);

        // 根据otherId查询用户最新评论内容
        List<CommentVo> userComments = userService.getCommnetVoByUserId(userVo.getUser().getUserId());
        model.addAttribute("userComments", userComments);
        // 根据otherId查询归档信息
        List<Archiver> archiver = userService.getArchiverByUserId(userVo.getUser().getUserId());
        model.addAttribute("userArchivers", archiver);

        // 查询全部评论信息,
        List<CommentVo> articleComments = commentService.getCommentListByArticleId(articleId);
        model.addAttribute("articleComments", articleComments);


        /////////////////////////////////////////////// 不需要用户id（当前+博主）的方法写这上面//////////////////////////////////////////
        // 获取当前登陆的用户信息
        //cookie中获取userId
        String  userIdStr =CookieUtils.getCookieValue(request,"userId");
        Integer userId = StringUtils.isEmpty(userIdStr)?null:Integer.valueOf(userIdStr);
        model.addAttribute("userId", userId);
        if (userId != null) {
            User user = userService.getUserInfoByUserId(userId);
            if (!StringUtils.isEmpty(user)) {
                user.setPassword("CouDD");
            }
            model.addAttribute("user", user);

            // 判断点赞标识
            Like like = userPowerMapper.getLikeByArticleIdAndUserId(articleId, userId);
            Integer likeId =null;
            if (!StringUtils.isEmpty(like))
            likeId = like.getLikeId();
            model.addAttribute("likeId",likeId);

            // 判断关注标识,userId是当前用户id
            Follow follow = userPowerMapper.selectFollow(article.getUserId(), userId);
            Integer followId = null;
            if (!StringUtils.isEmpty(follow))
                followId = follow.getFollowId();
            model.addAttribute("followId", followId);

            // 判断收藏标识
            Integer collectId = userPowerMapper.getCollectIdByArticleIdAndUserId(articleId,userId);
            model.addAttribute("collectId", collectId);

            // 一级评论默认点赞标识
            List<Integer> likeCommentIdList = new LinkedList<>();
            for (CommentVo comment : articleComments) {
                Integer currentCommentId = comment.getComment().getCommentId();
                Integer id = commentService.getLikeIdByUserIdAndCommentId(userId, currentCommentId);
                if (id != null) {
                    likeCommentIdList.add(currentCommentId);
                }
            }
            model.addAttribute("likeCommentIdList", likeCommentIdList);

        }
        return "/article/article_detail_new";
    }

}
