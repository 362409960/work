package com.cn.ub.service.patient;

import java.util.List;

import com.cn.ub.entry.patient.PatientPicture;
import com.cn.ub.service.BaseService;

public interface PatientPictureSevice extends BaseService<PatientPicture> {
	
	List<PatientPicture> getPIcList(String id)throws Exception;

}
