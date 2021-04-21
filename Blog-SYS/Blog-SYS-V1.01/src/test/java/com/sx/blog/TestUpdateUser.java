package com.sx.blog;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.sx.blog.admin.mapper.DeleteArticleMapper;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestUpdateUser {

    @Autowired
    private DeleteArticleMapper mapper;

    @Test
    public void delete() {
        Integer cow = mapper.deleteArticleById(7);
        System.out.println(cow);
    }

}
