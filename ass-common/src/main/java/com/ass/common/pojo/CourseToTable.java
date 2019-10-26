package com.ass.common.pojo;

/**
 * 用于存放要显示到页面的课表信息
 * @author chenfuyuan
 *
 */

public class CourseToTable {
	private String className;
	private String courseName;
	private String teacherName;
	private String classRoomName;
	private String time;
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getClassRoomName() {
		return classRoomName;
	}
	public void setClassRoomName(String classRoomName) {
		this.classRoomName = classRoomName;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
}
