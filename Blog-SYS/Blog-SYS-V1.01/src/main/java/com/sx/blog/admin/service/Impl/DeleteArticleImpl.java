package com.sx.blog.admin.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sx.blog.admin.mapper.DeleteArticleMapper;
import com.sx.blog.admin.service.IDeleteArticleService;

@Service
public class DeleteArticleImpl implements IDeleteArticleService{

	@Autowired
	private DeleteArticleMapper mapper;
	
	@Override
	public Integer deleteArticle(Integer articleId) {

		//根据文章id删除文章
		mapper.deleteArticleById(articleId);
		
		return null;
	}
	
}
