package com.cn.ub.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.ub.entry.user.User;
import com.cn.ub.service.user.UserService;

@Controller
public class LoginController {
	

	@Autowired
	private UserService userService;
	
	@RequestMapping("/index")
	public String viewPage()throws Exception{
		return "login";
	}
	@RequestMapping(value= "/login")
	@ResponseBody
	public Map<String,Object> loginView(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Map<String, Object> map = new HashMap<>();
		String username = request.getParameter("username");
		
		String pwd = request.getParameter("pwd");
		
		User user = userService.getUser(username);
		
		if (user == null){
			map.put("error", "用户名不存在");
			map.put("code", 3);
		} else if (!pwd.equals(user.getPassword())){
			map.put("error", "密码错误");
			map.put("code", 3);
		} else {
			request.setAttribute("user", user);
			if ("0".equals(user.getUserType())){
				map.put("suc", "登陆成功，进入管理员界面");
				map.put("code", 0);
			}
			if ("1".equals(user.getUserType())){
				map.put("suc", "登陆成功，进入上次用户界面");
				map.put("code", 1);
			}
			if ("2".equals(user.getUserType())){
				map.put("suc", "登陆成功，进入查看用户界面");
				map.put("code", 2);
			}
		}
		
		return map;
	}

}
