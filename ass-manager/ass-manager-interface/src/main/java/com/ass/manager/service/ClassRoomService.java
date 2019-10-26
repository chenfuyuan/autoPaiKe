package com.ass.manager.service;

import java.util.List;

import com.ass.common.pojo.EasyUIDataGridResult;
import com.ass.pojo.TClassroom;

public interface ClassRoomService {
	void add(TClassroom t);
	EasyUIDataGridResult findList(Integer currentPage, Integer currentCount);
	void edit(TClassroom t);
	TClassroom get(Integer id);
	void deleteById(Integer id);
	EasyUIDataGridResult search(String words, Integer currentPage, int currentCount);
	List<Object> searchName(String word);
	void saveBatch(List<TClassroom> list);
	List<TClassroom> findAll();
	String findClassRoomName(Integer classroomid);
	Integer findIdByName(String input);
}
