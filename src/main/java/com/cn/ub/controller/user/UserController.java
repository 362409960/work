package com.cn.ub.controller.user;

import java.io.PrintWriter;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

import com.cn.ub.entry.user.User;
import com.cn.ub.entry.user.UserVO;
import com.cn.ub.service.user.UserService;
import com.cn.ub.service.user.UserUtilsService;
import com.github.pagehelper.PageHelper;

@Controller
@RequestMapping("/user")
public class UserController {
	
	private static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;
	@Autowired
	private UserUtilsService userUtilsService;
	
	@RequestMapping(value="/index")
	public String toIndex(HttpServletRequest request, HttpServletResponse response)throws Exception{
		return "user/list";
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
	public String saveProductInfo(UserVO uservo, HttpServletRequest request, HttpServletResponse response) {
		try {
			
			userUtilsService.saveUserVO(uservo);
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
		UserVO uservo = userUtilsService.getUserVO(id);
		List<User> userList = userService.getUserList("2");
		model.addAttribute("uservo", uservo);
		model.addAttribute("userList", userList);
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
	public String editProductInfo(UserVO uservo, HttpServletRequest request) {
		try {
			userUtilsService.updateUserVO(uservo);
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
	/**
	 * 下拉框选择用户
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/checkName", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> checkName(@RequestParam(value = "id") String id) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		try {
			List<User> user = userService.getUserList(id);
			map.put("user", user);
		} catch (Exception e) {
			logger.warn("删除信息发布异常:",e);
			return map;
		}
		return map;
	}
	@RequestMapping(value = "/toUpPwd", method = RequestMethod.GET)
    public String toUpPwd(ModelMap model,HttpServletRequest request,HttpServletResponse response)throws Exception{
		User user = (User) request.getSession().getAttribute("user");
		model.addAttribute("user", user);
    	return "password";
    }
	
	@RequestMapping(value = "/updatPwd")
	public void updatPwd(HttpServletRequest request,HttpServletResponse response){
		response.setContentType("text/html;charset=utf-8"); //指定输出内容类型和编码
		PrintWriter out = null;
		boolean isValid = true;
    	try {
    		out = response.getWriter();//获取输出口
    		String id = request.getParameter("id");
        	String password = request.getParameter("newsPwd");
            User user = new User();
            user.setId(id);
            user.setPassword(MD5.Md5(password));
            user.setUpdateTime(new Date());
            userService.update(user);
        	
		} catch (Exception e) {
			isValid = false;
		}
		out.println(net.sf.json.JSONArray.fromObject(isValid));//返回结果
        out.flush();
        out.close();
	}


}
