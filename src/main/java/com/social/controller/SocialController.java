package com.social.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import weibo4j.Oauth;
import weibo4j.model.WeiboException;
import weibo4j.util.WeiboConfig;

import com.qq.connect.QQConnectException;
import com.social.util.AppConfig;
import com.social.util.Constans;
import com.social.util.WeChatDevUtils;

@Controller
@RequestMapping("/social")
public class SocialController extends BaseController {
	private static Logger log = Logger.getLogger(SocialController.class);
	
	@RequestMapping("/index")
	public String index() {
		return "social/index";
	}

	@RequestMapping("/weibo")
	public void weibo() throws WeiboException, IOException {
		updateProperties();
		Oauth oauth = new Oauth();
		String url = oauth.authorize("code","");
		getResponse().sendRedirect(url);
	}
	
	@RequestMapping("/wechat")
	public void wechat() throws IOException, Exception {
		String state = getUUID();
		getRequest().getSession().setAttribute("state", state);
		getResponse().sendRedirect(WeChatDevUtils.getQrconnect(AppConfig.getProperty(Constans.WX.SOCIAL_LOGIN_REDIRECT_URI), state));
	}
	
	@RequestMapping("/mobile/wechat")
	public void mobileWechat() throws Exception {
		getRequest().getSession().setAttribute("state", "1");
		String redirectUrl = AppConfig.getProperty(Constans.WX.SOCIAL_LOGIN_REDIRECT_URI);
		log.info("===================redirectUrl " + redirectUrl);
		String url = new WeChatDevUtils().getAuthrUrl(redirectUrl, "snsapi_userinfo");
		log.info("===================url " + url);
		getResponse().sendRedirect(url);		
	}
	
	@RequestMapping("/qq")
	public void qq() throws IOException, QQConnectException {
		getResponse().setContentType("text/html;charset=utf-8");
		getResponse().sendRedirect(new com.qq.connect.oauth.Oauth().getAuthorizeURL(getRequest()));
	}
	
	@RequestMapping("/faceBook")
	public String faceBook() {
		
		return "";
	}
	
	private void updateProperties() {
		WeiboConfig.updateProperties("client_ID", AppConfig.getProperty(Constans.WEIBO.SOCIAL_LOGIN_CLIENT_ID));
		WeiboConfig.updateProperties("client_SERCRET", AppConfig.getProperty(Constans.WEIBO.SOCIAL_LOGIN_CLIENT_SERCRET));
		WeiboConfig.updateProperties("redirect_URI", AppConfig.getProperty(Constans.WEIBO.SOCIAL_LOGIN_REDIRECT_URI));
	}
	
}
