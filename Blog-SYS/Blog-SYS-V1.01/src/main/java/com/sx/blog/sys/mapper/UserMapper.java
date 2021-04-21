package com.sx.blog.sys.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sx.blog.common.pojo.Archiver;
import com.sx.blog.common.pojo.Article;
import com.sx.blog.common.pojo.User;

@Mapper
public interface UserMapper {

    // @Select("select user_id from public.user where user_id=24")
    // String test();
    User getUserByUsername(String username);

    User getUserByUserId(Integer userId);

    // in
    List<User> getUserByUserIdList(@Param("userIdList") List userIdList);

    List<Integer> getStatusByEmail(String eMail);

    Integer insertUserBaseInfo(User user);

    Integer updateStatusByUsername(HashMap active);

    // queryUserDetail
    Integer countFollow(Integer userId);

    Integer countFans(Integer userId);

    Integer countDoLike(Integer userId);
    
    Integer countHaveLike(Integer userId);

    Integer countCollect(Integer userId);

    Integer countArticle(Integer userId);

    Integer countArticleDraft(Integer userId);

    Integer updateByUserSelf(User user);

    List<Article> getDraftByUserId(Integer userId);

    List<Article> getArticleByUserId(Integer userId);

    List<Integer> getLikeArticleIDByUserId(Integer userId);

    List<Article> getLikeArticleByLikeList(@Param("likeList") List likeList);

    List<Archiver> getArchiverByUserId(Integer userId);

    List<Article> getArticleByUserIdAndYearMonth(HashMap a);

    List<Article> getCollectionArticleByUserId(Integer userId);

    List getFollowUserByUserId(Integer userId);

    List getFansUserByUserId(Integer userId);

    Integer countCommnetNum(Integer userId);

}
