package com.sx.blog;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.sx.blog.admin.enetity.AdminUser;
import com.sx.blog.admin.mapper.AdminMapper;
import com.sx.blog.admin.service.IAdminUserService;
import com.sx.blog.admin.service.ex.ServiceException;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestAdmin {

    @Autowired
    private AdminMapper mapper;

    @Autowired
    private IAdminUserService mappers;

    @Test
    public void login() {
        String userName = "掘csdn幕后黑手";

        AdminUser s = mapper.selectAdminUser(userName);
        System.err.println("conn=" + s);
    }

    @Test
    public void login2() {
        try {
            String username = "掘csdn幕后黑";
            String password = "12345";
            mappers.AdminLogin(username, password);
            System.out.println("OK");
        } catch (ServiceException e) {
            System.err.println(e.getClass().getName());
            System.err.println(e.getMessage());
        }
    }

}
