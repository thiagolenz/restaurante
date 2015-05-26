package com.sacarona.model;

public class User extends AbstractEntity{
	private Long id;
	private String name;
	private String lang;
	private String socialMediaId;
	private String email;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public String getSocialMediaId() {
		return socialMediaId;
	}
	public void setSocialMediaId(String socialMediaId) {
		this.socialMediaId = socialMediaId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + "]";
	}
}
