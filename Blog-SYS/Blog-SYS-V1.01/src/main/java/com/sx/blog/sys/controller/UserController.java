package com.sx.blog.sys.controller;

import java.io.File;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sx.blog.common.SysResult;
import com.sx.blog.common.pojo.Archiver;
import com.sx.blog.common.pojo.Article;
import com.sx.blog.common.pojo.User;
import com.sx.blog.common.util.CookieUtils;
import com.sx.blog.common.util.MyMD5Util;
import com.sx.blog.common.vo.CommentVo;
import com.sx.blog.common.vo.UserVo;
import com.sx.blog.sys.service.ArticleService;
import com.sx.blog.sys.service.IShowService;
import com.sx.blog.sys.service.UserService;

/**
 * 
 * @author 李金鑫
 *
 */
@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private IShowService showService;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private ArticleService articleService;

    // 通用跳转页面
    @RequestMapping("/{page}")
    public String showPage(@PathVariable String page) {
        return "/users/" + page;
    }

    // 用户登录
    @RequestMapping("/login/dologin")
    @ResponseBody
    public SysResult doLogin(String name, String pwd, String rem, Model model, HttpServletRequest request,
        HttpServletResponse response) {
        HashMap result = userService.checkLogin(name, pwd);
        if (result == null) {
            return SysResult.fail("用户名或密码错误");
        }
        String userIdStr = String.valueOf(result.get("userId"));
        if (userIdStr != null && (Integer)result.get("status") == 0 && rem != null) {
            // 设置userId 的cookie +时间+路径
            CookieUtils.setCookie(request, response, "userId", userIdStr, 3600 * 24);// 这个方法默认访问路径为'/'
            return SysResult.success("登入成功");
        } else if (userIdStr != null && (Integer)result.get("status") == 0) {
            // 设置userId 的cookie +时间+路径
            CookieUtils.setCookie(request, response, "userId", userIdStr);
            return SysResult.success("登 入成功");
        } else if (userIdStr != null && (Integer)result.get("status") == 1) {
            return SysResult.fail(400, "用户被禁用");
        }
        return SysResult.fail(400, "用户名或密码错误");
    }

    // 注册判断邮箱是否已注册
    @RequestMapping("/regist/check")
    @ResponseBody
    public SysResult checkParam(String username, String email) {
        if (username != null) {
            // 根据username查询用户，存在则提示用户名已注册 true 可用
            Boolean isUsername = userService.checkUsername(username);
            if (!isUsername) {
                return SysResult.fail("用户名被使用");
            }
        } else if (email != null) {
            // 根据email查询status，已激活（0），则提示邮箱已注册 true 可用
            Boolean isEmail = userService.checkEMail(email);
            if (!isEmail) {
                return SysResult.fail("邮箱已注册");
            }
        }
        return SysResult.success();
    }

    // 注册
    @RequestMapping("/regist/doregist")
    @ResponseBody
    public SysResult doregist(String name, String email, String pwd, String repwd, HttpServletRequest request,
        HttpServletResponse response) {
        User newUser = new User();
        Boolean isSuccess = false;
        if (name != null && email != null && pwd != null) {
            newUser.setUsername(name);
            newUser.setPassword(MyMD5Util.md5password(pwd));
            newUser.setEMail(email);
            newUser.setStatus(1);
            newUser.setCreateTime(new Timestamp(new Date().getTime()));
            newUser.setUpdateTime(new Timestamp(new Date().getTime()));
            isSuccess = userService.registWithoutActivate(newUser);
            if (isSuccess) {
                // 生成验证码，发送邮件
                String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
                StringBuilder yzm = new StringBuilder(4);
                for (int i = 0; i < 4; i++) {
                    char ch = str.charAt(new Random().nextInt(str.length()));
                    yzm.append(ch);
                }
                // 将激活码加密后保存在cookie中30分钟（后期改成redis）
                CookieUtils.setCookie(request, response, "yzm", MyMD5Util.md5password(yzm.toString()), 60 * 30);
                CookieUtils.setCookie(request, response, "username", name, 60 * 30);
                SimpleMailMessage message = new SimpleMailMessage();
                message.setFrom("943247920@qq.com");
                message.setTo(newUser.getEMail());
                message.setSubject("主题：验证码");
                message.setText("验证码:" + yzm);
                mailSender.send(message);
                return SysResult.success("注册成功，已发送激活邮件");
            }
            // 输入验证码激活（需要另作一个激活页面）
            // 提示注册成功
            return SysResult.fail(400, "邮件发送失败");
        }
        return SysResult.fail(400, "参数缺失");
    }

    // 用户激活
    @RequestMapping("/regist/doact")
    @ResponseBody
    public SysResult doAct(String yzm, HttpServletRequest request, HttpServletResponse response) {
        // 从cookie中获取验证码，没有，提示"未注册或已过期"
        // 获取cookie值
        String yzm2 = CookieUtils.getCookieValue(request, "yzm");
        String username = CookieUtils.getCookieValue(request, "username");
        if (yzm2 == null)
            return SysResult.fail("未注册或已过期");
        // 与输入的比对
        // 修改用户属性
        // 提示激活成功
        if (MyMD5Util.md5password(yzm).equals(yzm2)) {
            userService.activateUser(username);
            return SysResult.success("激活成功");
        }
        return SysResult.fail("验证码输入错误");
    }

    // 登出
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        // 删除cookie 默认域名还是'/'
        CookieUtils.deleteCookie(request, response, "userId");
        System.out.println("=========重定向===========");
        return "redirect:http://localhost/";
    }

    // 用户个人信息页面
    @RequestMapping("/userinfo")
    @ResponseBody
    public ModelAndView showUserInfo(HttpServletRequest request, HttpServletResponse response, Model model) {
        // 从cookie中获取userId
        String userIdStr = CookieUtils.getCookieValue(request, "userId");
        Integer userId = StringUtils.isEmpty(userIdStr) ? null : Integer.valueOf(userIdStr);
        if (userId != null) {
            User user = userService.getUserInfoByUserId(userId);
            user.setPassword("CouDD");
            model.addAttribute("user", user);
        }
        // 根据id查询各个关联表的总数
        UserVo userVo = userService.getUserDetail(userId);
        ModelAndView view = new ModelAndView();
        view.setViewName("users/userinfo");
        view.addObject("userVo", userVo);
        return view;
    }

    // 用户信息页模块-动态
    @RequestMapping("/list-body-dt")
    public String showDT(HttpServletRequest request, HttpServletResponse response, Model model) {
        // 从cookie中获取userId
        String userIdStr = CookieUtils.getCookieValue(request, "userId");
        Integer userId = StringUtils.isEmpty(userIdStr) ? null : Integer.valueOf(userIdStr);
        // myArticles
        List<Article> myArticles = userService.getArticleByUserId(userId);
        model.addAttribute("myArticles", myArticles);
        // userVo
        UserVo userVo = userService.getUserDetail(userId);
        model.addAttribute("userVo", userVo);
        return "users/userInfoListBody/list_body_dynamic";
    }

    // 用户信息页模块-收藏
    @RequestMapping("/list-body-sc")
    public String showSC(HttpServletRequest request, HttpServletResponse response, Model model) {
        // 从cookie中获取userId
        String userIdStr = CookieUtils.getCookieValue(request, "userId");
        Integer userId = StringUtils.isEmpty(userIdStr) ? null : Integer.valueOf(userIdStr);
        // myArticles
        HashMap<String, List> UserAndArticle = userService.getCollectionArticleByUserId(userId);
        model.addAttribute("myArticles", UserAndArticle.get("ArticleList"));
        model.addAttribute("Author", UserAndArticle.get("userList"));
        return "users/userInfoListBody/list_body_collection";
    }

    // 用户信息页模块-点赞
    @RequestMapping("/list-body-dz")
    public String showDZ(HttpServletRequest request, HttpServletResponse response, Model model) {
        // 从cookie中获取userId
        String userIdStr = CookieUtils.getCookieValue(request, "userId");
        Integer userId = StringUtils.isEmpty(userIdStr) ? null : Integer.valueOf(userIdStr);
        // LikeArticle
        HashMap likeArticle = userService.getLikeArticleByUserId(userId);
        model.addAttribute("myArticles", likeArticle.get("ArticleList"));
        model.addAttribute("Author", likeArticle.get("userList"));
        return "users/userInfoListBody/list_body_thumbs_up";
    }

    // 用户信息页模块-草稿
    @RequestMapping("/list-body-cg")
    public String showCG(HttpServletRequest request, HttpServletResponse response, Model model) {
        // 从cookie中获取userId
        String userIdStr = CookieUtils.getCookieValue(request, "userId");
        Integer userId = StringUtils.isEmpty(userIdStr) ? null : Integer.valueOf(userIdStr);
        // draft
        List<Article> draft = userService.getDraftByUserId(userId);
        model.addAttribute("myArticles", draft);
        return "users/userInfoListBody/list_body_draft";
    }

    // 用户信息页模块-关注
    @RequestMapping("/list-body-gz")
    public String showGZ(HttpServletRequest request, HttpServletResponse response, Model model) {
        // 从cookie中获取userId
        String userIdStr = CookieUtils.getCookieValue(request, "userId");
        Integer userId = StringUtils.isEmpty(userIdStr) ? null : Integer.valueOf(userIdStr);
        // follow
        List<User> dalao = userService.getFollowUserByUserId(userId);
        model.addAttribute("follows", dalao);
        return "users/userInfoListBody/list_body_follow";
    }

    // 用户信息页模块-粉丝
    @RequestMapping("/list-body-fs")
    public String showFS(HttpServletRequest request, HttpServletResponse response, Model model) {
        // 从cookie中获取userId
        String userIdStr = CookieUtils.getCookieValue(request, "userId");
        Integer userId = StringUtils.isEmpty(userIdStr) ? null : Integer.valueOf(userIdStr);
        // fans
        List<User> fans = userService.getFansUserByUserId(userId);
        model.addAttribute("fans", fans);
        return "users/userInfoListBody/list_body_fans";
    }

    // 编辑个人信息页面
    @RequestMapping("edit")
    public String showEdit(Model model, HttpServletRequest request, HttpServletResponse response) {
        // 从cookie中获取userId
        String userIdStr = CookieUtils.getCookieValue(request, "userId");
        Integer userId = StringUtils.isEmpty(userIdStr) ? null : Integer.valueOf(userIdStr);
        if (userId != null) {
            User user = userService.getUserInfoByUserId(userId);
            user.setPassword("CouDD");
            model.addAttribute("user", user);
        }
        return "/users/edit";
    }

    // 编辑个人信息
    @RequestMapping("edit/doedit")
    @ResponseBody
    public SysResult doEdit(User user, Model model, HttpServletRequest request, HttpServletResponse response) {
        // 从cookie中获取userId
        String userIdStr = CookieUtils.getCookieValue(request, "userId");
        Integer userId = StringUtils.isEmpty(userIdStr) ? null : Integer.valueOf(userIdStr);
        user.setUserId(userId);
        user.setUpdateTime(new Timestamp(new Date().getTime()));
        Integer isSuccess = userService.updateUserInfo(user);
        if (isSuccess == 1) {
            return SysResult.success("更新成功");
        }
        return SysResult.fail("更新失败");
    }

    // 上传头像
    @RequestMapping("edit/image")
    @ResponseBody
    public SysResult upload(MultipartFile face, Map map, Model model, HttpServletRequest request,
        HttpServletResponse response) {
        if (face != null) {
            // 从cookie中获取userId
            String userIdStr = CookieUtils.getCookieValue(request, "userId");
            Integer userId = StringUtils.isEmpty(userIdStr) ? null : Integer.valueOf(userIdStr);

            String fileName = face.getOriginalFilename();
            String filePath = "D:/GitWorkesp/blog/images/"; // 上传后的路径
            String suffixName = fileName.substring(fileName.lastIndexOf(".")); // 后缀名
            User currentUser = userService.getUserInfoByUserId(userId);
            fileName = currentUser.getUsername() + "-" + currentUser.getUserId()
            // + "-" + new Date().getTime()
                + suffixName; // 新文件名
            File dest = new File(filePath);
            if (!dest.exists()) {
                dest.mkdir();
            }
            // 等待接收数据流的文件
            File file2 = new File(dest, fileName);
            try {
                // 传入数据
                face.transferTo(file2);
            } catch (Exception e) {
                e.printStackTrace();
            }
            map.put("imgPath", "/images/" + fileName);
            HashMap map2 = new HashMap<>();
            map2.put("imgPath", "/images/" + fileName);
            return SysResult.success("上传 成功", map2);
        } else {
            return SysResult.fail("上传缺失");
        }
    }

    // 打开其他用户界面
    @RequestMapping("other/{username}")
    public String showOtherInfo(HttpServletRequest request, HttpServletResponse response, Model model,
        @PathVariable String username) {
        // 从cookie中获取userId
        String userIdStr = CookieUtils.getCookieValue(request, "userId");
        Integer userId = StringUtils.isEmpty(userIdStr) ? null : Integer.valueOf(userIdStr);
        if (userId != null) {
            User user = userService.getUserInfoByUserId(userId);
            user.setPassword("CouDD");
            model.addAttribute("user", user);
        }
        /*左侧用户*/
        // 根据username查询other用户信息,在查询用户信息
        UserVo userVo = userService.getUserVoByUserName(username);
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

        /*右侧文章*/
        List<Article> myArticles = userService.getArticleByUserId(userVo.getUser().getUserId());
        model.addAttribute("myArticles", myArticles);

        return "users/other_userinfo";
    }

    // 查询归档文章章列表并跳转至他人信息页面
    @RequestMapping("other/archiver")
    public String showArchiver(String username, String yearMonth, HttpServletRequest request, Model model) {
        // 从cookie中获取userId
        String userIdStr = CookieUtils.getCookieValue(request, "userId");
        Integer userId = StringUtils.isEmpty(userIdStr) ? null : Integer.valueOf(userIdStr);
        if (userId != null) {
            User user = userService.getUserInfoByUserId(userId);
            user.setPassword("CouDD");
            model.addAttribute("user", user);
        }
        /*左侧用户*/
        // 根据username查询other用户信息,在查询用户信息
        UserVo userVo = userService.getUserVoByUserName(username);
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

        /*右侧文章*/
        List<Article> myArticles = userService.getArticleByUserIdAndYearMonth(userVo.getUser().getUserId(), yearMonth);
        model.addAttribute("myArticles", myArticles);

        return "users/other_userinfo";
    }
}
