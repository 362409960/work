package com.cn.ub.dao.user;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cn.ub.entry.user.PicInfo;

public interface PicInfoDAO {
	
	 /**
	  * 保存图片
	  * @param sysPictureInfo
	  */
	 void save(PicInfo picInfo);
	 
	 void delete(@Param("ids") List<String> ids);
	 
	 void deleteById(@Param("id") String id);
	 /**
	  * 
	   * @version V1.0  
	   * @Title: getPicName  
	   * @Description: 根据图片Id，查询图片名称
	   * @param picId  图片的主键Id
	   * @return  图片的名称
	   * @return String  返回类型
	   * @throws
	  */
	 String getPicName(String picId);

}
