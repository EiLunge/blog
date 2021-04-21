
package com.sx.blog.common.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.experimental.Accessors;
@Data
@JsonIgnoreProperties(ignoreUnknown=true) //表示JSON转化时忽略未知属性
@Accessors(chain=true)
public class Comment implements Serializable {

	private static final long serialVersionUID = -612206430592072819L;
	
	private Integer commentId;  //主键 评论id
	private Integer userId;     //用户id
	private Integer articleId;  //文章id
	private String commentText; //评论内容
    private Integer likes; // 点赞量
	private Integer parentId;   //回复的评论id
	private Timestamp createTime;    //创建时间
    private Integer firstLevelCommentId;// 一级评论id


	
	
}



