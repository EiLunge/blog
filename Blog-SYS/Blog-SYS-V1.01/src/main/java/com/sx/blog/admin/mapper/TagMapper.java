package com.sx.blog.admin.mapper;

import java.lang.reflect.Parameter;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sx.blog.admin.enetity.Tag;

public interface TagMapper {
	/*
	 * 查询标签
	 */
	
	List<Tag> selectAllTag(@Param("count")Integer count ,@Param("start") Integer start);
	/*
	 * 查询标签
	 */
	Integer countTag();
	/*
	 * 修改标签
	 */
	Integer updateTagNameById(Tag tag);
	/*
	 * 删除标签
	 */
	Integer deleteTagNameById(Tag tag);

}
