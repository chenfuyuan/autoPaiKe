package com.ass.pojo;

import java.util.Date;

public class TTeacher {
    private String id;

    private String name;

    private String password;

    private String code;

    private Date year;

    private String degree;

    private String title;

    private String sex;

    private String phone;

    private String address;

    private Integer status;

    private Date created;

    private Date updated;

    private Integer departmentid;
    
    public TTeacher(){
    	
    }

    public TTeacher(String id, String name, String password, String code, Date year, String degree, String title,
			String sex, String phone, String address, Integer status, Date created, Date updated,
			Integer departmentid) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.code = code;
		this.year = year;
		this.degree = degree;
		this.title = title;
		this.sex = sex;
		this.phone = phone;
		this.address = address;
		this.status = status;
		this.created = created;
		this.updated = updated;
		this.departmentid = departmentid;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree == null ? null : degree.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Integer getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(Integer departmentid) {
        this.departmentid = departmentid;
    }
}