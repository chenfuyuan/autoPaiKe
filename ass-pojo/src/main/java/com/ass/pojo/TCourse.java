package com.ass.pojo;

import java.util.Date;

public class TCourse {
    private Integer id;

    private String name;

    private Integer weektime;

    private Integer credit;

    private Date created;

    private Date updated;

    private String assess;

    private Integer type;

    private Integer number;

    private Integer savnum;

    private Integer departmentid;

    private Integer status;

    private String groups;

    private String way;
    
public TCourse(){
    	
    }
    
    public TCourse(String name, Integer weektime, Integer credit, Date created, Date updated, String assess,
			Integer type, Integer number, Integer savnum, Integer departmentid, Integer status, String groups,
			String way) {
		super();
		this.name = name;
		this.weektime = weektime;
		this.credit = credit;
		this.created = created;
		this.updated = updated;
		this.assess = assess;
		this.type = type;
		this.number = number;
		this.savnum = savnum;
		this.departmentid = departmentid;
		this.status = status;
		this.groups = groups;
		this.way = way;
	}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getWeektime() {
        return weektime;
    }

    public void setWeektime(Integer weektime) {
        this.weektime = weektime;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
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

    public String getAssess() {
        return assess;
    }

    public void setAssess(String assess) {
        this.assess = assess == null ? null : assess.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getSavnum() {
        return savnum;
    }

    public void setSavnum(Integer savnum) {
        this.savnum = savnum;
    }

    public Integer getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(Integer departmentid) {
        this.departmentid = departmentid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getGroups() {
        return groups;
    }

    public void setGroups(String groups) {
        this.groups = groups == null ? null : groups.trim();
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way == null ? null : way.trim();
    }
}