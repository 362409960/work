package com.cn.ub.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.ub.entry.user.User;
import com.cn.ub.service.user.UserService;

@Controller
public class LoginController {
	

	@Autowired
	private UserService userService;
	
	@RequestMapping("/toLogin")
	public String viewPage()throws Exception{
		return "login";
	}
	@RequestMapping(value= "/login")
	@ResponseBody
	public Map<String,Object> loginView(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Map<String, Object> map = new HashMap<>();
		String username = request.getParameter("name");
		
		String pwd = request.getParameter("password");
		
		User user = userService.getUser(username);
		
		if (user == null){
			map.put("error", "用户名不存在");
			map.put("code", 3);
			map.put("flag", "false");
		} else if (!pwd.equals(user.getPassword())){
			map.put("error", "密码错误");
			map.put("code", 3);
			map.put("flag", "false");
		} else {
			request.getSession().setAttribute("user", user);
			if ("0".equals(user.getUserType())){
				map.put("success", "登陆成功，进入管理员界面");
				map.put("code", 0);
				map.put("flag", "true");
			}
			if ("1".equals(user.getUserType())){
				map.put("success", "登陆成功，进入上次用户界面");
				map.put("code", 1);
				map.put("flag", "true");
			}
			if ("2".equals(user.getUserType())){
				map.put("success", "登陆成功，进入查看用户界面");
				map.put("code", 2);
				map.put("flag", "true");
			}
		}
		
		return map;
	}
	
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/toIndex")
	public String index(HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws Exception {
		    String code = request.getParameter("code");
		    User user = (User) request.getSession().getAttribute("user");
		    if(null == user){
		    	return "login";
		    }
		    model.addAttribute("username", user.getUsername());
		    if ("0".equals(code)){
		    	model.addAttribute("action", "/user/index");
		    } else if ("1".equals(code)){
		    	model.addAttribute("action", "/case/index");
		    } else {
		    	model.addAttribute("action", "/case/upIndex");
		    }
		     

		return "index";
	}
	@RequestMapping(value = "/toLogOut")
	public String toLogOut(HttpServletRequest request, HttpServletResponse response)throws Exception{
		request.getSession().removeAttribute("user");
		return "redirect:toLogin";
	}
	

}
