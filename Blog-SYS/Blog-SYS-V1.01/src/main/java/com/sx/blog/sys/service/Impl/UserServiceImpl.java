package com.sx.blog.sys.service.Impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sx.blog.common.pojo.Archiver;
import com.sx.blog.common.pojo.Article;
import com.sx.blog.common.pojo.Comment;
import com.sx.blog.common.pojo.User;
import com.sx.blog.common.pojo.UserDetail;
import com.sx.blog.common.util.MyMD5Util;
import com.sx.blog.common.vo.CommentVo;
import com.sx.blog.common.vo.UserVo;
import com.sx.blog.sys.mapper.ArticleMapper;
import com.sx.blog.sys.mapper.CommentMapper;
import com.sx.blog.sys.mapper.UserMapper;
import com.sx.blog.sys.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    CommentMapper commentMapper;
    /**
     * 
     * 检查用户名和密码是否正确
     * 
     * @param
     * @return userId,status
     */
    @Override
    public HashMap checkLogin(String username, String password) {
        User user = userMapper.getUserByUsername(username);
        HashMap hashMap = new HashMap<>();
        if (user != null && user.getPassword().equals(MyMD5Util.md5password(password))) {
            hashMap.put("userId", user.getUserId());
            hashMap.put("status", user.getStatus());
            return hashMap;
        }
        return null;
    }

    /**
     * 通过userId查询用户信息
     * 
     * @param 传入userId
     * @return 返回User
     */
    @Override
    public User getUserInfoByUserId(Integer userId) {
        User user = userMapper.getUserByUserId(userId);
        return user;
    }

    /**
     * 验证用户名是否存在
     * 
     * @param
     * @return true可用（不存在），false存在(不可注册)
     */
    @Override
    public Boolean checkUsername(String username) {
        User user = userMapper.getUserByUsername(username);
        if (user != null)
            return false;
        return true;
    }

    /**
     * 验证邮箱是否被注册
     * 
     * @param
     * @return true可用（未被注册/注册但未激活），false不可用
     */
    @Override
    public Boolean checkEMail(String eMail) {
        List<Integer> statusList = userMapper.getStatusByEmail(eMail);
        for (int i = 0; i < statusList.size(); i++) {
            if (statusList.get(i) == 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    @Transactional
    public Boolean registWithoutActivate(User user) {
        Integer isSuccess = userMapper.insertUserBaseInfo(user);
        if (isSuccess == 1) {
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public Integer activateUser(String username) {
        HashMap activate = new HashMap<>();
        activate.put("username", username);
        activate.put("updateTime", new Timestamp(new Date().getTime()));
        Integer isSuccess = userMapper.updateStatusByUsername(activate);
        return isSuccess;
    }

    @Override
    public UserVo getUserDetail(Integer userId) {
        UserDetail userDetail = new UserDetail(userMapper.countFollow(userId), userMapper.countFans(userId),
            userMapper.countArticle(userId), userMapper.countDoLike(userId), userMapper.countHaveLike(userId),
            userMapper.countCollect(userId), userMapper.countArticleDraft(userId), userMapper.countCommnetNum(userId));
        UserVo userVo = new UserVo();
        User user = this.getUserInfoByUserId(userId);
        user.setPassword("有什么好看的");
        userVo.setUser(user);
        userVo.setUserDetail(userDetail);
        return userVo;
    }

    @Override
    public Integer updateUserInfo(User user) {
        return userMapper.updateByUserSelf(user);
    }

    @Override
    public List<Article> getDraftByUserId(Integer userId) {
        return userMapper.getDraftByUserId(userId);
    }

    @Override
    public List<Article> getArticleByUserId(Integer userId) {
        return userMapper.getArticleByUserId(userId);
    }

    @Override
    public HashMap<String, List> getLikeArticleByUserId(Integer userId) {
        // 设置返回类
        HashMap<String, List> UserAndArticle = new HashMap<String, List>() {
            {
                put("ArticleList", null);
                put("userList", null);
            }
        };
        // 这边没用链表查询（用foreach）
        // 1.查询出用户点赞的文章id列表
        List list = userMapper.getLikeArticleIDByUserId(userId);
        // 2.在文章id列表不为空（为空sql报错）的情况下foreach遍历查询 文章列表
        if (list.isEmpty() == true) {
            return UserAndArticle;
        }
        List<Article> list2 = userMapper.getLikeArticleByLikeList(list);
        // 3.拼接出文章作者用户id列表，并查处 作者列表 在前端通过index拼接
        List<Integer> userIdList = new LinkedList<>();

        for (int i = 0; i < list2.size(); i++) {
            userIdList.add(list2.get(i).getUserId());
        }
        List<User> userList = userMapper.getUserByUserIdList(userIdList);
        // 拼接放入。hashMap中
        if (list2.isEmpty() != true && userList.isEmpty() != true) {
            UserAndArticle.put("ArticleList", list2);
            UserAndArticle.put("userList", userList);
        }
        return UserAndArticle;
    }

    @Override
    public HashMap<String, List> getCollectionArticleByUserId(Integer userId) {
        HashMap<String, List> UserAndArticle = new HashMap<String, List>(){{put("ArticleList",null);put("userList",null);}};
        // 连2表,用户后加，就不连进去 了
        List<Article> ArticleList = userMapper.getCollectionArticleByUserId(userId);
        if(ArticleList.isEmpty()==true) {
            return UserAndArticle;
        }
        List<Integer> userIdList = new LinkedList<>();
        for (int i = 0; i < ArticleList.size(); i++) {
            userIdList.add(ArticleList.get(i).getUserId());
        }
        List<User> userList = userMapper.getUserByUserIdList(userIdList);
        if (ArticleList.isEmpty() != true && userList.isEmpty() != true) {
            UserAndArticle.put("ArticleList", ArticleList);
            UserAndArticle.put("userList", userList);
        }
        return UserAndArticle;
    }

    @Override
    public List<User> getFollowUserByUserId(Integer userId) {
        // 直接连表
        List userList = userMapper.getFollowUserByUserId(userId);
        return userList;
    }

    @Override
    public List<User> getFansUserByUserId(Integer userId) {
        // 直接连表
        List userList = userMapper.getFansUserByUserId(userId);
        return userList;
    }

    @Override
    public List<CommentVo> getCommnetVoByUserId(Integer userId) {
        // 1.查询所有一级评论
        List<Comment> commentList = commentMapper.getCommentListByUserId(userId);
        List<CommentVo> commentVoList = new LinkedList<CommentVo>();
        // 2.填充commentVo
        // comment username userIcon sonCommentNum
        for (Comment comment : commentList) {
            CommentVo commentVo = new CommentVo();
            // 每条父评论对应的子评论 //跟据parentId查询子语句
            // comment username userIcon sonCommentNum;
            User user = userMapper.getUserByUserId(comment.getUserId());
            Integer sonCommentNum = commentMapper.getReplyNum(comment.getCommentId());
            String articleTitle = articleMapper.getArticleById(comment.getArticleId()).getTitle();
            commentVoList
                .add(commentVo.setComment(comment).setUsername(user.getUsername()).setArticleTitle(articleTitle));
        }
        // 一级评论的集合
        return commentVoList;

    }

    @Override
    public List<Archiver> getArchiverByUserId(Integer userId) {
        List<Archiver> archiver = userMapper.getArchiverByUserId(userId);
        StringBuffer newYM2 = new StringBuffer("");
        for (int i = 0; i < archiver.size(); i++) {
            // 处理年月格式2019-02->2019年2月
            String YM = archiver.get(i).getYearMonth();
            String[] newYM = YM.split("\\-");
            int k = 0;
            for (int j = 0; j < newYM.length; j++) {
                if (k == 0) {
                    newYM2.append(newYM[j] + '年');
                    k++;
                } else {
                    if (newYM[j].charAt(0) == '0') {
                        newYM2.append(newYM[j] + '月');
                    } else {
                        newYM2.append(newYM[j] + '月');
                    }
                }
            }
            archiver.get(i).setYearMonthStr(newYM2.toString());
            newYM2 = new StringBuffer("");
        }
        return archiver;
    }

    @Override
    public UserVo getUserVoByUserName(String username) {
        Integer userId = userMapper.getUserByUsername(username).getUserId();
        UserDetail userDetail = new UserDetail(userMapper.countFollow(userId), userMapper.countFans(userId),
            userMapper.countArticle(userId), userMapper.countDoLike(userId), userMapper.countHaveLike(userId),
            userMapper.countCollect(userId), userMapper.countArticleDraft(userId), userMapper.countCommnetNum(userId));
        UserVo userVo = new UserVo();
        User user = this.getUserInfoByUserId(userId);
        user.setPassword("有什么好看的");
        userVo.setUser(user);
        userVo.setUserDetail(userDetail);
        return userVo;
    }

    /**
     * 查询归档文章列表
     */
    @Override
    public List<Article> getArticleByUserIdAndYearMonth(Integer userId, String yearMonth) {
        HashMap a = new HashMap() {
            {
                put("userId", userId);
                put("yearMonth", yearMonth);
            }
        };
        return userMapper.getArticleByUserIdAndYearMonth(a);
    }

}
