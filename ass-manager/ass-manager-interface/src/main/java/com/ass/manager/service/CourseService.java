package com.ass.manager.service;

import java.util.List;

import com.ass.common.pojo.EasyUIDataGridResult;
import com.ass.pojo.TCourse;


public interface CourseService {
	void add(TCourse t);
	EasyUIDataGridResult findList(Integer currentPage, Integer currentCount);
	void edit(TCourse t);
	TCourse get(Integer id);
	void deleteById(Integer id);
	EasyUIDataGridResult search(String words, Integer currentPage, int currentCount);
	List<Object> searchName(String word);
	void saveBatch(List<TCourse> list);
	List<TCourse> findAll();
	List<TCourse> findAllAjax(Integer departmentId);
	String findCourseName(Integer courseid);
	List<TCourse> findAllAjaxs();
}
