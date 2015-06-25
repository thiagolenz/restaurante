package com.sacarona.model.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.sacarona.model.AbstractEntity;

@Entity
@Table(name="users")
public class User extends AbstractEntity {
	@SequenceGenerator(name="seq_users",
			sequenceName="seq_users",
			allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
	generator="seq_users")
	@Id
	private Long id;
	private String name;
	private String lang;
	private String socialMediaId;
	private String email;
	
	public User() {
		
	}
	
	public User(Long id) {
		super();
		this.id = id;
	}

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
