package com.ass.pojo;

import java.util.Date;

public class TDepartment {
    private Integer id;

    private String name;

    private String dean;

    private String explains;

    private Byte status;

    private Date created;

    private Date updated;
    
    public TDepartment(){
    	super();
    }
    
    public TDepartment(String name, String dean, String explains, Byte status, Date created, Date updated) {
		super();
		this.name = name;
		this.dean = dean;
		this.explains = explains;
		this.status = status;
		this.created = created;
		this.updated = updated;
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

    public String getDean() {
        return dean;
    }

    public void setDean(String dean) {
        this.dean = dean == null ? null : dean.trim();
    }

    public String getExplains() {
        return explains;
    }

    public void setExplains(String explains) {
        this.explains = explains == null ? null : explains.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
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
}