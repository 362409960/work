package com.cn.ub.controller.patient;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.ub.common.PageUtils;
import com.cn.ub.entry.patient.CaseInfo;
import com.cn.ub.entry.user.User;
import com.cn.ub.entry.user.UserVO;
import com.cn.ub.service.patient.CaseInfoService;
import com.cn.ub.service.patient.PatientPictureSevice;
import com.github.pagehelper.PageHelper;

@Controller
@RequestMapping("/case")
public class CaseInfocontroller {
	
	@Autowired
	private CaseInfoService caseInfoService;
	@Autowired
	private PatientPictureSevice patientPictureSevice;
	
	@RequestMapping(value="/index")
	public String toIndex(HttpServletRequest request, HttpServletResponse response)throws Exception{
		return "/case/list";
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
			@RequestParam(value = "rows", defaultValue = "10") int pageSize, CaseInfo caseInfo,
			HttpServletRequest request,HttpServletResponse response) {		
		try {
			PageHelper.startPage(pageNumber, pageSize, true);
			
			//PageUtils<User> page = new PageUtils<User>();
			//return page;
		} catch (Exception e) {
			
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
	public String saveProductInfo(CaseInfo caseInfo, HttpServletRequest request, HttpServletResponse response) {
		try {
			
		
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
	public String editProductInfo(CaseInfo caseInfo, HttpServletRequest request) {
		try {
		
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
			
		} catch (Exception e) {
			
			return "failure";
		}
		return "success";
	}
	

}
