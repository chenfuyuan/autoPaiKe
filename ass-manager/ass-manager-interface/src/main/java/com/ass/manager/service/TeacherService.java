package com.ass.manager.service;

import java.util.List;

import com.ass.common.pojo.EasyUIDataGridResult;
import com.ass.pojo.TCourseTeacher;
import com.ass.pojo.TTeacher;


public interface TeacherService {
	void insertTC(Integer courseId,TTeacher t);
	TTeacher add(TTeacher t);
	EasyUIDataGridResult findList(Integer currentPage, Integer currentCount);
	void deleteTC(String id);
	void edit(TTeacher t);
	TTeacher get(String id);
	void deleteById(String id);
	EasyUIDataGridResult search(String words, Integer currentPage, int currentCount);
	List<Object> searchName(String word);
	void saveBatch(List<TTeacher> list);
	List<TTeacher> findAll();
	String findTeacherName(String teacherid);
	List<TTeacher> findAllAjax(Integer departmentId);
	List<TCourseTeacher> getCourseId(String id);
	List<TTeacher> findAllAjaxs();	
}
