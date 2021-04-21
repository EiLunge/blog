package com.sx.blog.sys.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.sx.blog.common.pojo.Collect;
import com.sx.blog.common.pojo.Follow;
import com.sx.blog.common.pojo.Like;

/** 
* @author 邓宇阳
* @date 2019年12月29日
*/
public interface UserPowerMapper {
    //查询点赞信息
	@Select("select * from public.like where user_id=#{userId} and article_id=#{articleId}")
	Like getLikeByArticleIdAndUserId(@Param("articleId")Integer articleId,@Param("userId")Integer userId);

/*	//修改点赞转状态
	@Update("update public.like set status=#{status} where like_id=#{likeId} ")
	void updateLikeStatus(@Param("status")Integer status, @Param("likeId")Integer likeId);*/
	//删除点赞数据
	@Delete("delete from public.like where like_id = #{likeId}")
    void delLikeByLikeId(Integer likeId);

	//插入点赞数据
	@Insert("insert into public.like (user_id,article_id,status) values (#{userId},#{articleId},#{status})")
	@Options(useGeneratedKeys=true, keyProperty="likeId", keyColumn="like_id")
	void addLike(Like like);

	//插入关注表
	@Insert("insert into public.follow (user_id,follow_user,status)  values (#{uid},#{userId},0)")
	void addFollow(@Param("userId")Integer userId, @Param("uid")Integer uid);
	//插入粉丝表表
	@Insert("insert into public.fans (user_id,user_fans,status)  values (#{userId},#{uid},0)")
	void addFans(@Param("userId")Integer userId, @Param("uid")Integer uid);
	//删除关注表
	@Delete("delete from public.follow where follow_id = #{followId}")
	void delFollow(Integer followId);
	//删除粉丝表
	@Delete("delete from public.fans where user_id = #{userId} and user_fans = #{uid}")
	void delFans(@Param("userId")Integer userId,@Param("uid")Integer uid);
	//查询是否存在关注关系
	@Select("select follow_id from public.follow where user_id=#{uid} and follow_user=#{userId}")
	Follow selectFollow(@Param("userId")Integer userId,@Param("uid")Integer uid);

	//增加收藏  RETURNING collect_id
	@Insert("insert into public.collect (user_id,article_id) values (#{userId},#{articleId})")
	@Options(useGeneratedKeys=true, keyProperty="collectId", keyColumn="collect_id")
	void addCollectArticle(Collect collect);
	
    //删除收藏
	@Delete("delete from public.collect where collect_id = #{collectId}")
	void delCollectArticle(Integer collectId);

	//博文举报
	@Insert("insert into public.report (user_id,article_id,msg) values (#{userId},#{articleId},#{msg})")
	void addReportArticle(@Param("articleId")Integer articleId, @Param("msg")String msg, @Param("userId")Integer userId);

	//查询收藏id
	@Select("select collect_id from public.collect where article_id=#{articleId} and user_id=#{uid}")
	Integer getCollectIdByArticleIdAndUserId(@Param("articleId")Integer articleId, @Param("uid")Integer uid);
    //查询likeId
    @Select("select like_id from public.like where article_id=#{articleId} and user_id=#{uid}")
    Integer getLikeIdByArticleIdAndUserId(@Param("articleId")Integer articleId, @Param("uid")Integer uid);

}
