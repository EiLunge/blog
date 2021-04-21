package com.sx.blog.admin.mapper;

import java.util.List;

import com.sx.blog.admin.enetity.ReportUser;

/**
 * 被举报用户管理持久层接口
 * @author 浩爸爸
 *
 */
public interface ReportMapper {

     /*
      *  查询被举报用户的名字，博文，以及被举报信息
      */
     List<ReportUser> selectByUserId();
     
     /*
      * 根据博文id删除博文
      */
     Integer deleteArticleById(Integer article);
}
