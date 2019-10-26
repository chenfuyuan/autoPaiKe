package com.ass.manager.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.ass.common.pojo.EasyUIDataGridResult;
import com.ass.manager.service.AdminService;
import com.ass.mapper.TUserMapper;
import com.ass.pojo.TUser;
import com.ass.pojo.TUserExample;
import com.ass.pojo.TUserExample.Criteria;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;




/**
 * 管理员信息管理Service
 * @author chenfuyuan
 *
 */

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private TUserMapper tUserMapper;
	
	//添加信息
	public void add(TUser t) {
		//补全属性
		String password = t.getCode().substring(12, 18);			//获取身份证后六位作为初始密码
		t.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));		//MD5加密后存放
		t.setCode(DigestUtils.md5DigestAsHex(t.getCode().getBytes()));
		t.setStatus(1);
		//添加
		tUserMapper.insert(t);
	}

	//分页查询所有信息
	public EasyUIDataGridResult findList(Integer currentPage,Integer currentCount) {
		//设置分页信息
		PageHelper.startPage(currentPage, currentCount);
		//执行查询
		TUserExample example=new TUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(1);
		List<TUser> list = tUserMapper.selectByExample(example);	
		//取分页信息
		PageInfo<TUser> pageInfo=new PageInfo<TUser>(list);
		//创建返回结果对象
		EasyUIDataGridResult result=new EasyUIDataGridResult();
		result.setTotal(pageInfo.getTotal());
		result.setRows(list);
		return result;
	}

	//根据ID获取信息
	public TUser get(Integer id) {
		TUser t = tUserMapper.selectByPrimaryKey(id);
		return t;
	}

	//修改信息
	public void edit(TUser t) {
		TUser oldT=tUserMapper.selectByPrimaryKey(t.getId());
		//补全属性
		t.setStatus(oldT.getStatus());
		t.setPassword(oldT.getPassword());
		t.setCode(oldT.getCode());
		tUserMapper.updateByPrimaryKey(t);
	}

	//根据ID删除单个信息
	public void deleteById(Integer id) {
		TUser t=tUserMapper.selectByPrimaryKey(id);
		t.setStatus(0);
		tUserMapper.updateByPrimaryKey(t);
	}

	//根据名称模糊查询信息
	public EasyUIDataGridResult search(String words, Integer currentPage, int currentCount) {
		//设置分页信息
		PageHelper.startPage(currentPage, currentCount);
		//执行查询
		TUserExample example=new TUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(1);
		if(StringUtils.isNotBlank(words)){
			criteria.andNameLike("%"+words+"%");
		}
		List<TUser> list = tUserMapper.selectByExample(example);	
		//取分页信息
		PageInfo<TUser> pageInfo=new PageInfo<TUser>(list);
		//创建返回结果对象
		EasyUIDataGridResult result=new EasyUIDataGridResult();
		result.setTotal(pageInfo.getTotal());
		result.setRows(list);
		return result;
	}

	//根据获取的内容模糊查询名称
	public List<Object> searchName(String word) {
		List<Object> names= tUserMapper.selectName(word);
		return names;
	}

	//查询所有信息
	public List<TUser> findAll() {
		TUserExample example=new TUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(1);
		return tUserMapper.selectByExample(example);
	}

	//修改密码
	public void editPassword(TUser user) {
		tUserMapper.updateByPrimaryKey(user);
	}	
}
