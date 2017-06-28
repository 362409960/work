package com.cn.ub.service.impl.patient;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


import com.cn.ub.common.UuidUtils;
import com.cn.ub.dao.patient.CaseInfoDAO;
import com.cn.ub.dao.patient.PatientPictureDAO;
import com.cn.ub.entry.patient.CaseInfo;
import com.cn.ub.entry.patient.PatientPicture;

import com.cn.ub.service.patient.CaseInfoService;
import com.cn.ub.service.user.PicInfoService;

@Service
public class CaseInfoServiceImpl implements CaseInfoService {

	@Value("${qiniu_pic_space}")
	private String qiniuProductPicSpace;
	// 七牛图片上传地址
	@Value("${qiniu_pic_url}")
	private String qiniuProducturl;

	@Autowired
	private CaseInfoDAO caseInfoDAO;

	@Autowired
	private PatientPictureDAO patientPictureDAO;
	@Autowired
	private PicInfoService picInfoServiceImpl;

	@Override
	public void save(CaseInfo obj) throws Exception {
		String caseId = UuidUtils.getUuid();
		obj.setId(caseId);
		caseInfoDAO.save(obj);
		// 保存图片
		if (null != obj.getPicId() && obj.getPicId().length > 0) {
			for (PatientPicture p : obj.getPicId()) {
				String pid = picInfoServiceImpl.upLoadImgFile(p.getPicFile(), qiniuProductPicSpace, "1");
				p.setCaseId(caseId);
				p.setId(UuidUtils.getUuid());
				p.setPicId(pid);
				p.setState("0");
				p.setCreateTime(new Date());
				patientPictureDAO.save(p);
			}
		}

	}

	@Override
	public void update(CaseInfo obj) throws Exception {
		caseInfoDAO.update(obj);
		if (null != obj.getPicId() && obj.getPicId().length > 0) {
			String picIds = "";
			for (PatientPicture p : obj.getPicId()) {
				if (StringUtils.isEmpty(p.getId()) && p.getPicFile() != null) {
					String id = UuidUtils.getUuid();
					String pid = picInfoServiceImpl.upLoadImgFile(p.getPicFile(), qiniuProductPicSpace, "1");
					p.setCaseId(obj.getId());
					p.setId(id);
					p.setPicId(pid);
					p.setState("0");
					p.setCreateTime(new Date());
					patientPictureDAO.save(p);
					picIds += id + ",";
				} else {
					if (!StringUtils.isEmpty(p.getId())) {
						patientPictureDAO.update(p);
						picIds += p.getId() + ",";
					}

				}
			}
			//判断是否为空，为空这通过商品id查询删除所有的相关图片
			if(!StringUtils.isEmpty(picIds)){
				picIds = picIds.substring(0, picIds.length()-1);
				String  [] picId  = picIds.split(",");
				Map<String,Object> map = new HashMap();
				map.put("ids", picId);
				map.put("pouductId", obj.getId());
				//先查询，把7牛的id查询出来
			   List<PatientPicture> productPicList = patientPictureDAO.findByMap(map);
			   //得到7牛id
			   String sysP = "";
			    if(null != productPicList && productPicList.size() > 0){
			    	for(PatientPicture pic:productPicList){
			    		sysP += pic.getId() + ",";
			    	}
			    }
			    if(!StringUtils.isEmpty(sysP)){
			    	sysP=sysP.substring(0, sysP.length()-1);
			    	String [] sysPid = sysP.split(",");
			    	List<String> ids = new ArrayList<String>();
			    	for(String s:sysPid){
			    		ids.add(s);
			    	}
			    	picInfoServiceImpl.delete(ids);
			    	patientPictureDAO.deleteByIds(map);
			    }
			}
			
		}

	}

	@Override
	public void deleteById(String id) throws Exception {
		caseInfoDAO.deleteById(id);
		patientPictureDAO.deleteById(id);

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
