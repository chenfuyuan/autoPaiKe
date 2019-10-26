package com.ass.manager.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ass.common.pojo.EasyUIDataGridResult;
import com.ass.manager.service.CourseService;
import com.ass.mapper.TCourseMapper;
import com.ass.pojo.TCourse;
import com.ass.pojo.TCourseExample;
import com.ass.pojo.TCourseExample.Criteria;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;




/**
 * 课程信息管理Service
 * @author chenfuyuan
 *
 */

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private TCourseMapper tCourseMapper;
	
	//添加信息
	public void add(TCourse t) {
		//补全属性
		t.setStatus(1);
		t.setCreated(new Date());
		t.setUpdated(new Date());
		//添加
		tCourseMapper.insert(t);
	}

	//查询所有信息
	public EasyUIDataGridResult findList(Integer currentPage,Integer currentCount) {
		//设置分页信息
		PageHelper.startPage(currentPage, currentCount);
		//执行查询
		TCourseExample example=new TCourseExample();
		Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(1);
		List<TCourse> list = tCourseMapper.selectByExample(example);	
		//取分页信息
		PageInfo<TCourse> pageInfo=new PageInfo<TCourse>(list);
		//创建返回结果对象
		EasyUIDataGridResult result=new EasyUIDataGridResult();
		result.setTotal(pageInfo.getTotal());
		result.setRows(list);
		return result;
	}

	//根据ID获取信息
	public TCourse get(Integer id) {
		TCourse t = tCourseMapper.selectByPrimaryKey(id);
		return t;
	}

	//修改信息
	public void edit(TCourse t) {
		TCourse oldT=tCourseMapper.selectByPrimaryKey(t.getId());
		//补全属性
		t.setCreated(oldT.getCreated());
		t.setStatus(oldT.getStatus());
		t.setUpdated(new Date());
		tCourseMapper.updateByPrimaryKey(t);
	}

	//根据ID删除单个信息
	public void deleteById(Integer id) {
		TCourse t=tCourseMapper.selectByPrimaryKey(id);
		t.setStatus(0);
		tCourseMapper.updateByPrimaryKey(t);
	}

	//根据课程名模糊查询信息
	public EasyUIDataGridResult search(String words, Integer currentPage, int currentCount) {
		//设置分页信息
		PageHelper.startPage(currentPage, currentCount);
		//执行查询
		TCourseExample example=new TCourseExample();
		Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(1);
		if(StringUtils.isNotBlank(words)){
			criteria.andNameLike("%"+words+"%");
		}
		List<TCourse> list = tCourseMapper.selectByExample(example);	
		//取分页信息
		PageInfo<TCourse> pageInfo=new PageInfo<TCourse>(list);
		//创建返回结果对象
		EasyUIDataGridResult result=new EasyUIDataGridResult();
		result.setTotal(pageInfo.getTotal());
		result.setRows(list);
		return result;
	}

	//根据获取的内容模糊查询学号
	public List<Object> searchName(String word) {
		List<Object> names= tCourseMapper.selectName(word);
		return names;
	}

	//批量保存信息
	public void saveBatch(List<TCourse> list) {
		for (TCourse t : list) {
			tCourseMapper.insert(t);
		}	
	}

	//查询所有班级信息
	public List<TCourse> findAll() {
		TCourseExample example=new TCourseExample();
		Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(1);
		return tCourseMapper.selectByExample(example);
	}

	//查询某专业下的所有课程
	public List<TCourse> findAllAjax(Integer departmentId) {
		TCourseExample example=new TCourseExample();
		Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(1);
		criteria.andDepartmentidEqualTo(departmentId);
		return tCourseMapper.selectByExample(example);
	}

	//查询某课程名称
	public String findCourseName(Integer courseid) {
		return tCourseMapper.selectNameById(courseid);
	}

	//查询所有课程
	public List<TCourse> findAllAjaxs() {
		TCourseExample example=new TCourseExample();
		Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(1);
		return tCourseMapper.selectByExample(example);
	}	
}
