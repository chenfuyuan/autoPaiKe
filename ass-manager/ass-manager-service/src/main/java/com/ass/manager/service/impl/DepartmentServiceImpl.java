package com.ass.manager.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ass.common.pojo.EasyUIDataGridResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ass.manager.service.DepartmentService;
import com.ass.mapper.TDepartmentMapper;
import com.ass.pojo.TDepartment;
import com.ass.pojo.TDepartmentExample;
import com.ass.pojo.TDepartmentExample.Criteria;

/**
 * 专业信息管理Service
 * @author chenfuyuan
 *
 */

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private TDepartmentMapper tDepartmentMapper;
	
	//添加专业信息
	public void addDepartment(TDepartment tDepartment) {
		//补全属性
		tDepartment.setStatus((byte) 1);
		tDepartment.setCreated(new Date());
		tDepartment.setUpdated(new Date());
		//添加
		tDepartmentMapper.insert(tDepartment);
	}

	//查询所有专业信息
	public EasyUIDataGridResult findListDepartment(Integer currentPage,Integer currentCount) {
		//设置分页信息
		PageHelper.startPage(currentPage, currentCount);
		//执行查询
		TDepartmentExample example=new TDepartmentExample();
		Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo((byte) 1);
		List<TDepartment> list = tDepartmentMapper.selectByExample(example);	
		//取分页信息
		PageInfo<TDepartment> pageInfo=new PageInfo<TDepartment>(list);
		//创建返回结果对象
		EasyUIDataGridResult result=new EasyUIDataGridResult();
		result.setTotal(pageInfo.getTotal());
		result.setRows(list);
		return result;
	}

	//根据专业ID获取专业信息
	public TDepartment getDepartment(Integer id) {
		TDepartment tDepartment = tDepartmentMapper.selectByPrimaryKey(id);
		return tDepartment;
	}

	//修改专业信息
	public void editDepartment(TDepartment tDepartment) {
		TDepartment t=tDepartmentMapper.selectByPrimaryKey(tDepartment.getId());
		//补全属性
		tDepartment.setStatus(t.getStatus());
		tDepartment.setCreated(t.getCreated());
		tDepartment.setUpdated(new Date());
		tDepartmentMapper.updateByPrimaryKey(tDepartment);
	}

	//根据ID删除单个专业信息
	public void deleteById(Integer id) {
		TDepartment tDepartment=tDepartmentMapper.selectByPrimaryKey(id);
		tDepartment.setStatus((byte) 0);
		tDepartmentMapper.updateByPrimaryKey(tDepartment);
	}

	//根据专业名模糊查询专业信息
	public EasyUIDataGridResult searchDepartment(String words, Integer currentPage, int currentCount) {
		//设置分页信息
		PageHelper.startPage(currentPage, currentCount);
		//执行查询
		TDepartmentExample example=new TDepartmentExample();
		Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo((byte) 1);
		if(StringUtils.isNotBlank(words)){
			criteria.andNameLike("%"+words+"%");
		}
		List<TDepartment> list = tDepartmentMapper.selectByExample(example);	
		//取分页信息
		PageInfo<TDepartment> pageInfo=new PageInfo<TDepartment>(list);
		//创建返回结果对象
		EasyUIDataGridResult result=new EasyUIDataGridResult();
		result.setTotal(pageInfo.getTotal());
		result.setRows(list);
		return result;
	}

	//根据获取的内容模糊查询专业名称
	public List<Object> searchDepartmentName(String word) {
		List<Object> names= tDepartmentMapper.selectName(word);
		return names;
	}

	//批量保存专业信息
	public void saveBatch(List<TDepartment> list) {
		for (TDepartment tDepartment : list) {
			tDepartmentMapper.insert(tDepartment);
		}	
	}

	//查询所有专业信息
	public List<TDepartment> findAll() {
		TDepartmentExample example=new TDepartmentExample();
		Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo((byte) 1);
		List<TDepartment> list = tDepartmentMapper.selectByExample(example);
		return list;
	}	
}
