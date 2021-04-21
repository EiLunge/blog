package com.sx.blog.admin.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sx.blog.admin.enetity.Tag;
import com.sx.blog.admin.service.ITagService;
import com.sx.blog.common.SysResult;


/**
 * 标签控制器
 * @author 浩爸爸
 *
 */

@RestController
public class DeleteTagcontroller extends BaseController{
	
	@Autowired
	private ITagService service;
	
	/*
	 * 删除标签
	 */
	@RequestMapping("deletetag")
	@ResponseBody
	public SysResult DeleteArticle(String tagName,Integer tagId) {
		 //传递过来的id为"5" 正则为5
		 String articleId = tagName.replace("\"", "");
		 
		 //创建tag对象对数据封装
		 Tag t=new Tag();
		 t.setTagName(articleId);
		 t.setTagId(tagId);
		 
		 //讲tag对象放入业务层删除标签接口
		 service.deleteTag(t);
		 //返回数据
		 return SysResult.success();
	}
	/*
	 * 统计标签
	 */
	@RequestMapping("counttag")
	@ResponseBody
	public Integer count() {	 
		 //讲tag对象放入业务层删除标签接口
		 Integer c = service.countTag();

		 //返回数据
		 return c;
	}
	
}
