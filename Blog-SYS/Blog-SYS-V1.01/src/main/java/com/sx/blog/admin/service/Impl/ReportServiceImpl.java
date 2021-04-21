package com.sx.blog.admin.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sx.blog.admin.enetity.ReportUser;
import com.sx.blog.admin.mapper.ReportMapper;
import com.sx.blog.admin.service.IReportService;
import com.sx.blog.admin.service.ex.NotFindReportUserException;
/**
 *管里用户业务层
 *
 * @author 浩爸爸
 *
 */
@Service
public class ReportServiceImpl implements IReportService{

	@Autowired
	private ReportMapper mapper;
	
	String name;
	String message;
	String title;
	Integer articleId;
	
	@Override
	public List<ReportUser> ReportLogin() {
		
		//调用持久层接口，将数据全部查询出来
		List<ReportUser> result= mapper.selectByUserId();
		
		//进行判断是否为空
		if(result==null) {
			
			throw new NotFindReportUserException("暂时没有用户被举报");
		}
		
		//创建新集合，将数据封装
		List<ReportUser> list=new ArrayList<ReportUser>();
		
		for (ReportUser reportUser : result) {
			
			 //创建新的ReportUser 对象用于封装数据
			 ReportUser user=new ReportUser();
			
			 name = reportUser.getUsername();
			 
			 user.setUsername(name);
			 
			 message = reportUser.getMsg();
			 
			 user.setMsg(message);
			 
			 title = reportUser.getTitle();
			 
			 user.setTitle(title);
			 
			 articleId = reportUser.getArticleId();
			 
			 user.setArticleId(articleId);
			 
			 list.add(user);
		}			
		return list;
	}


}
