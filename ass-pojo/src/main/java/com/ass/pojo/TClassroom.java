package com.ass.pojo;

import java.util.Date;

public class TClassroom {
    private Integer id;

    private String name;

    private String type;

    private Integer number;

    private Integer state;

    private Date craeted;

    private Date updated;

    private Integer status;
    
    public TClassroom(){
    	
    }

    public TClassroom(String name, String type, Integer number, Integer state, Date craeted, Date updated,
			Integer status) {
		super();
		this.name = name;
		this.type = type;
		this.number = number;
		this.state = state;
		this.craeted = craeted;
		this.updated = updated;
		this.status = status;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCraeted() {
        return craeted;
    }

    public void setCraeted(Date craeted) {
        this.craeted = craeted;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}