package com.sx.blog.admin.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sx.blog.admin.enetity.UpdateArticle;
import com.sx.blog.admin.mapper.DeleteArticleMapper;
import com.sx.blog.admin.service.IDeleteArticleService;
import com.sx.blog.admin.service.IHotArticleService;
import com.sx.blog.common.SysResult;
import com.sx.blog.common.pojo.Article;

/**
 * 删除文章，查看文章控制器
 * @author 浩爸爸
 *
 */

@RestController
public class DeleteArticleContorller extends BaseController{
	
	@Autowired
	private IDeleteArticleService service;
	@Autowired
	private IHotArticleService services;
	
	@Autowired
	private DeleteArticleMapper mapper;
	
	
	/*
	 * 删除文章
	 */
	@RequestMapping("delete")
	@ResponseBody
	public SysResult DeleteArticle(String artid) {
		 
		 //传递过来的id为"5" 正则为5
		 String articleId = artid.replace("\"", "");
		 	 
		 //将String转为Integer
		 Integer  articleIntr= Integer.parseInt(articleId);
		
	     //调用业务层方法
		 service.deleteArticle(articleIntr);
		 
		 mapper.deleteReport(articleIntr);
		 //返回数据
		 return SysResult.success();
	}
	
	/*
	 * 查看文章
	 */
	@RequestMapping("lookArticle")
	@ResponseBody
	public SysResult UpdateArticle(String artid,String title) {
		 
		 //传递过来的id为"5" 正则为5
		 String articleId = artid.replace("\"", "");
		 	 
		 //将String转为Integer
		 Integer  articleIntr= Integer.parseInt(articleId);
		 
	     //调用业务层方法
		 UpdateArticle look = services.updateArticle(articleIntr);		 
		
		 //返回数据
		 return SysResult.success(look);
	}
	/*
	 * 查看热门文章
	 */
	@RequestMapping("lookArticleHot")
	@ResponseBody
	public SysResult UpdateArticle(String title) {
		 
		 //传递过来的id为"5" 正则为5
		 String articleId = title.replace("\"", "");
		 
		 String str = articleId;
		 Integer i = null;
		 if(str!=null){
		      i = Integer.valueOf(str);
		 }
		 		 
	     //调用业务层方法
		 Article look = services.selectArticle(i);

		
		 //返回数据
		 return SysResult.success(look);
	}
	
}
