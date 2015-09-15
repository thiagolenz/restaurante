package com.sacarona.model.dealing;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

import com.sacarona.model.AbstractEntity;
import com.sacarona.model.user.User;

@Entity
public class Receipt extends AbstractEntity {
	@SequenceGenerator(name="seq_receipt",
			sequenceName="seq_receipt",
			allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
	generator="seq_receipt")
	@Id
	private Long id;
	private Long userId;
	private Long otherUserId;
	private String description;
	private String lastCreditCardNumbers;
	private BigDecimal value;
	private String paymentControl;
	private Date paymentDate;
	
	@Transient
	private User otherUser;
	
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
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLastCreditCardNumbers() {
		return lastCreditCardNumbers;
	}
	public void setLastCreditCardNumbers(String lastCreditCardNumbers) {
		this.lastCreditCardNumbers = lastCreditCardNumbers;
	}
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	public String getPaymentControl() {
		return paymentControl;
	}
	public void setPaymentControl(String paymentControl) {
		this.paymentControl = paymentControl;
	}
	public Date getPaymentDate() {
		return paymentDate;
	}
	
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	public Long getOtherUserId() {
		return otherUserId;
	}
	public void setOtherUserId(Long otherUserId) {
		this.otherUserId = otherUserId;
	}
	public User getOtherUser() {
		return otherUser;
	}
	public void setOtherUser(User otherUser) {
		this.otherUser = otherUser;
	}
}
