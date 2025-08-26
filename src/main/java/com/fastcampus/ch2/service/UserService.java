package com.fastcampus.ch2.service;

import com.fastcampus.ch2.domain.UserDto;

public interface UserService {
	boolean login(String id, String pwd);
	UserDto getUserById(String id) throws Exception;
}
