package com.cn.ub.dao.patient;

import java.util.List;
import java.util.Map;

import com.cn.ub.dao.BaseDAO;
import com.cn.ub.entry.patient.PatientPicture;

public interface PatientPictureDAO extends BaseDAO<PatientPicture> {
	
	List<PatientPicture> getPIcList(String id)throws Exception;
	
	List<PatientPicture> findByMap(Map<String, Object> map)throws Exception; 
	
	void deleteByIds(Map<String, Object> map) throws Exception;

}
