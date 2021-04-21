
package com.sx.blog.common.pojo;
import java.sql.Timestamp;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;
@Data
@JsonIgnoreProperties(ignoreUnknown=true) //表示JSON转化时忽略未知属性
@Accessors(chain=true)
public class Article {
	private Integer articleId;
	private Integer userId;
	private String tagId;
	private String title;
	//数据表中为abstract设置resultMap时注意一下
	private String abstracts;
	private String pcContent;
	private String wxContent;
	private Integer likes;
	private Integer commentNum;
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	private Timestamp createTime;

	private Timestamp updateTime;
	private String images;
	private Integer status;   //状态 0 草稿  1发表
	private Integer browse;  //浏览量
	private Integer recommend;//是否推荐
	
}



