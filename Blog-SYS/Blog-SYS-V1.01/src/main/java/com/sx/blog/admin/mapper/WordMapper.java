package com.sx.blog.admin.mapper;

import java.util.List;

import com.sx.blog.admin.enetity.Word;

public interface WordMapper {
	/*
	 * 查询所有的title内容
	 */
	List<Word> selectAllTitle();
	
}
