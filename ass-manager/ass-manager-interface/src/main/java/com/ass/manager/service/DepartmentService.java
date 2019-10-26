package com.ass.manager.service;

import java.util.List;

import com.ass.common.pojo.EasyUIDataGridResult;
import com.ass.pojo.TDepartment;

public interface DepartmentService {
	void addDepartment(TDepartment tDepartment);
	EasyUIDataGridResult findListDepartment(Integer currentPage, Integer currentCount);
	TDepartment getDepartment(Integer id);
	void editDepartment(TDepartment tDepartment);
	void deleteById(Integer id);
	EasyUIDataGridResult searchDepartment(String words, Integer currentPage, int currentCount);
	List<Object> searchDepartmentName(String word);
	void saveBatch(List<TDepartment> list);
	List<TDepartment> findAll();
}
