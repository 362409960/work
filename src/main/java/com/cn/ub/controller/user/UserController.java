package com.cn.ub.controller.user;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.ub.common.MD5;
import com.cn.ub.common.PageUtils;
import com.cn.ub.common.UuidUtils;
import com.cn.ub.entry.user.User;
import com.cn.ub.service.user.UserService;
import com.github.pagehelper.PageHelper;

@Controller
@RequestMapping("/user")
public class UserController {
	
	private static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/index")
	public String toIndex(HttpServletRequest request, HttpServletResponse response)throws Exception{
		return "/user/list";
	}
	
	/**
	 * 查询列表
	 * @param pageNumber
	 * @param pageSize
	 * @param user
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/list")
	@ResponseBody
	public PageUtils<User> list(@RequestParam(value = "page", defaultValue = "0") int pageNumber,
			@RequestParam(value = "rows", defaultValue = "10") int pageSize, User user,
			HttpServletRequest request,HttpServletResponse response) {		
		try {
			PageHelper.startPage(pageNumber, pageSize, true);
			List< User> infos = userService.getList(user);
			PageUtils<User> page = new PageUtils<User>(infos);
			return page;
		} catch (Exception e) {
			logger.warn("用户信息:",e);
		}
		return null;
	}

	/**
	 * 指向添加界面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/create")
	public String toAdd(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {

		return "user/add";
	}

	/**
	 * 保存
	 * @param user
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public String saveProductInfo(User user, HttpServletRequest request, HttpServletResponse response) {
		try {
			user.setId(UuidUtils.getUuid());
			user.setState("1");
			user.setPassword(MD5.Md5("123456"));
			user.setCreateTime(new Date());
			userService.save(user);
		} catch (Exception e) {

			return "failure";
		}
		return "success";
	}

	/**
	 * 指向编辑界面
	 * @param request
	 * @param response
	 * @param model
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/toEdit")
	public String toEdit(HttpServletRequest request, HttpServletResponse response, ModelMap model,
			@RequestParam(value = "id") String id) throws Exception {
		User user = userService.getObjById(id);
		model.addAttribute("user", user);
		return "user/edit";
	}

	/**
	 * 编辑界面
	 * @param User
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public String editProductInfo(User user, HttpServletRequest request) {
		try {
             user.setUpdateTime(new Date());
             userService.update(user);
		} catch (Exception e) {
			return "failure";
		}
		return "success";
	}
	/**
	 * 删除数据
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public String deleteProductInfo(@RequestParam(value = "id") String id) {		
		try {
			userService.deleteById(id);
		} catch (Exception e) {
			logger.warn("删除信息发布异常:",e);
			return "failure";
		}
		return "success";
	}

}
