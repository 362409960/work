package com.cn.ub.service.impl.patient;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.ub.dao.patient.PatientPictureDAO;
import com.cn.ub.entry.patient.PatientPicture;
import com.cn.ub.service.patient.PatientPictureSevice;
@Service
public class PatientPictureSeviceImpl implements PatientPictureSevice {
	
	@Autowired
	private PatientPictureDAO patientPictureDAO;

	@Override
	public void save(PatientPicture obj) throws Exception {
		patientPictureDAO.save(obj);

	}

	@Override
	public void update(PatientPicture obj) throws Exception {
		patientPictureDAO.update(obj);

	}

	@Override
	public void deleteById(String id) throws Exception {
		patientPictureDAO.deleteById(id);

	}

	@Override
	public List<PatientPicture> getList(PatientPicture obj) throws Exception {
	
		return patientPictureDAO.getList(obj);
	}

	@Override
	public PatientPicture getObjById(String id) throws Exception {
		
		return patientPictureDAO.getObjById(id);
	}

}
