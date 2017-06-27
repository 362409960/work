package com.cn.ub.common;



import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;


import net.sf.json.JSONObject;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

public class QiniuUtil {
		 
	/**
	 
	  * @version V1.0  
	  * @Title: upLoadFileByPath  
	  * @Description: 根据文件路径和工作空间上传文件到七牛 
	  * @param qiniuAccessKey  云存储账号key	
	  * @param qiniuSecretKey 云存储账号秘钥
	  * @param uploadSpace 文件的上传空间
	  * @param filePath 上传文件路径
	  * @throws QiniuException   
	  * @return String  在七牛上的文件名称
	  * @throws
	 */
	public static String upLoadFileByPath(String qiniuAccessKey,String qiniuSecretKey,String uploadSpace,String filePath) throws QiniuException{
		File file = new File(filePath);
		//文件不存在或者不是文件，返回null
		if(!file.exists() || !file.isFile()){ 
			return null;
		}
		 
		// 取uuuid作为文件保存在七牛上的文件名称
		String key = UuidUtils.getUuid();  
		
		//获取文件名
		String fileName = file.getName();
		//获取文件后缀
		if(fileName.contains(".")){
			key = key + fileName.substring(fileName.lastIndexOf("."));
		}
		
		 	
		//产生实例
		Auth auth = Auth.create(qiniuAccessKey,qiniuSecretKey);
		 
		//创建上传对象
		UploadManager uploadManager = new UploadManager(null);
		  
		//获取上传token
		String token =  auth.uploadToken(uploadSpace);
	 
		//上传文件
		Response res = uploadManager.put(file.getAbsoluteFile(), key, token);
		//解析结果
		JSONObject reqJson = JSONObject.fromObject(res.bodyString());
		//返回结果中key不会空则上传成功，返回七牛上的文件名称,保存到图片表中
		if(StringUtils.isNotEmpty((String)reqJson.get("key"))){
			return key;
		}else{
			return null;
		}
	}
 	
	/**
	  
	  * @version V1.0  
	  * @Title: upLoadFileByByte  
	  * @Description: 跟字节数组上传文件，并返回图片id 
	  * @param qiniuAccessKey  云存储账号key	
	  * @param qiniuSecretKey 云存储账号秘钥
	  * @param uploadSpace 文件的上传空间
	  * @param fileName 文件名称
	  * @param data 文件data字节数组
	  * @throws QiniuException   
	  * @return String  在七牛上的文件名称
	  * @throws
	 */
	public static String upLoadFileByByte(String qiniuAccessKey,String qiniuSecretKey,String uploadSpace,String fileName,byte[] data) throws QiniuException {
	 
		// 取uuuid作为文件保存在七牛上的文件名称
		String key = UuidUtils.getUuid();  
		 
		//获取文件后缀
		if(fileName.contains(".")){
			key = key + fileName.substring(fileName.lastIndexOf("."));
		}
		
		//产生实例
		Auth auth = Auth.create(qiniuAccessKey, qiniuSecretKey);
		 
		//创建上传对象
		UploadManager uploadManager = new UploadManager(null);
		  
		//根据工作空间获取上传token
		String token =  auth.uploadToken(uploadSpace);
		//上传文件	
		Response res =uploadManager.put(data, key, token);
		 
		//解析结果
		JSONObject reqJson = JSONObject.fromObject(res.bodyString());
		//返回结果中key不会空则上传成功，返回七牛上的文件名称
		if(StringUtils.isNotEmpty((String)reqJson.get("key"))){
			return key;
		}else{
			return null;
		}
	}
	

	/**
	
	  * @version V1.0  
	  * @Title: getToken  
	  * @Description:  根据云存储账号key、秘钥和获取token 
	  * @param qiniuAccessKey  云存储账号key	
	  * @param qiniuSecretKey 云存储账号秘钥
	  * @param uploadSpace 文件的上传空间
	  * @throws QiniuException   
	  * @return String  token
	  * @throws
	 */
	public static String getToken(String qiniuAccessKey,String qiniuSecretKey,String uploadSpace) throws QiniuException {
		//产生实例
		Auth auth = Auth.create(qiniuAccessKey, qiniuSecretKey);		 
		//创建上传对象
		UploadManager uploadManager = new UploadManager(null);		  
		//根据工作空间获取上传token
		String token =  auth.uploadToken(uploadSpace);
		return token;		
	}	
}
