package com.sx.blog.admin.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sx.blog.admin.enetity.HotArticle;
import com.sx.blog.admin.enetity.UpdateArticle;
import com.sx.blog.admin.mapper.HotArticleMapper;
import com.sx.blog.admin.service.IHotArticleService;
import com.sx.blog.common.pojo.Article;

@Service
public class HotArticleImpl implements IHotArticleService{

	@Autowired
	private HotArticleMapper mapper;
	
	@Override
	public List<HotArticle> selectHotArticle() {
		
		//根据点赞数量查询出排名前三的文章
		List<HotArticle> list = mapper.selectHotArticle();
		
		return list;
	}

	@Override
	public UpdateArticle updateArticle(Integer articleId) {
		
		return mapper.updateArticle(articleId);
	}

	@Override
	public Article selectArticle(Integer articleId) {
		return mapper.selectHotPcc(articleId);
	}

	


	
}
