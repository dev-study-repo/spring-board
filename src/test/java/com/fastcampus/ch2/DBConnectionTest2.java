package com.fastcampus.ch2;

import org.junit.*;
import org.springframework.context.*;
import org.springframework.context.support.*;

import javax.sql.*;
import java.sql.*;

import static org.junit.Assert.*;

public class DBConnectionTest2 {
    @Test
    public void jdbcConnectionTest() throws Exception {
//        // 스키마의 이름(springbasic)이 다른 경우 알맞게 변경
//        String DB_URL = "jdbc:mysql://localhost:3306/springbasic?useUnicode=true&characterEncoding=utf8";
//
//        // DB의 userid와 pwd를 알맞게 변경
//        String DB_USER = "root";
//        String DB_PASSWORD = "12345678";
//        String DB_DRIVER = "com.mysql.jdbc.Driver";
//
//        DriverManagerDataSource ds = new DriverManagerDataSource();
//        ds.setDriverClassName(DB_DRIVER);
//        ds.setUrl(DB_URL);
//        ds.setUsername(DB_USER);
//        ds.setPassword(DB_PASSWORD);
	    
    	//위 내용을 bean에 등록해서 쓰는 방법.(root-context.xml에 등록)
        ApplicationContext ac = new GenericXmlApplicationContext("file:src/main/webapp/WEB-INF/spring/**/root-context.xml");
        DataSource ds = ac.getBean(DataSource.class);

        Connection conn = ds.getConnection(); // 데이터베이스의 연결을 얻는다.

        System.out.println("conn = " + conn);
        assertTrue(conn!=null);
    }
}
