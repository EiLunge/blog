package com.sx.blog.sys.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.sx.blog.common.SysResult;
import com.sx.blog.common.pojo.Article;
import com.sx.blog.common.pojo.Tag;
import com.sx.blog.common.util.CookieUtils;
import com.sx.blog.sys.mapper.ArticleMapper;
import com.sx.blog.sys.service.ArticleService;

/**
 * 文章控制器
 * @author 邓宇阳
 *
 */
@Controller
@RequestMapping("article")
public class ArticleController {

	@Autowired
	private ArticleService articleService;
	@Autowired
	private ArticleMapper articleMapper;

	/*
	 * 打开编辑文章页面,打开分类标签
	 */
	@RequestMapping("/edit")
	public String enid(Model model) {
		List<Tag> tag = articleService.findTag();
		model.addAttribute("Tag", tag);
		return "/show/publish_article";
	}

    /**
     * 写文章
     * 
     * @param tag
     *            分类标签
     * @param abstracts
     *            标签
     * @param title
     *            标题
     * @param pcContent
     *            主体内容
     * @return
     */
	@RequestMapping("/publish")
	@ResponseBody
	public SysResult addArticle(@RequestParam(value = "tag",required = true) List<String> tag,String abstracts,
        String title, String pcContent, Integer articleId, String images, HttpServletRequest request) {
		//转化tag
		String tagId = articleService.getTagIdByTagName(tag);
		//转换时间格式
		Timestamp time = new Timestamp(new Date().getTime());
        // 从cookie中获取userId
        String userIdStr = CookieUtils.getCookieValue(request, "userId");
        Integer userId = StringUtils.isEmpty(userIdStr) ? null : Integer.valueOf(userIdStr);
		//创建实体类,添加文章

		Article article = new Article();
		article.setAbstracts(abstracts).setPcContent(pcContent).setImages(images)
		.setTitle(title).setUpdateTime(time).setTagId(tagId)
            .setUserId(userId).setStatus(1);
        // 新增章
        if (articleId == null) {
			article.setCreateTime(time);
			articleService.addArticle(article);
		}else {
			//编辑修改文章
			article.setArticleId(articleId);
			articleMapper.updateArticle(article);
		}
		return SysResult.success();	
	} 

	//存草稿
	@ResponseBody
	@RequestMapping("/modify")
	public SysResult addArticleDraft(@RequestParam(value = "tag",required = true) List<String> tag,String abstracts,
        String title, String pcContent, Integer articleId, String images, HttpServletRequest request) {
        // System.out.println("草稿:"+articleId);
		//转化tag
		String tagId = articleService.getTagIdByTagName(tag);
		//转换时间格式
		Timestamp t = new Timestamp(new Date().getTime());
        // 从cookie中获取userId
        String userIdStr = CookieUtils.getCookieValue(request, "userId");
        Integer userId = StringUtils.isEmpty(userIdStr) ? null : Integer.valueOf(userIdStr);
		//创建实体类,添加文章
		Article article = new Article();
        article.setAbstracts(abstracts).setPcContent(pcContent).setImages(images).setTagId(tagId).setTitle(title)
            .setUpdateTime(t).setRecommend(0)
            .setUserId(userId).setStatus(0);
		if(articleId==null) {
			article.setCreateTime(t);
			articleService.addArticle(article);
		}else {
			//编辑修改文章
			article.setArticleId(articleId);
			articleMapper.updateArticle(article);
		}
		return SysResult.success();	
	}
	
    /*
             * 草稿跳到编辑文章   更新
     */
	@RequestMapping("/update/{articleId}")
	public String updateArticle(@PathVariable Integer articleId,Model model) {
		Article article = articleMapper.selectArticle(articleId);
		List<Tag> tagList = articleService.findTag();
        model.addAttribute("Tag", tagList);
        List<Integer> tagIdList = articleService.selectTagsIdByArticleId(articleId);
        // 回显数据
        model.addAttribute("articleTitle", article.getTitle());
        model.addAttribute("tagIdList", tagIdList);
        model.addAttribute("articleAbstracts", article.getAbstracts());
        model.addAttribute("contents", article);
        // 记录articleId 标记这文章是草稿过来，更新or发布
        model.addAttribute("articleId", articleId);

		return "/show/publish_article";
	}


	/*
	 * 删除文章
	 */
	@ResponseBody
	@RequestMapping("/delete")
    public SysResult delArticle(Integer articleId) {
        articleService.delArticle(articleId);
		return SysResult.success();
	}

	/*
	 * 上传图片
	 */
	@ResponseBody
	@RequestMapping("uploadfile")
	public Map<String, Object> uploadfileImages(@RequestParam(value = "editormd-image-file", required = false) MultipartFile file, 
			HttpServletRequest request) {
		Map<String, Object> resultMap = articleService.uploadfileImages(file,request);
		return resultMap;
	}
}
