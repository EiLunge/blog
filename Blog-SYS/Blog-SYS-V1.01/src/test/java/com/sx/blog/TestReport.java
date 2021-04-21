package com.sx.blog;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.sx.blog.admin.enetity.ReportUser;
import com.sx.blog.admin.enetity.Tag;
import com.sx.blog.admin.mapper.TagMapper;
import com.sx.blog.admin.service.IReportService;
import com.sx.blog.admin.service.ITagService;
import com.sx.blog.admin.service.ex.ServiceException;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestReport {

    @Autowired
    private IReportService service;

    @Autowired
    private ITagService mapper;

    @Autowired
    private  TagMapper mapperaa;

    @Test
    public void selectTags() {
    	List<Tag>  s = mapper.selectAll(20, 10);
    	System.out.println(s);
    }
    
    
    @Test
    public void selectTag() {
    	List  s = mapperaa.selectAllTag(0, 10);
    	System.out.println(s);
    }
    
    
 

    @Test
    public void select2() {
        try {
            List<ReportUser> list = service.ReportLogin();
            System.out.println(list);
            System.out.println("OK");
        } catch (ServiceException e) {
            System.err.println(e.getClass().getName());
            System.err.println(e.getMessage());
        }
    }

}
