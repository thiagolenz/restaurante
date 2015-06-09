package com.sacarona.model.track;

import java.util.Date;

import com.sacarona.model.AbstractEntity;

public class LocationTrack extends AbstractEntity {
	private Long id;
	private Long userId;
	private String latitude;
	private String longitude;
	private Date trackDate;
	
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
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	public Date getTrackDate() {
		return trackDate;
	}
	
	public void setTrackDate(Date trackDate) {
		this.trackDate = trackDate;
	}

}
