package com.cn.ub.service;

import java.util.List;

public interface BaseService<T> {

	// 增加
	void save(T obj) throws Exception;

	// 修改
	void update(T obj) throws Exception;

	// 按id删除
	void deleteById(String id) throws Exception;

	// 按对象查询
	List<T> getList(T obj) throws Exception;

	// 按条件查询对象
	T getObjById(String id) throws Exception;

}
