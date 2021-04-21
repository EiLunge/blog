package com.sx.blog.sys.service;

import java.util.HashMap;
import java.util.List;

import com.sx.blog.common.pojo.Archiver;
import com.sx.blog.common.pojo.Article;
import com.sx.blog.common.pojo.User;
import com.sx.blog.common.vo.CommentVo;
import com.sx.blog.common.vo.UserVo;

public interface UserService {
    HashMap checkLogin(String username, String password);

    User getUserInfoByUserId(Integer userId);

    Boolean checkUsername(String username);

    Boolean checkEMail(String eMail);

    Boolean registWithoutActivate(User user);

    Integer activateUser(String username);

    UserVo getUserDetail(Integer userId);

    Integer updateUserInfo(User user);

    List<Article> getDraftByUserId(Integer userId);

    List<Article> getArticleByUserId(Integer userId);

    List<Article> getArticleByUserIdAndYearMonth(Integer userId, String yearMonth);

    HashMap<String, List> getLikeArticleByUserId(Integer userId);

    HashMap<String, List> getCollectionArticleByUserId(Integer userId);

    List<CommentVo> getCommnetVoByUserId(Integer userId);

    List<User> getFollowUserByUserId(Integer userId);

    List<User> getFansUserByUserId(Integer userId);

    List<Archiver> getArchiverByUserId(Integer userId);
    
    UserVo getUserVoByUserName(String username);
}
