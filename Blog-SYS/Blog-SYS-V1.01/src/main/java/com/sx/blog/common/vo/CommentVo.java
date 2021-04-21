package com.sx.blog.common.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sx.blog.common.pojo.Comment;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author 邓宇阳
 * @date 2019年12月31日
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true) // 表示JSON转化时忽略未知属性
@Accessors(chain = true)
public class CommentVo {
    private Comment comment;
    private String username;
    private String userIcon;
    private Integer sonCommentNum;
    private String parentName;
    private String articleTitle;

}
