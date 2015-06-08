package com.sacarona.model.feedback;

import java.math.BigDecimal;
import java.util.Date;

import com.sacarona.model.AbstractEntity;

public class Feedback extends AbstractEntity {
	private Long id;
	private Long userGaveId;
	private Long userReceivedId;
	private BigDecimal score;
	private String message;
	private Date feedbackDate;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserGaveId() {
		return userGaveId;
	}
	public void setUserGaveId(Long userGaveId) {
		this.userGaveId = userGaveId;
	}
	public Long getUserReceivedId() {
		return userReceivedId;
	}
	public void setUserReceivedId(Long userReceivedId) {
		this.userReceivedId = userReceivedId;
	}
	public BigDecimal getScore() {
		return score;
	}
	public void setScore(BigDecimal score) {
		this.score = score;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getFeedbackDate() {
		return feedbackDate;
	}
	public void setFeedbackDate(Date feedbackDate) {
		this.feedbackDate = feedbackDate;
	}

}
