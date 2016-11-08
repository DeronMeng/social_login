package com.social.domain;

import com.social.util.Constans.OpenIdType;

public class User {
	private Integer id;

    private String userName;

    private String password;

    private Integer age;
    
    private String openId;
    
    private OpenIdType openIdType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public OpenIdType getOpenIdType() {
		return openIdType;
	}

	public void setOpenIdType(OpenIdType openIdType) {
		this.openIdType = openIdType;
	}
}
