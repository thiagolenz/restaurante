package com.sacarona.model.mobile;

import java.util.Date;

import com.sacarona.model.AbstractEntity;

public class CellPhoneRegister extends AbstractEntity{
	private Long id;
	private Long userId;
	private String regId;
	private Date lastConnected;
	private String type;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	
	public Date getLastConnected() {
		return lastConnected;
	}
	
	public void setLastConnected(Date lastConnected) {
		this.lastConnected = lastConnected;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
}
