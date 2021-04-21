package com.sx.blog.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sx.blog.common.pojo.Article;
import com.sx.blog.common.pojo.Tag;
import com.sx.blog.common.pojo.User;

public interface ArticleMapper {

	//编辑文章
	Article selectArticle(Integer articleId);
	
	//查询所有标签
	@Select("select * from public.tag")
	List<Tag> selectTagName();
	
    // 根据标签id查询分类标签信息
	@Select("select * from public.tag where tag_id=#{tagId}")
	Tag selectArticleTagName(Integer tagId);

    // 根据文章id查标签列表
    @Select("select tag_id from public.article where article_id = #{articleId}")
    String selectTagsIdByArticleId(Integer articleId);

	//添加文章
	void addArticle(Article article);

	//根据文章id查询文章信息
	@Select("select * from public.article where article_id = #{articleId}")
	Article getArticleById(Integer articleId);

	//根据用户id查询用户信息
	@Select("select user_id,username,icon from public.user where user_id=#{userId} and status = 0")
	User getUserById(Integer userId);
	
	@Update("update public.article set browse=#{browse} where article_id=#{articleId}")
	void addBrowse(@Param("browse") Integer browse,@Param("articleId")Integer articleId);

	//增加点赞量
	@Update("update public.article set likes=#{likes} where article_id=#{articleId}")
	void updateLikes(@Param("likes")Integer likes,@Param("articleId")Integer articleId);

	//增加图片路径
	@Update("update public.article set images=#{urlImages} where article_id=#{articleId}")
	void updateImages(String urlImages);

	//删除图片
	@Delete("delete from public.article where article_id = #{articleId}")
	void delArticle(Integer articleId);

	//修改文章
	void updateArticle(Article article);
	
	
	
	
}
