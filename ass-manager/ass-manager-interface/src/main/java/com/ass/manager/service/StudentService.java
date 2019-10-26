package com.ass.manager.service;

import java.util.List;

import com.ass.common.pojo.EasyUIDataGridResult;
import com.ass.pojo.TStudent;


public interface StudentService {
	void add(TStudent t);
	EasyUIDataGridResult findList(Integer currentPage, Integer currentCount);
	void edit(TStudent t);
	TStudent get(String id);
	void deleteById(String id);
	EasyUIDataGridResult search(String words, Integer currentPage, int currentCount);
	List<Object> searchName(String word);
	void saveBatch(List<TStudent> list);
	List<TStudent> findAll();
}
