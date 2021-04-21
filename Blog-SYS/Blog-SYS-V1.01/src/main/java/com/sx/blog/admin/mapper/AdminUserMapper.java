package com.sx.blog.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sx.blog.admin.enetity.User;

/**
 * 管理员管理用户持久层接口
 * @author 浩爸爸
 *
 */
public interface AdminUserMapper {

	List<User> selectAllUser();
	
	Integer updateUser(
			@Param("eMail") String eMail,
			@Param("gender") Integer gender,
			@Param("job") String job,
			@Param("username") String username);
}
