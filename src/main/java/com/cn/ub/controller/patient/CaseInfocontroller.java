package com.cn.ub.controller.patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cn.ub.service.patient.CaseInfoService;
import com.cn.ub.service.patient.PatientPictureSevice;

@Controller
@RequestMapping("/case")
public class CaseInfocontroller {
	
	@Autowired
	private CaseInfoService caseInfoService;
	@Autowired
	private PatientPictureSevice patientPictureSevice;
	

}
