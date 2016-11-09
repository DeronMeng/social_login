package com.social.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.social.dao.UserDao;
import com.social.domain.User;
import com.social.domain.vo.UserVO;
import com.social.service.UserService;
import com.social.util.Constans.OpenIdType;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Resource
	private UserDao userDao;

	@Override
	public User getUserById(int userId) {
		return userDao.selectByPrimaryKey(userId);
	}

	@Override
	public UserVO getUserByOpenId(String openId, OpenIdType openIdType) {
		return userDao.getUserByOpenId(openId, openIdType);
	}

	@Override
	public int insertSelective(User record) {
		return userDao.insertSelective(record);
	}
}
