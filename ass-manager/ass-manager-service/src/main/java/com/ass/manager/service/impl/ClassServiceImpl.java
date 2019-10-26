package com.ass.manager.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ass.common.pojo.EasyUIDataGridResult;
import com.ass.manager.service.ClassService;
import com.ass.mapper.TClassMapper;
import com.ass.pojo.TClass;
import com.ass.pojo.TClassExample;
import com.ass.pojo.TClassExample.Criteria;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;




/**
 * 班级信息管理Service
 * @author chenfuyuan
 *
 */

@Service
public class ClassServiceImpl implements ClassService {

	@Autowired
	private TClassMapper tClassMapper;
	
	//添加班级信息
	public void add(TClass t) {
		//补全属性
		String yearLast = new SimpleDateFormat("yy",Locale.CHINESE).format(Calendar.getInstance().getTime());
		Integer dId=t.getDepartmentid();
		int count = tClassMapper.selectIdCount(yearLast+dId+"%");
		String countString;
		if(count<10){
			countString="0"+(count+1);
		}else {
			countString=(count+1)+"";
		}
		t.setId(yearLast+dId+countString);
		t.setSerial(count+1);
		t.setStatus(1);
		t.setCreated(new Date());
		t.setUpdated(new Date());
		//添加
		tClassMapper.insert(t);
	}

	//查询所有班级信息
	public EasyUIDataGridResult findList(Integer currentPage,Integer currentCount) {
		//设置分页信息
		PageHelper.startPage(currentPage, currentCount);
		//执行查询
		TClassExample example=new TClassExample();
		Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(1);
		List<TClass> list = tClassMapper.selectByExample(example);	
		//取分页信息
		PageInfo<TClass> pageInfo=new PageInfo<TClass>(list);
		//创建返回结果对象
		EasyUIDataGridResult result=new EasyUIDataGridResult();
		result.setTotal(pageInfo.getTotal());
		result.setRows(list);
		return result;
	}

	//根据ID获取班级信息
	public TClass get(String id) {
		TClass t = tClassMapper.selectByPrimaryKey(id);
		return t;
	}

	//修改班级信息
	public void edit(TClass t) {
		TClass oldT=tClassMapper.selectByPrimaryKey(t.getId());
		//补全属性
		t.setSerial(oldT.getSerial());
		t.setCreated(oldT.getCreated());
		t.setStatus(oldT.getStatus());
		t.setUpdated(new Date());
		tClassMapper.updateByPrimaryKey(t);
	}

	//根据ID删除单个班级信息
	public void deleteById(String id) {
		TClass t=tClassMapper.selectByPrimaryKey(id);
		t.setStatus(0);
		tClassMapper.updateByPrimaryKey(t);
	}

	//根据班级名模糊查询班级信息
	public EasyUIDataGridResult search(String words, Integer currentPage, int currentCount) {
		//设置分页信息
		PageHelper.startPage(currentPage, currentCount);
		//执行查询
		TClassExample example=new TClassExample();
		Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(1);
		if(StringUtils.isNotBlank(words)){
			criteria.andNameLike("%"+words+"%");
		}
		List<TClass> list = tClassMapper.selectByExample(example);	
		//取分页信息
		PageInfo<TClass> pageInfo=new PageInfo<TClass>(list);
		//创建返回结果对象
		EasyUIDataGridResult result=new EasyUIDataGridResult();
		result.setTotal(pageInfo.getTotal());
		result.setRows(list);
		return result;
	}

	//根据获取的内容模糊查询班级名称
	public List<Object> searchName(String word) {
		List<Object> names= tClassMapper.selectName(word);
		return names;
	}

	//批量保存班级信息
	public void saveBatch(List<TClass> list) {
		for (TClass t : list) {
			tClassMapper.insert(t);
		}	
	}

	//查询所有班级信息
	public List<TClass> findAll() {
		TClassExample example=new TClassExample();
		Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(1);
		return tClassMapper.selectByExample(example);
	}

	//查询某专业下的所有班级
	public List<TClass> findAllAjax(Integer departmentId) {
		TClassExample example=new TClassExample();
		Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(1);
		criteria.andDepartmentidEqualTo(departmentId);
		return tClassMapper.selectByExample(example);
	}

	//根据Id获取班级名称
	public String fingClassName(String classid) {
		return tClassMapper.selectNameById(classid);
	}

	//查询所有班级
	public List<TClass> findAllAjaxs() {
		TClassExample example=new TClassExample();
		Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(1);
		return tClassMapper.selectByExample(example);
	}	
}
