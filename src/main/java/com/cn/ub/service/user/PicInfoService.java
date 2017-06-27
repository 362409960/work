package com.cn.ub.service.user;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cn.ub.entry.user.PicInfo;

public interface PicInfoService {
	
	/**
	
	  * @version V1.0  
	  * @Title: getQiniuconfig  
	  * @Description: 根据业务类型获取七牛上传参数map 
	  * @param funType 业务类型： 1商品图片，2其他
	  * @return HashMap  返回类型
	  * @throws
	 */
	HashMap getQiniuconfig(String funType);
		
	
	
	/**
	 
	  * @version V1.0  
	  * @Title: delete  
	  * @Description: 假删除把状态改成不能使用状态
	  * @param ids  图片id 
	  * @return void  返回类型
	  * @throws
	 */
	void delete(List<String> ids);
	
	/**
	
	  * @version V1.0  
	  * @Title: delete  
	  * @Description: 根据id假删除把状态改成不能使用状态
	  * @param ids  图片id 
	  * @return void  返回类型
	  * @throws
	 */
	void deleteById(String id);
	/**
	 
	  * @version V1.0  
	  * @Title: save  
	  * @Description:  批量新增图片 
	  * @param picNameList 图片地址list
	  * @param funType 所属业务类型： 1商品图片，2其他
	  * @return List<String>  返回类型
	  * @throws
	 */
	List<String> save(List<String> picNameList,String funType);
	
	/**
	
	  * @version V1.0  
	  * @Title: save  
	  * @Description: 保存图片
	  * @param picPath  图片路径
	  * @param funType 业务类型
	  * @throws
	 */
	public PicInfo save(String picPath,String funType );
	
	/**
	 
	  * @version V1.0  
	  * @Title: upLoad  
	  * @Description: 根据文件路径和工作空间上传文件到七牛
	  * @param filePath 文件路径
	  * @param uploadSpace 七牛的空间名称
	  * @return String  图片表的id
	  * @throws
	 */
	public String upLoadPicFile(String filePath,String uploadSpace,String funType);
	
	

	/**
	  
	  * @version V1.0  
	  * @Title: getPicIds  
	  * @Description: 公共上传方法，上传后返回sys_picture_info图片id 
	  * @param request
	  * @param uploadToken
	  * @return   
	  * @return List<String>  返回类型
	  * @throws
	 */
	List<String> upLoadImgFiles(MultipartHttpServletRequest request,String uploadSpace,String funType); 

	/**
	  
	  * @version V1.0  
	  * @Title: upLoadImgFile  
	  * @Description: 上传单个图片，并返回图片id
	  * @param mpf 图片对象
	  * @param uploadSpace 图片空间
	  * @param funType 业务类型
	  * @return String  上传成功的图片id 
	 */
	
	String upLoadImgFile(MultipartFile mpf,String uploadSpace,String funType);
	
	/**
	 * 
	  
	  * @version V1.0  
	  * @Title: upLoadImgFileReturnAddress  
	  * @Description: TODO(上传单个图片，并返回图片相对地址) 
	  * @param mpf
	  * @param uploadSpace
	  * @param funType
	  * @return   
	  * @return String  返回类型
	  * @throws
	 */
	String upLoadImgFileReturnAddress(MultipartFile mpf,String uploadSpace,String funType);

}
