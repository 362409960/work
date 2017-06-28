package com.cn.ub.service.impl.patient;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cn.ub.dao.patient.PatientPictureDAO;
import com.cn.ub.dao.user.PicInfoDAO;
import com.cn.ub.entry.patient.PatientPicture;
import com.cn.ub.service.patient.PatientPictureSevice;
@Service
public class PatientPictureSeviceImpl implements PatientPictureSevice {
	
	@Value("${qiniu_pic_space}")
	private String qiniuProductPicSpace;
	//七牛图片上传地址
	@Value("${qiniu_pic_url}")
	private String qiniuProducturl;
	
	@Autowired
	private PatientPictureDAO patientPictureDAO;
	@Autowired
	private PicInfoDAO picInfoDAO;

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

	@Override
	public List<PatientPicture> getPIcList(String id) throws Exception {
		List<PatientPicture> list = patientPictureDAO.getPIcList(id);
		for (PatientPicture productPic : list) {
			if(StringUtils.isNotEmpty(productPic.getfUrl())){
				//设置
				productPic.setfUrl(qiniuProducturl + productPic.getfUrl());
			}
		}
		
		return list;
	}

}
