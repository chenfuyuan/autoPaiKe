package com.ass.manager.service;

import java.util.List;

import com.ass.common.pojo.EasyUIDataGridResult;
import com.ass.pojo.TUser;

public interface AdminService {

	void add(TUser t);
	EasyUIDataGridResult findList(Integer currentPage, Integer currentCount);
	void edit(TUser t);
	TUser get(Integer id);
	void deleteById(Integer id);
	EasyUIDataGridResult search(String words, Integer currentPage, int currentCount);
	List<Object> searchName(String word);
	List<TUser> findAll();
	void editPassword(TUser user);
}
