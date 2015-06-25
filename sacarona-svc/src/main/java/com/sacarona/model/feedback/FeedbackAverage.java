package com.sacarona.model.feedback;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import com.sacarona.model.AbstractEntity;

@Entity
public class FeedbackAverage extends AbstractEntity {
	@SequenceGenerator(name="seq_feedback_average",
			sequenceName="seq_feedback_average",
			allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
	generator="seq_feedback_average")
	@Id
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
