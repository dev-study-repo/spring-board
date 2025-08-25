package com.fastcampus.ch2.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.fastcampus.ch2.domain.UserDto;

@Repository
public class UserDaoImpl implements UserDao {
	@Autowired
    private SqlSessionTemplate session;
    private static String namespace = "com.fastcampus.ch2.mapper.UserMapper.";
    
    public UserDto selectById(String id) throws Exception{
    	return session.selectOne(namespace+"selectById",id);
    }

}
