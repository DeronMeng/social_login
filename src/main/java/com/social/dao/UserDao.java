package com.social.dao;

import com.social.domain.User;
import com.social.domain.vo.UserVO;
import com.social.util.Constans.OpenIdType;

public interface UserDao {
	int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    public UserVO getUserByOpenId(String openId, OpenIdType openIdType);
    
}
