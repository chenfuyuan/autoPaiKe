package com.ass.manager.service.impl;

import com.ass.common.pojo.EasyUIDataGridResult;
import com.ass.manager.service.TeacherService;
import com.ass.mapper.TCourseTeacherMapper;
import com.ass.mapper.TTeacherMapper;
import com.ass.pojo.TCourseTeacher;
import com.ass.pojo.TCourseTeacherExample;
import com.ass.pojo.TTeacher;
import com.ass.pojo.TTeacherExample;
import com.ass.pojo.TTeacherExample.Criteria;
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
 * 教师信息管理Service
 * @author LXC
 *
 */

@Service
public class TeacherServiceImpl implements TeacherService {

	@Autowired
	private TTeacherMapper tTeacherMapper;

	@Autowired
	private TCourseTeacherMapper tCourseTeacherMapper;
	
	//设置教师工号
	public String getJobNum(TTeacher t){
		String yearLast = new SimpleDateFormat("yy",Locale.CHINESE).format(Calendar.getInstance().getTime());
		Integer dId=t.getDepartmentid();
		int count = tTeacherMapper.selectIdCount(yearLast+dId+"%");
		String countString;
		if(count<10){
			countString="0"+(count+1);
		}else {
			countString=(count+1)+"";
		}
		return yearLast+dId+countString;
	}
	
	//添加教师信息
	public TTeacher add(TTeacher t) {
		//补全属性
		String teacherId = getJobNum(t);
		t.setId(teacherId);
		String password = t.getCode().substring(12, 18);			//获取身份证后六位作为初始密码
		t.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));		//MD5加密后存放
		t.setCode(DigestUtils.md5DigestAsHex(t.getCode().getBytes()));
		t.setYear(new Date());
		t.setStatus(1);
		t.setCreated(new Date());
		t.setUpdated(new Date());
		//添加
		tTeacherMapper.insert(t);
		return t;
	}

	//添加教师课程信息
	@Override
	public void insertTC(Integer courseId,TTeacher teacher) {
		TCourseTeacher tCourseTeacher=new TCourseTeacher();
		tCourseTeacher.setTeacherid(teacher.getId());
		tCourseTeacher.setCourseid(courseId);
		tCourseTeacherMapper.insert(tCourseTeacher);
	}

	//查询所有信息
	public EasyUIDataGridResult findList(Integer currentPage,Integer currentCount) {
		//设置分页信息
		PageHelper.startPage(currentPage, currentCount);
		//执行查询
		TTeacherExample example=new TTeacherExample();
		Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(1);
		List<TTeacher> list = tTeacherMapper.selectByExample(example);	
		//取分页信息
		PageInfo<TTeacher> pageInfo=new PageInfo<TTeacher>(list);
		//创建返回结果对象
		EasyUIDataGridResult result=new EasyUIDataGridResult();
		result.setTotal(pageInfo.getTotal());
		result.setRows(list);
		return result;
	}

	//根据ID获取信息
	public TTeacher get(String id) {
		TTeacher t = tTeacherMapper.selectByPrimaryKey(id);
		return t;
	}
	
	//根据教师id删除教师课程信息
	public void deleteTC(String id) {
		TCourseTeacherExample example=new TCourseTeacherExample();
		com.ass.pojo.TCourseTeacherExample.Criteria criteria = example.createCriteria();
		criteria.andTeacheridEqualTo(id);
		tCourseTeacherMapper.deleteByExample(example);
	}

	//修改信息
	public void edit(TTeacher t) {
		TTeacher oldT=tTeacherMapper.selectByPrimaryKey(t.getId());
		//补全属性
		t.setPassword(oldT.getPassword());
		t.setCode(oldT.getCode());
		t.setYear(oldT.getYear());
		t.setCreated(oldT.getCreated());
		t.setStatus(oldT.getStatus());
		t.setUpdated(new Date());
		tTeacherMapper.updateByPrimaryKey(t);
	}

	//根据ID删除单个信息
	public void deleteById(String id) {
		TTeacher t=tTeacherMapper.selectByPrimaryKey(id);
		t.setStatus(0);
		tTeacherMapper.updateByPrimaryKey(t);
	}

	//根据工号模糊查询信息
	public EasyUIDataGridResult search(String words, Integer currentPage, int currentCount) {
		//设置分页信息
		PageHelper.startPage(currentPage, currentCount);
		//执行查询
		TTeacherExample example=new TTeacherExample();
		Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(1);
		if(StringUtils.isNotBlank(words)){
			criteria.andIdLike("%"+words+"%");
		}
		List<TTeacher> list = tTeacherMapper.selectByExample(example);	
		//取分页信息
		PageInfo<TTeacher> pageInfo=new PageInfo<TTeacher>(list);
		//创建返回结果对象
		EasyUIDataGridResult result=new EasyUIDataGridResult();
		result.setTotal(pageInfo.getTotal());
		result.setRows(list);
		return result;
	}

	//根据获取的内容模糊查询工号
	public List<Object> searchName(String word) {
		List<Object> names= tTeacherMapper.selectName(word);
		return names;
	}

	//批量保存信息
	public void saveBatch(List<TTeacher> list) {
		for (TTeacher t : list) {
			tTeacherMapper.insert(t);
		}	
	}

	//查询所有信息
	public List<TTeacher> findAll() {
		TTeacherExample example=new TTeacherExample();
		Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(1);
		return tTeacherMapper.selectByExample(example);
	}

	//根据id获取教师名称
	public String findTeacherName(String teacherid) {
		return tTeacherMapper.selectNameById(teacherid);
	}
	
	//查询某专业下的所有教师
	public List<TTeacher> findAllAjax(Integer departmentId) {
		TTeacherExample example=new TTeacherExample();
		Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(1);
		criteria.andDepartmentidEqualTo(departmentId);
		return tTeacherMapper.selectByExample(example);
	}

	//获取该老师教的课程
	public List<TCourseTeacher> getCourseId(String id) {
		TCourseTeacherExample example=new TCourseTeacherExample();
		com.ass.pojo.TCourseTeacherExample.Criteria criteria = example.createCriteria();
		criteria.andTeacheridEqualTo(id);
		return tCourseTeacherMapper.selectByExample(example);
	}

	//获取所有老师
	public List<TTeacher> findAllAjaxs() {
		TTeacherExample example=new TTeacherExample();
		Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(1);
		return tTeacherMapper.selectByExample(example);
	}
}
