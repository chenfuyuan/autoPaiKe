package com.ass.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ass.common.pojo.EasyUIDataGridResult;
import com.ass.manager.service.AutoService;
import com.ass.mapper.TReadyMapper;
import com.ass.pojo.TReady;
import com.ass.pojo.TReadyExample;
import com.ass.pojo.TReadyExample.Criteria;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 排课信息管理Service
 * @author chenfuyuan
 *
 */

@Service
public class AutoServiceImpl implements AutoService {
	
	@Autowired
	private TReadyMapper tReadyMapper;

	//根据班级编号获取课程表
	public List<TReady> findByClassId(String classId) {
		TReadyExample example=new TReadyExample();
		Criteria criteria = example.createCriteria();
		criteria.andClassidEqualTo(classId);
		List<TReady> list = tReadyMapper.selectByExample(example);
		return list;
	}
	
	//根据教师编号获取课程表
	public List<TReady> findByTeacherId(String teacherId) {
		TReadyExample example=new TReadyExample();
		Criteria criteria = example.createCriteria();
		criteria.andTeacheridEqualTo(teacherId);
		List<TReady> list = tReadyMapper.selectByExample(example);
		return list;
	}

	//根据教室编号获取课程表
	public List<TReady> findClassRoomById(Integer classRoomId) {
		TReadyExample example=new TReadyExample();
		Criteria criteria = example.createCriteria();
		criteria.andClassroomidEqualTo(classRoomId);
		List<TReady> list = tReadyMapper.selectByExample(example);
		return list;
	}
	
	//分页查询所有信息
	public EasyUIDataGridResult findList(Integer currentPage,Integer currentCount) {
		//设置分页信息
		PageHelper.startPage(currentPage, currentCount);
		//执行查询
		TReadyExample example=new TReadyExample();
		List<TReady> list = tReadyMapper.selectByExample(example);	
		//取分页信息
		PageInfo<TReady> pageInfo=new PageInfo<TReady>(list);
		//创建返回结果对象
		EasyUIDataGridResult result=new EasyUIDataGridResult();
		result.setTotal(pageInfo.getTotal());
		result.setRows(list);
		return result;
	}

	//删除单条排课信息
	public void deleteById(Long id) {
		tReadyMapper.deleteByPrimaryKey(id);
	}

	//修改某条排课信息的课程
	public void editCourse(Long id, Integer courseId) {
		TReady tReady = tReadyMapper.selectByPrimaryKey(id);
		tReady.setCourseid(courseId);
		tReadyMapper.updateByPrimaryKey(tReady);
	}

	//修改某条排课信息的教师
	public void editTeacher(Long id, String teacherId) {
		TReady tReady = tReadyMapper.selectByPrimaryKey(id);
		tReady.setTeacherid(teacherId);
		tReadyMapper.updateByPrimaryKey(tReady);
	}

	//修改某条排课信息的班级
	public void editClass(Long id, String classId) {
		TReady tReady = tReadyMapper.selectByPrimaryKey(id);
		tReady.setClassid(classId);
		tReadyMapper.updateByPrimaryKey(tReady);
	}

	//修改某条排课信息的教室
	public void editClassRoom(Long id, Integer classroomId) {
		TReady tReady = tReadyMapper.selectByPrimaryKey(id);
		tReady.setClassroomid(classroomId);
		tReadyMapper.updateByPrimaryKey(tReady);
	}

	//根据id获取到数据
	public TReady get(Long id) {
		return tReadyMapper.selectByPrimaryKey(id);
	}

	//修改某条排课信息的上课时间
	public void editTime(TReady tReady) {
		tReadyMapper.updateByPrimaryKey(tReady);
	}
}
