package com.sacarona.model.mobile;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import com.sacarona.model.AbstractEntity;

@Entity
public class CellPhoneRegister extends AbstractEntity{
	@SequenceGenerator(name="seq_cell_phone_register",
			sequenceName="seq_cell_phone_register",
			allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
	generator="seq_cell_phone_register")
	@Id
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
