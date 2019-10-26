package com.ass.manager.controller;

import com.ass.common.pojo.CourseToTable;
import com.ass.common.pojo.EasyUIDataGridResult;
import com.ass.manager.service.*;
import com.ass.manager.service.autoPaike.GAAlgorithm;
import com.ass.pojo.TReady;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 自动排课
 * @author chenfuyuan
 *
 */

@Controller
public class AutoController {
	
	@Autowired
	private GAAlgorithm gAAlgorithm;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private ClassService classService;
	
	@Autowired
	private ClassRoomService classRoomService;
	
	@Autowired
	private AutoService autoService;
	
	@Value("${currentPageCount}")
	private Integer currentPageCount;
	
	//自动排课
	@RequestMapping("/ass/auto")
	@ResponseBody
	public String auto(HttpServletRequest request) {
		String groups = request.getParameter("groups");
		gAAlgorithm.caculte(groups);
		return "1";
	}
	
	//获取班级课表
	@RequestMapping("/ass/findCourse")
	@ResponseBody
	public List<CourseToTable> findCourse(HttpServletRequest request) {
		String classId = request.getParameter("classId");
		List<TReady> Tlist=autoService.findByClassId(classId);
		List<CourseToTable> list=new ArrayList<CourseToTable>();
		for (TReady tReady : Tlist) {
			CourseToTable courseToTable=new CourseToTable();
			courseToTable.setCourseName(courseService.findCourseName(tReady.getCourseid()));
			courseToTable.setTeacherName(teacherService.findTeacherName(tReady.getTeacherid()));
			courseToTable.setClassRoomName(classRoomService.findClassRoomName(tReady.getClassroomid()));
			courseToTable.setClassName(classService.fingClassName(tReady.getClassid()));
			courseToTable.setTime(tReady.getTime());
			list.add(courseToTable);
		}
		return list;
	}
	
	//获取教师课表
	@RequestMapping("/ass/findTeacherCourse")
	@ResponseBody
	public List<CourseToTable> findTeacherCourse(HttpServletRequest request) {
		String teacherId = request.getParameter("teacherId");
		List<TReady> Tlist=autoService.findByTeacherId(teacherId);
		List<CourseToTable> list=new ArrayList<CourseToTable>();
		for (TReady tReady : Tlist) {
			CourseToTable courseToTable=new CourseToTable();
			courseToTable.setCourseName(courseService.findCourseName(tReady.getCourseid()));
			courseToTable.setTeacherName(teacherService.findTeacherName(tReady.getTeacherid()));
			courseToTable.setClassRoomName(classRoomService.findClassRoomName(tReady.getClassroomid()));
			courseToTable.setClassName(classService.fingClassName(tReady.getClassid()));
			courseToTable.setTime(tReady.getTime());
			list.add(courseToTable);
		}
		return list;
	}
	
	//获取教室课表
	@RequestMapping("/ass/findClassRoomCourse")
	@ResponseBody
	public List<CourseToTable> findClassRoomCourse(HttpServletRequest request) {
		String input = request.getParameter("input");
		Integer classRoomId=classRoomService.findIdByName(input);
		List<TReady> Tlist=autoService.findClassRoomById(classRoomId);
		List<CourseToTable> list=new ArrayList<CourseToTable>();
		for (TReady tReady : Tlist) {
			CourseToTable courseToTable=new CourseToTable();
			courseToTable.setCourseName(courseService.findCourseName(tReady.getCourseid()));
			courseToTable.setTeacherName(teacherService.findTeacherName(tReady.getTeacherid()));
			courseToTable.setClassRoomName(classRoomService.findClassRoomName(tReady.getClassroomid()));
			courseToTable.setClassName(classService.fingClassName(tReady.getClassid()));
			courseToTable.setTime(tReady.getTime());
			list.add(courseToTable);
		}
		return list;
	}

	//获取排课信息
	@RequestMapping("/findAutoCourse/{currentPage}")
	public String findList(Model model,@PathVariable("currentPage")Integer currentPage) {
		if(currentPage==null){
			currentPage=1;
		}
		Integer currentCount=currentPageCount;		//设置每页显示的条数
		EasyUIDataGridResult result=autoService.findList(currentPage,currentCount);
		int totalPage=(int) Math.ceil(1.0*result.getTotal()/currentCount);		//计算总页数
		model.addAttribute("result",result);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("currentPageCount", currentPageCount);
		return "auto";
	}
	
