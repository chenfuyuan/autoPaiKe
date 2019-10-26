package com.ass.manager.service;

import java.util.List;

import com.ass.common.pojo.EasyUIDataGridResult;
import com.ass.pojo.TClass;

public interface ClassService {
	void add(TClass t);
	EasyUIDataGridResult findList(Integer currentPage, Integer currentCount);
	void edit(TClass t);
	TClass get(String id);
	void deleteById(String id);
	EasyUIDataGridResult search(String words, Integer currentPage, int currentCount);
	List<Object> searchName(String word);
	void saveBatch(List<TClass> list);
	List<TClass> findAll();
	List<TClass> findAllAjax(Integer departmentId);
	String fingClassName(String classid);
	List<TClass> findAllAjaxs();
}
