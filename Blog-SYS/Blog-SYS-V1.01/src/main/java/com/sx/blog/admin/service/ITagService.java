package com.sx.blog.admin.service;

import java.util.List;

import com.sx.blog.admin.enetity.Tag;

public interface ITagService {
	/*
	 * 查询标签
	 */
	List<Tag> selectAll(Integer count,Integer start); 
	
	
	Integer countTag();
	
	
	/*
	 * 修改标签
	 */
	void updateTag(Tag tag);
	/*
	 * 删除标签
	 */
	Integer deleteTag(Tag tag);
	
}
