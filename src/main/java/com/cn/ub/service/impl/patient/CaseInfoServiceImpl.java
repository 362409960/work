package com.cn.ub.service.impl.patient;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.ub.dao.patient.CaseInfoDAO;
import com.cn.ub.entry.patient.CaseInfo;
import com.cn.ub.service.patient.CaseInfoService;
@Service
public class CaseInfoServiceImpl implements CaseInfoService{
	@Autowired
	private CaseInfoDAO caseInfoDAO;

	@Override
	public void save(CaseInfo obj) throws Exception {
		caseInfoDAO.save(obj);
		
	}

	@Override
	public void update(CaseInfo obj) throws Exception {
		caseInfoDAO.update(obj);
		
	}

	@Override
	public void deleteById(String id) throws Exception {
		caseInfoDAO.deleteById(id);
		
	}

	@Override
	public List<CaseInfo> getList(CaseInfo obj) throws Exception {
		
		return caseInfoDAO.getList(obj);
	}

	@Override
	public CaseInfo getObjById(String id) throws Exception {
		
		return caseInfoDAO.getObjById(id);
	}

}
