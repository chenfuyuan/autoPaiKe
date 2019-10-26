package com.ass.pojo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TClass {
    private String id;

    private Integer serial;

    private String name;

    private Integer number;

    private Date created;

    private Date updated;

    private Integer departmentid;

    private Integer status;
    
    public TClass(){
    	super();
    }

    public TClass(String id, Integer serial, String name, Integer number, Date created, Date updated,
			Integer departmentid, Integer status) {
		super();
		this.id = id;
		this.serial = serial;
		this.name = name;
		this.number = number;
		this.created = created;
		this.updated = updated;
		this.departmentid = departmentid;
		this.status = status;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getSerial() {
        return serial;
    }

    public void setSerial(Integer serial) {
        this.serial = serial;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Date getCreated() {
    	return  created;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}