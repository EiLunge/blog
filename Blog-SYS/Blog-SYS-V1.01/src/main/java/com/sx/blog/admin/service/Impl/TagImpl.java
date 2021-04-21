package com.sx.blog.admin.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sx.blog.admin.enetity.Tag;
import com.sx.blog.admin.mapper.TagMapper;
import com.sx.blog.admin.service.ITagService;
import com.sx.blog.admin.service.ex.InsertTagException;

@Service
public class TagImpl implements ITagService{

	@Autowired
	private TagMapper mapper;
	
	
	@Override
	public List<Tag> selectAll(Integer count,Integer start) {
		
		//持久层查出全部的标签
		List<Tag> list = mapper.selectAllTag(count, start);
		
		return list;
	}
	
	@Override
	public void updateTag(Tag tag) {
		
		//根据标签名字修改标签
		mapper.updateTagNameById(tag);
		
	}

	@Override
	public Integer deleteTag(Tag tag) {
		
		//根据标签id删除白浅
		 mapper.deleteTagNameById(tag);
		 
		 return null;
	}
	/*
	 * (non-Javadoc)统计标签数量
	 * @see com.sx.blog.admin.service.ITagService#countTag()
	 */
	@Override
	public Integer countTag() {
		
		Integer cot = mapper.countTag();
		
		return cot;
	}

	
}
