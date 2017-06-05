package com.cn.ub.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.ub.entry.user.User;
import com.cn.ub.service.patient.CaseInfoService;
import com.cn.ub.service.patient.PatientPictureSevice;
import com.cn.ub.service.user.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private CaseInfoService caseInfoService;

	/**
	 * 管理员界面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/controlUserInterface")
	public String controlUserInterface(HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws Exception {

		return "user/user_control";
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/uploadUserInterface")
	public String uploadUserInterface(HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws Exception {

		return "case/user_upload";
	}

	/**
	 * 查看用户界面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/viewUserInterface")
	public String viewUserInterface(HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws Exception {

		return "case/user_view";
	}

	@RequestMapping(value = "/create")
	public String toAdd(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {

		return "";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public String saveProductInfo(User user, HttpServletRequest request, HttpServletResponse response) {
		try {

		} catch (Exception e) {

			return "FAILURE";
		}
		return "SUCCESS";
	}

	@RequestMapping(value = "/toEdit")
	public String toEdit(HttpServletRequest request, HttpServletResponse response, ModelMap model,
			@RequestParam(value = "id") String id) throws Exception {

		return "";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public String editProductInfo(User User, HttpServletRequest request) {
		try {

		} catch (Exception e) {

			return "FAILURE";
		}
		return "SUCCESS";
	}

}
