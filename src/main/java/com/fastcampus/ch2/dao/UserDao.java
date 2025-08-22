package com.fastcampus.ch2.dao;

import com.fastcampus.ch2.domain.UserDto;

public interface UserDao {
	UserDto selectById(String id) throws Exception;
}
