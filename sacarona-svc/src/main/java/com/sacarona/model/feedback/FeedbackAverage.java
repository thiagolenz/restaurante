package com.sacarona.model.feedback;

import java.math.BigDecimal;

import com.sacarona.model.AbstractEntity;

public class FeedbackAverage extends AbstractEntity {
	private Long id;
	private Long userId;
	private BigDecimal averageValue;
	
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
	public BigDecimal getAverageValue() {
		return averageValue;
	}
	public void setAverageValue(BigDecimal averageValue) {
		this.averageValue = averageValue;
	}
}
