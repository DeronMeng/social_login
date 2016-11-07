package com.social.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/social")
public class SocialController {
	
	
	@RequestMapping("/index")
	public String index() {
		
		return "social/index";
	}

	@RequestMapping("/weibo")
	public String weibo() {
		
		return "";
	}
}
