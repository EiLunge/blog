package com.sx.blog.sys.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.sx.blog.common.pojo.Article;
import com.sx.blog.common.pojo.Tag;
import com.sx.blog.common.vo.UserVo;

public interface ArticleService {


	List<Tag> findTag();

	void addArticle(Article article);

	Article getArticleById(Integer articleId);

	UserVo getUserByArticleId(Integer articleId);

	Map<String, Object> uploadfileImages(MultipartFile file, HttpServletRequest request);
	
	/**
	 * 通过标签名查询标签id
	 *@param tagName
	 *@return 标签id 
	 */
	String getTagIdByTagName(List<String> tagName);

    /**
     * 回显文章标签信息
     * 
     * @param articleId
     * @return tagId List
     */
    List<Integer> selectTagsIdByArticleId(Integer articleId);
    
    /**
     * 删除文章及其评论
     */
    void delArticle(Integer articleId);
}
