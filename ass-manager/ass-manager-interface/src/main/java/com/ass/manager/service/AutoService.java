package com.ass.manager.service;

import java.util.List;

import com.ass.common.pojo.EasyUIDataGridResult;
import com.ass.pojo.TReady;

public interface AutoService {

	List<TReady> findByClassId(String classId);

	List<TReady> findByTeacherId(String teacherId);

	List<TReady> findClassRoomById(Integer classRoomId);

	EasyUIDataGridResult findList(Integer currentPage, Integer currentCount);

	void deleteById(Long id);

	void editCourse(Long id, Integer courseId);

	void editTeacher(Long id, String teacherId);

	void editClass(Long id, String classId);

	void editClassRoom(Long id, Integer classroomId);

	TReady get(Long id);

	void editTime(TReady tReady);

}
