package com.sx.blog.common.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 用户其他信息
 * @author 邓宇阳
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true) //表示JSON转化时忽略未知属性
@Accessors(chain=true)
public class UserDetail {

	private Integer followNum;  //用户关注人数量
	private Integer fansNum;    //用户粉丝数
	private Integer articleNum; //用户发表博文数  status = 1
	private Integer likesNum;   //用户点赞数
	private Integer haveLikesNum; //用户点赞数
	private Integer collectNum; //用户收藏博文
	private Integer articleDraftNum;  //用户草稿博文  status = 0
	private Integer commentNum;//用户评论数 
}
