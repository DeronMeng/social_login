package com.social.util;

public class Constans {
	public class WX {
		public static final String SOCIAL_LOGIN_CLIENT_ID = "wechat_client_id";
		public static final String SOCIAL_LOGIN_CLIENT_SERCRET = "wechat_client_sercret";
		public static final String SOCIAL_LOGIN_REDIRECT_URI = "wechat_redirect_uri";
		public static final String SOCIAL_LOGIN_MOBILE_REDIRECT_URI = "mobile_wechat_redirect_uri";
	}
	
	public class WEIBO {
		public static final String SOCIAL_LOGIN_CLIENT_ID = "weibo_client_id";
		public static final String SOCIAL_LOGIN_CLIENT_SERCRET = "weibo_client_sercret";
		public static final String SOCIAL_LOGIN_REDIRECT_URI = "weibo_redirect_uri";
	}
	
	public class QQ {
		public static final String SOCIAL_LOGIN_CLIENT_ID = "qq_client_id";
		public static final String SOCIAL_LOGIN_CLIENT_SERCREt = "qq_client_sercret";
		public static final String SOCIAL_LOGIN_REDIRECT_URI = "qq_redirect_uri";
	}
	
	public enum OpenIdType {
		WEIBO,QQ,WECHAT
	}
}
