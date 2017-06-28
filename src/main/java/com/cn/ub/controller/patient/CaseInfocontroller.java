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
import com.cn.ub.entry.patient.PatientPicture;
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
		return "case/list";
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
	public PageUtils<CaseInfo> list(@RequestParam(value = "page", defaultValue = "0") int pageNumber,
			@RequestParam(value = "rows", defaultValue = "10") int pageSize, CaseInfo caseInfo,
			HttpServletRequest request,HttpServletResponse response) {		
		try {
			PageHelper.startPage(pageNumber, pageSize, true);
			List<CaseInfo> info = caseInfoService.getList(caseInfo);
			PageUtils<CaseInfo> page = new PageUtils<CaseInfo>(info);
			return page;
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

		return "case/add";
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
			caseInfoService.save(caseInfo);
		
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
		
		 CaseInfo caseInfo = caseInfoService.getObjById(id);
		 List<PatientPicture> patiList = patientPictureSevice.getPIcList(id);
		 model.addAttribute("caseInfo", caseInfo);
		 model.addAttribute("patiList", patiList);
	
		return "case/edit";
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
		caseInfoService.update(caseInfo);;
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
			caseInfoService.deleteById(id);
		} catch (Exception e) {
			
			return "failure";
		}
		return "success";
	}
	

}
