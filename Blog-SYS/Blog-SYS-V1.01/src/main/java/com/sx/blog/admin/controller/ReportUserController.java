package com.sx.blog.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.sx.blog.admin.enetity.HotArticle;
import com.sx.blog.admin.enetity.ReportUser;
import com.sx.blog.admin.enetity.Tag;
import com.sx.blog.admin.enetity.User;
import com.sx.blog.admin.service.AdminLookUserService;
import com.sx.blog.admin.service.IHotArticleService;
import com.sx.blog.admin.service.IReportService;
import com.sx.blog.admin.service.ITagService;
import com.sx.blog.common.SysResult;
/**
 * 被举报用户等控制器
 * @author 浩爸爸
 *
 */
@RestController
public class ReportUserController extends BaseController{

	@Autowired
	private IReportService service;
	@Autowired
	private AdminLookUserService services;
	@Autowired
	private IHotArticleService servicess;
	@Autowired
	private  ITagService tagservice;

	Integer count;
	Integer start;
	
	@RequestMapping("adminindex")
	public ModelAndView login(User user,Model model,Tag tag,Integer cou) {
		Integer count=10;
		Integer start=0;
		
		//修改用户信息
		services.updateUser(user);
		
		//修改标签
		tagservice.updateTag(tag);
		
		//管理用户登陆状态
		List<ReportUser> list= service.ReportLogin();
		
		//查看所有用户信息
		List<User> lists =  services.selectUser();
		
		//热门文章
		List<HotArticle> listss= servicess.selectHotArticle();

		System.out.println("点击下一页后"+start);
		//标签
		
		List<Tag> result = tagservice.selectAll(count,start);
			
		//为了前端便利数据讲数据封装到modle中
		model.addAttribute("tag", result);
		
		//使用model将数据传入前端thy模板
		model.addAttribute("hot", listss); 
		
		//为了前端便利数据讲数据封装到modle中
		model.addAttribute("user", lists);
		
		//将list放入model中
		model.addAttribute("users", list);
		
		//创建modelanview封装数据返回到前端页面
		ModelAndView view=new ModelAndView("/admins/adminindex");
		
		return view;
	}
	
	@RequestMapping("pageTag")
	public SysResult tag(Integer cou,Model model) {
		Integer count=10;
		Integer start=0;
		
		if(cou == 10) {
			count += 10;
			start += 10;
		}	

		List<Tag> result = tagservice.selectAll(count,start);
		
		return SysResult.success(result);
	}
	@RequestMapping("pageTagUp")
	public SysResult tagUp(Integer cou,Model model) {
		
		if(cou == 20) {
			count=10;
			start=0;
		}	
		//标签
		List<Tag> result = tagservice.selectAll(count,start);
			
		return SysResult.success(result);
	}
	
}
