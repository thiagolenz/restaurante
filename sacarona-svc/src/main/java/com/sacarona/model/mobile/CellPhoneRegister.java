package com.sacarona.model.mobile;

import com.sacarona.model.AbstractEntity;
import com.sacarona.model.user.User;

public class CellPhoneRegister extends AbstractEntity{
	private Long id;
	private User user;
	
	private String regId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	
}
