package com.fastcampus.ch2.dao;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fastcampus.ch2.domain.UserDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class UserDaoImplTest {

	@Autowired
    private UserDao userDao;
	@Test
	public void test() throws Exception{
    	UserDto userDto = userDao.selectById("lee77");
    	System.out.print(userDto.getId());
    	assertTrue(userDto != null);
    	
    }

}
