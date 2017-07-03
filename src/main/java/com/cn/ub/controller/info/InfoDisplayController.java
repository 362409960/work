package com.cn.ub.controller.info;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("/info")
public class InfoDisplayController {
	
	
	@RequestMapping(value="/index")
	public String toIndex(HttpServletRequest request, HttpServletResponse response)throws Exception{
		return "info/index";
	}
	

	@RequestMapping(value = "/studyList", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> studyList(HttpServletRequest request) {
		// 最外面json
		Map<String, Object> map =  new HashMap<>();

		try {
			JSONArray ja = new JSONArray();

			JSONObject jn = new JSONObject();
			jn.put("patientName", "唐远坤");
			jn.put("patientId", "10120161001559716");
			jn.put("studyDate", "20010108");
			jn.put("modality", "MR");
			jn.put("studyDescription", "BRAIN SELLA");
			jn.put("numImages", "17");
			jn.put("studyId", "mrstudy");
			ja.add(jn);

			map.put("studyList", ja);
		} catch (Exception e) {
			map.put("errror", "404");
		}

		return map;

	}
	
	@RequestMapping(value = "/mrstudyList", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> mrstudyList(HttpServletRequest request) {
		// 最外面json
		Map<String, Object> map = new HashMap<>();

		try {
			//从页面上取参数
			String studyId = request.getParameter("studyId");
			
			map.put("patientName", "唐远坤");
			map.put("patientId", "832040");
			map.put("studyDate", "20010108");
			map.put("modality", "MR");
			map.put("studyDescription", "BRAIN SELLA");
			map.put("numImages", "17");
			map.put("studyId", "mrstudy");
			
			JSONObject jsn = new JSONObject();
			jsn.put("seriesDescription", "3-PLANE LOC");
			jsn.put("seriesNumber", "1");
			jsn.put("instanceList", "1");
			JSONArray ja = new JSONArray();

			JSONObject jn = new JSONObject();
			jn.put("imageId", "0.dcm");
			jn.put("imageId", "1.dcm");
			ja.add(jn);

			jsn.put("instanceList", ja);
			
			JSONArray jns = new JSONArray();
			jns.add(jsn);
			map.put("seriesList", jns);
			
		} catch (Exception e) {
			map.put("errror", "404");
		}

		return map;

	}

	
}
