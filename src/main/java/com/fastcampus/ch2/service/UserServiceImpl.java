package com.fastcampus.ch2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fastcampus.ch2.dao.UserDao;
import com.fastcampus.ch2.domain.UserDto;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	@Override
	public boolean login(String id, String pwd) {
	try {
		UserDto userDto = userDao.selectById(id);
		return userDto != null && userDto.getPwd().equals(pwd);
	}catch(Exception e){
		System.err.println("로그인 중 예외 발생: " + e.getMessage());
        return false;
	}
	}

}
