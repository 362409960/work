package com.cn.ub.service.impl.user;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cn.ub.common.UuidUtils;
import com.cn.ub.dao.user.PicInfoDAO;
import com.cn.ub.entry.user.PicInfo;
import com.cn.ub.service.user.PicInfoService;
import com.qiniu.common.QiniuException;

public class PicInfoServiceImpl implements PicInfoService {

	//记录log	 
		private  final Logger logger = LoggerFactory.getLogger(PicInfoServiceImpl.class);
		
		@Autowired
		//图片操作dao
		private PicInfoDAO sysPictureInfoDAO;
		
		
		@Value("${qiniu_accessKey}")
		//云存储账号key	
		private String qiniuAccessKey;
		
		@Value("${qiniu_secretKey}")
		//云存储账号秘钥
		private String qiniuSecretKey;
		  
		
		@Value("${qiniu_product_pic_space}")
		//商品图片空间
		private String qiniuProductPicSpace;
		  
		@Value("${qiniu_sys_pic_space}")
		//其他图片空间
		private String qiniuSysPicSpace;
		  
		
		@Value("${qiniu_product_pic_url}")
		//商品图片访问域名
		private String qiniuProductPicUrl;
		  
		@Value("${qiniu_sys_pic_url}")
		//系统图片访问域名
		private String qiniuSysPicUrl;
		
		
		 
		
		
		/**
		  * @copyright：丰巨泰科
		  * @version V1.0  
		  * @Title: save  
		  * @Description: 保存图片,返回图片id集合
		  * @param picNameList  图片集合
		  * @param funType 业务类型
		  * @return List  返回类型
		  * @throws
		 */
		@Override
		public List save(List<String> picNameList,String funType ){
			ArrayList<String> picIdList = new ArrayList<String>();
			for (Iterator iterator = picNameList.iterator(); iterator.hasNext();) {
				String picPath = (String) iterator.next();
				if(StringUtils.isNotEmpty(picPath)){
					save(picPath,funType); 
				} 
			}  
			return picIdList;
		}
		
		/**
		  * @copyright：丰巨泰科
		  * @version V1.0  
		  * @Title: save  
		  * @Description: 保存图片
		  * @param picPath  图片路径
		  * @param funType 业务类型
		  * @throws
		 */
		@Override
		public PicInfo save(String picPath,String funType ){
			if(StringUtils.isNotEmpty(picPath)){
				PicInfo info = new PicInfo();
				info.setId(UuidUtils.getUuid());
				info.setCreateTime(new Date());
//				info.setCreateUserId(user.getId());
				info.setPicPath(picPath);
				
				info.setDisabled(0);
				sysPictureInfoDAO.save(info);
				return info;
			}else{
				return null;
			}
		}
		
		
		
		
		@Override
		public void delete(List<String> ids) {
			sysPictureInfoDAO.delete(ids);
		}
		
		/**
		  * @copyright：丰巨泰科
		  * @version V1.0  
		  * @Title: delete  
		  * @Description: 假删除把状态改成不能使用状态
		  * @param ids  图片id 
		  * @return void  返回类型
		  * @throws
		 */
		@Override
		public void deleteById(String id) {
			sysPictureInfoDAO.deleteById(id);
		}
		
		/**
		  * @copyright：丰巨泰科
		  * @version V1.0  
		  * @Title: upLoad  
		  * @Description: 根据文件路径和工作空间上传文件到七牛
		  * @param filePath 文件路径
		  * @param uploadSpace 七牛的空间名称
		  * @return String  图片表的id
		  * @throws
		 */
		@Override
		public String upLoadPicFile(String filePath,String uploadSpace,String funType){
			File file = new File(filePath);
			String fileId = "";
			//文件不存在或者不是文件，返回null
			if(!file.exists() || !file.isFile()){ 
				return null;
			}
			
			try {
				//上传文件到qiniu
				String upFilePath = QiniuUtil.upLoadFileByPath(qiniuAccessKey, qiniuSecretKey, uploadSpace,filePath);
				//保存到系统图片表
				if(StringUtils.isNotEmpty(upFilePath)){
					PicInfo picInfo = save(upFilePath,funType);
					fileId = picInfo.getId();
				}
			 } catch (QiniuException e) {
				 logger.warn("文件上传失败",e);
			 	return fileId;
			 }
			return fileId;
		}
		
		
		/**
		  * @copyright：丰巨泰科
		  * @version V1.0  
		  * @Title: upLoadImgFiles  
		  * @Description: 上传多个文件
		  * @param request
		  * @param uploadSpace 工作空间
		  * @return List<String>  上传成功的图片id
		  * @throws
		 */
		@Override
		public List<String> upLoadImgFiles(MultipartHttpServletRequest request,String uploadSpace,String funType) {
				List<String> picIds = new ArrayList<String>();
			try {
				Iterator<String> itr = request.getFileNames();
				MultipartFile mpf = null;
				 //循环上传文件
				while (itr.hasNext()) {
					mpf = request.getFile(itr.next());
					if(mpf == null || StringUtils.isEmpty(mpf.getOriginalFilename())){
						continue;
					}
					//获取文件名
					String fileName = mpf.getOriginalFilename();
					//获取文件后缀
					String subkey= fileName.substring(fileName.lastIndexOf("."));
					//重新生成文件名称
					String name = String.valueOf(new Date().getTime());
					String key = name + subkey;  
					byte[] data;
					data = mpf.getBytes();
					
					//上传文件到七牛
					String upFilePath = QiniuUtil.upLoadFileByByte(qiniuAccessKey, qiniuSecretKey, uploadSpace, fileName, data);
					
					//保存到系统图片表
					if(StringUtils.isNotEmpty(upFilePath)){
						PicInfo picInfo = save(upFilePath,funType);
						picIds.add(picInfo.getId());
					}	 
				}
				return picIds;
			} catch (IOException e) {
				logger.error("图片上传异常",e);
			}
			return picIds;
		}
		
