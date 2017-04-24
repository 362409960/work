package com.cn.ub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	
	
	
	@RequestMapping("/index")
	public String viewPage()throws Exception{
		return "index";
	}

}
