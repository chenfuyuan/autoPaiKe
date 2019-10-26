package com.ass.manager.service.impl;

import com.ass.common.pojo.EasyUIDataGridResult;
import com.ass.manager.service.StudentService;
import com.ass.mapper.TStudentMapper;
import com.ass.pojo.TStudent;
import com.ass.pojo.TStudentExample;
import com.ass.pojo.TStudentExample.Criteria;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;




/**
 * 学生信息管理Service
 * @author LXC
 *
 */

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private TStudentMapper tStudentMapper;
	
	//添加信息
	public void add(TStudent t) {
		//补全属性
		String yearLast = new SimpleDateFormat("yy",Locale.CHINESE).format(Calendar.getInstance().getTime());
		Integer dId=t.getDepartmentid();
		String classid = t.getClassid();
		classid=classid.substring(6,8);
		int count = tStudentMapper.selectIdCount(yearLast+dId+classid+"%");
		String countString;
		if(count<10){
			countString="0"+(count+1);
		}else {
			countString=(count+1)+"";
		}
		t.setId(yearLast+dId+classid+countString);
		
		String password = t.getCode().substring(12, 18);			//获取身份证后六位作为初始密码
		t.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));		//MD5加密后存放
		t.setCode(DigestUtils.md5DigestAsHex(t.getCode().getBytes()));
		t.setYear(new Date());
		t.setStatus(1);
		t.setCreated(new Date());
		t.setUpdated(new Date());
		//添加
		tStudentMapper.insert(t);
	}

	//查询所有信息
	public EasyUIDataGridResult findList(Integer currentPage,Integer currentCount) {
		//设置分页信息
		PageHelper.startPage(currentPage, currentCount);
		//执行查询
		TStudentExample example=new TStudentExample();
		Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(1);
		List<TStudent> list = tStudentMapper.selectByExample(example);	
		//取分页信息
		PageInfo<TStudent> pageInfo=new PageInfo<TStudent>(list);
		//创建返回结果对象
		EasyUIDataGridResult result=new EasyUIDataGridResult();
		result.setTotal(pageInfo.getTotal());
		result.setRows(list);
		return result;
	}

	//根据ID获取信息
	public TStudent get(String id) {
		TStudent t = tStudentMapper.selectByPrimaryKey(id);
		return t;
	}

	//修改信息
	public void edit(TStudent t) {
		TStudent oldT=tStudentMapper.selectByPrimaryKey(t.getId());
		//补全属性
		t.setPassword(oldT.getPassword());
		t.setCode(oldT.getCode());
		t.setYear(oldT.getYear());
		t.setCreated(oldT.getCreated());
		t.setStatus(oldT.getStatus());
		t.setUpdated(new Date());
		tStudentMapper.updateByPrimaryKey(t);
	}

	//根据ID删除单个信息
	public void deleteById(String id) {
		TStudent t=tStudentMapper.selectByPrimaryKey(id);
		t.setStatus(0);
		tStudentMapper.updateByPrimaryKey(t);
	}

	//根据学号模糊查询信息
	public EasyUIDataGridResult search(String words, Integer currentPage, int currentCount) {
		//设置分页信息
		PageHelper.startPage(currentPage, currentCount);
		//执行查询
		TStudentExample example=new TStudentExample();
		Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(1);
		if(StringUtils.isNotBlank(words)){
			criteria.andIdLike("%"+words+"%");
		}
		List<TStudent> list = tStudentMapper.selectByExample(example);	
		//取分页信息
		PageInfo<TStudent> pageInfo=new PageInfo<TStudent>(list);
		//创建返回结果对象
		EasyUIDataGridResult result=new EasyUIDataGridResult();
		result.setTotal(pageInfo.getTotal());
		result.setRows(list);
		return result;
	}

	//根据获取的内容模糊查询学号
	public List<Object> searchName(String word) {
		List<Object> names= tStudentMapper.selectName(word);
		return names;
	}

	//批量保存信息
	public void saveBatch(List<TStudent> list) {
		for (TStudent t : list) {
			tStudentMapper.insert(t);
		}	
	}

	//查询所有班级信息
	public List<TStudent> findAll() {
		TStudentExample example=new TStudentExample();
		Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(1);
		return tStudentMapper.selectByExample(example);
	}	
}
