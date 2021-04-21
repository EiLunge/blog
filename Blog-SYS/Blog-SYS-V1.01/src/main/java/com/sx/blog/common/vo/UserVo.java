package com.sx.blog.common.vo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sx.blog.common.pojo.User;
import com.sx.blog.common.pojo.UserDetail;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户基本信息+用户其他信息
 * @author 邓宇阳
 *
 */
@Data
@JsonIgnoreProperties(ignoreUnknown=true) //表示JSON转化时忽略未知属性
@Accessors(chain=true)
public class UserVo {

    private User user; // 用户对象
    private UserDetail userDetail; // 其他的属性


}
