package com.social.service;

import com.social.domain.User;
import com.social.domain.vo.UserVO;
import com.social.util.Constans.OpenIdType;

public interface UserService {
	public User getUserById(int userId);
	
	public UserVO getUserByOpenId(String openId, OpenIdType openIdType);
	
	int insertSelective(User record);
	
}
