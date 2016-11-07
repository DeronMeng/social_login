package com.social.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.social.dao.UserDao;
import com.social.domain.User;
import com.social.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Resource
	private UserDao userDao;

	@Override
	public User getUserById(int userId) {
		return userDao.selectByPrimaryKey(userId);
	}

}
