package com.sacarona.model.mobile;

import com.sacarona.model.AbstractEntity;

public class AppUserAuth extends AbstractEntity{
	private Long id;
	private String appToken;
	private String userToken;
	private Long userId;
	
	public AppUserAuth() {
		// TODO Auto-generated constructor stub
	}
	
	public AppUserAuth(String appToken, String userToken) {
		super();
		this.appToken = appToken;
		this.userToken = userToken;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAppToken() {
		return appToken;
	}
	public void setAppToken(String appToken) {
		this.appToken = appToken;
	}
	public String getUserToken() {
		return userToken;
	}
	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
}