		/**
		  * @copyright：丰巨泰科
		  * @version V1.0  
		  * @Title: upLoadImgFile  
		  * @Description: 上传单个图片，并返回图片id
		  * @param mpf 图片对象
		  * @param uploadSpace 图片空间
		  * @param funType 业务类型
		  * @return String  上传成功的图片id 
		 */
		@Override
		public String upLoadImgFile(MultipartFile mpf,String uploadSpace,String funType) {
			String picId = "";
			try {   
				if(mpf == null || StringUtils.isEmpty(mpf.getOriginalFilename())){
					return picId;
				}
				//获取文件名
				String fileName = mpf.getOriginalFilename();
				//获取文件后缀
				String subkey= fileName.substring(fileName.lastIndexOf("."));
				//重新生成文件名称
				String name = String.valueOf(new Date().getTime());
				String key = name + subkey;  
				byte[] data;
				data = mpf.getBytes();
				
				//上传文件到七牛
				String upFilePath = QiniuUtil.upLoadFileByByte(qiniuAccessKey, qiniuSecretKey, uploadSpace, fileName, data);
				
				//保存到系统图片表，返回七牛上的图片id		 
				if(StringUtils.isNotEmpty(upFilePath)){
					PicInfo picInfo = save(upFilePath,funType);
					picId = picInfo.getId();
				}			 
			} catch (IOException e) {
				logger.error("图片上传异常",e);
				return picId;
			}
			return picId;
		}
	 

		@Override
		public String upLoadImgFileReturnAddress(MultipartFile mpf, String uploadSpace, String funType) {
			String picUrl = "";
			try {   
				if(mpf == null || StringUtils.isEmpty(mpf.getOriginalFilename())){
					return picUrl;
				}
				//获取文件名
				String fileName = mpf.getOriginalFilename();		
				byte[] data;
				data = mpf.getBytes();
				
				//上传文件到七牛
				String upFilePath = QiniuUtil.upLoadFileByByte(qiniuAccessKey, qiniuSecretKey, uploadSpace, fileName, data);
				 
				//返回结果中key不会空则上传成功，返回七牛上的文件名称
				if(StringUtils.isNotEmpty(upFilePath)){
					SysPictureInfo picInfo = save(upFilePath,funType);
					picUrl = picInfo.getPicPath();
				}			 
			} catch (IOException e) {
				logger.error("图片上传异常",e);
				return picUrl;
			}
			return picUrl;
		}

		@Override
		public HashMap getQiniuconfig(String funType) {
			String uploadSpace ="";
			HashMap configMap = null;
			String accessUrl = "";
			if("1".equals(funType)){
				//商品图片空间
				uploadSpace = qiniuProductPicSpace;
				accessUrl = qiniuProductPicUrl;
			}else if("2".equals(funType)){
				//其他图片空间
				uploadSpace = qiniuSysPicSpace;
				accessUrl = qiniuSysPicUrl;
			} 
			if(StringUtils.isNotEmpty(uploadSpace)){
				try {
					String token = QiniuUtil.getToken(qiniuAccessKey, qiniuSecretKey, uploadSpace);
					configMap = new HashMap<String,String>();
					configMap.put("accessKey", qiniuAccessKey);
					configMap.put("secretKey", qiniuSecretKey);
					configMap.put("accessUrl", accessUrl);	
					configMap.put("token", token);				
				} catch (QiniuException e) {
					logger.error("回去图片上传参数及token传异常",e);
				}
			}
			return configMap;
		}
		

}
