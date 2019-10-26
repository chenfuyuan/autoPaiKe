package com.ass.manager.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ass.common.pojo.EasyUIDataGridResult;
import com.ass.manager.service.ClassRoomService;
import com.ass.mapper.TClassroomMapper;
import com.ass.pojo.TClassroom;
import com.ass.pojo.TClassroomExample;
import com.ass.pojo.TClassroomExample.Criteria;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;




/**
 * 教室信息管理Service
 * @author chenfuyuan
 *
 */

@Service
public class ClassRoomServiceImpl implements ClassRoomService {

	@Autowired
	private TClassroomMapper tClassRoomMapper;
	
	//添加信息
	public void add(TClassroom t) {
		//补全属性
		t.setState(0);
		t.setStatus(1);
		t.setCraeted(new Date());
		t.setUpdated(new Date());
		//添加
		tClassRoomMapper.insert(t);
	}

	//分页查询所有信息
	public EasyUIDataGridResult findList(Integer currentPage,Integer currentCount) {
		//设置分页信息
		PageHelper.startPage(currentPage, currentCount);
		//执行查询
		TClassroomExample example=new TClassroomExample();
		Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(1);
		List<TClassroom> list = tClassRoomMapper.selectByExample(example);	
		//取分页信息
		PageInfo<TClassroom> pageInfo=new PageInfo<TClassroom>(list);
		//创建返回结果对象
		EasyUIDataGridResult result=new EasyUIDataGridResult();
		result.setTotal(pageInfo.getTotal());
		result.setRows(list);
		return result;
	}

	//根据ID获取信息
	public TClassroom get(Integer id) {
		TClassroom t = tClassRoomMapper.selectByPrimaryKey(id);
		return t;
	}

	//修改信息
	public void edit(TClassroom t) {
		TClassroom oldT=tClassRoomMapper.selectByPrimaryKey(t.getId());
		//补全属性
		t.setState(oldT.getState());
		t.setCraeted(oldT.getCraeted());
		t.setStatus(oldT.getStatus());
		t.setUpdated(new Date());
		tClassRoomMapper.updateByPrimaryKey(t);
	}

	//根据ID删除单个信息
	public void deleteById(Integer id) {
		TClassroom t=tClassRoomMapper.selectByPrimaryKey(id);
		t.setStatus(0);
		tClassRoomMapper.updateByPrimaryKey(t);
	}

	//根据名称模糊查询信息
	public EasyUIDataGridResult search(String words, Integer currentPage, int currentCount) {
		//设置分页信息
		PageHelper.startPage(currentPage, currentCount);
		//执行查询
		TClassroomExample example=new TClassroomExample();
		Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(1);
		if(StringUtils.isNotBlank(words)){
			criteria.andNameLike("%"+words+"%");
		}
		List<TClassroom> list = tClassRoomMapper.selectByExample(example);	
		//取分页信息
		PageInfo<TClassroom> pageInfo=new PageInfo<TClassroom>(list);
		//创建返回结果对象
		EasyUIDataGridResult result=new EasyUIDataGridResult();
		result.setTotal(pageInfo.getTotal());
		result.setRows(list);
		return result;
	}

	//根据获取的内容模糊查询名称
	public List<Object> searchName(String word) {
		List<Object> names= tClassRoomMapper.selectName(word);
		return names;
	}

	//批量保存信息
	public void saveBatch(List<TClassroom> list) {
		for (TClassroom t : list) {
			tClassRoomMapper.insert(t);
		}	
	}

	//查询所有信息
	public List<TClassroom> findAll() {
		TClassroomExample example=new TClassroomExample();
		Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(1);
		return tClassRoomMapper.selectByExample(example);
	}

	//根据Id获取教室名称
	public String findClassRoomName(Integer classroomid) {
		return tClassRoomMapper.selectNameById(classroomid);
	}

	//根据教室名称获取Id
	public Integer findIdByName(String input) {
		return tClassRoomMapper.selectIDByName(input);
	}
}