	//删除一条排课信息
	@RequestMapping("/auto/delete/{id}")
	public String delete(@PathVariable("id")Long id) {
		autoService.deleteById(id);
		return "redirect:/findAutoCourse/1";
	}
	
	//修改排课的课程信息
	@RequestMapping("/auto/changeCourseId")
	public void editCourse(HttpServletRequest request) {
		//获取参数
		String idSting= request.getParameter("id");
		Long id=(long) Integer.parseInt(idSting);
		String courseIdSting= request.getParameter("courseId");
		Integer courseId=Integer.parseInt(courseIdSting);
		//修改课程
		autoService.editCourse(id,courseId);
	}
	
	//修改排课的课程信息
	@RequestMapping("/auto/changeTeacherId")
	public void editTeacher(HttpServletRequest request) {
		//获取参数
		String idSting= request.getParameter("id");
		Long id=(long) Integer.parseInt(idSting);
		String teacherId= request.getParameter("teacherId");
		//修改课程
		autoService.editTeacher(id,teacherId);
	}
	
	//修改排课的课程信息
	@RequestMapping("/auto/changeClassId")
	public void editClass(HttpServletRequest request) {
		//获取参数
		String idSting= request.getParameter("id");
		Long id=(long) Integer.parseInt(idSting);
		String classId= request.getParameter("classId");
		//修改课程
		autoService.editClass(id,classId);
	}
	
	//修改排课的课程信息
	@RequestMapping("/auto/changeClassRoomId")
	public void editClassRoom(HttpServletRequest request) {
		//获取参数
		String idSting= request.getParameter("id");
		Long id=(long) Integer.parseInt(idSting);
		String classroomIdString= request.getParameter("classroomId");
		Integer classroomId=Integer.parseInt(classroomIdString);
		//修改课程
		autoService.editClassRoom(id,classroomId);
	}
	
	//修改排课的星期几
	@RequestMapping("/auto/changeWeek")
	public void week(HttpServletRequest request) {
		//获取参数
		String idSting= request.getParameter("id");
		Long id=(long) Integer.parseInt(idSting);
		String weekString= request.getParameter("week");
		Integer week=Integer.parseInt(weekString);
		TReady tReady=autoService.get(id);
		Integer oldTime = Integer.parseInt(tReady.getTime());
		//判断其上课时间为周几
		Integer w=0;
		if(oldTime<=5){
			w=1;
		}
		if(oldTime<=10&&oldTime>5){
			w=2;
		}
		if(oldTime<=15&&oldTime>10){
			w=3;
		}
		if(oldTime<=20&&oldTime>15){
			w=4;
		}
		if(oldTime<=25&&oldTime>20){
			w=5;
		}
		//根据上课时间在周几改动排课表的上课时间
		if(week>w&&w>0){
			Integer time=oldTime+((week-1)*5);
			tReady.setTime(time.toString());
			autoService.editTime(tReady);
		}
		if(week<w&&w>0){
			Integer time=oldTime-((week-1)*5);
			tReady.setTime(time.toString());
			autoService.editTime(tReady);
		}
	}
	
	//修改排课的课程信息
	@RequestMapping("/auto/changeTime")
	public void editTime(HttpServletRequest request) {
		//获取参数
		String idSting= request.getParameter("id");
		Long id=(long) Integer.parseInt(idSting);
		String timeString= request.getParameter("time");
		Integer time=Integer.parseInt(timeString);
		TReady tReady=autoService.get(id);
		Integer oldTime = Integer.parseInt(tReady.getTime());
		//判断其上课时间为第几大节
		Integer w=0;
		if(oldTime==1 || oldTime==6|| oldTime==11 || oldTime==16 || oldTime==21){
			w=1;
		}
		if(oldTime==2 || oldTime==7 || oldTime==12 || oldTime==17 || oldTime==22){
			w=2;
		}
		if(oldTime==3 || oldTime==8 || oldTime==13 || oldTime==18 || oldTime==23){
			w=3;
		}
		if(oldTime==4 || oldTime==9 || oldTime==14 || oldTime==19 || oldTime==24){
			w=4;
		}
		if(oldTime==5 || oldTime==10 || oldTime==15 || oldTime==20 || oldTime==25){
			w=5;
		}
		if(time>w&&w>0){
			Integer newTime=oldTime+time-w;
			tReady.setTime(newTime.toString());
			autoService.editTime(tReady);
		}
		if(time<w&&w>0){
			Integer newTime=oldTime-(w-time);
			tReady.setTime(newTime.toString());
			autoService.editTime(tReady);
		}
	}
}
