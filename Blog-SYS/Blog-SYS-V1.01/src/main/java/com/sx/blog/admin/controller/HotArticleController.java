package com.sx.blog.admin.controller;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.sx.blog.admin.enetity.HotArticle;

import com.sx.blog.admin.service.IHotArticleService;
import com.sx.blog.common.SysResult;

/**
 * 热门文章控制器
 * @author 浩爸爸
 *
 */
@RestController
public class HotArticleController extends BaseController{

	@Autowired
	private IHotArticleService service;

	@RequestMapping("hot")
	public SysResult login(Model model) {	

		//调用业务层方法执行数据显示
		List<HotArticle> list= service.selectHotArticle();
		
		//使用model将数据传入前端thy模板
		model.addAttribute("hot", list); 

		//将list放入封装类中	
		return SysResult.success(list);
	}

}
