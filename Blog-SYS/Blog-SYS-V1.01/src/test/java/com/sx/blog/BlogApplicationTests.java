package com.sx.blog;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class BlogApplicationTests {


	@Test
	void contextLoads() {
	}
	
	@Autowired 
	public DataSource dataSource; 
	
	@Test
	public void getConnection() throws SQLException {
		Connection conn = dataSource.getConnection();
		System.err.println("conn="+conn);
	}
	

}
