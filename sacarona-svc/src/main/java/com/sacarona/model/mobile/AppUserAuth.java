package com.sacarona.model.mobile;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import com.sacarona.model.AbstractEntity;

@Entity
public class AppUserAuth extends AbstractEntity{
	@SequenceGenerator(name="seq_app_user_auth",
			sequenceName="seq_app_user_auth",
			allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
	generator="seq_app_user_auth")
	@Id
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
