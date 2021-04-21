package com.sx.blog.admin.service;

import java.util.List;

import com.sx.blog.admin.enetity.ReportUser;

/**
 * 管理用户账号业务接口
 * @author 浩爸爸
 *
 */
public interface IReportService {

	/*
	 * 处理用户账号登录还是禁止的接口 
	 */
	 List<ReportUser> ReportLogin();
	 
}
