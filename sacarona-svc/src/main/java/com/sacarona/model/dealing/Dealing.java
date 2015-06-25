package com.sacarona.model.dealing;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.sacarona.model.AbstractEntity;
import com.sacarona.model.order.Order;
import com.sacarona.model.travel.Travel;
import com.sacarona.model.user.User;

@Entity
public class Dealing extends AbstractEntity {
	@SequenceGenerator(name="seq_dealing",
			sequenceName="seq_dealing",
			allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
	generator="seq_dealing")
	@Id
	private Long id;

	@ManyToOne
	@JoinColumn(name = "travel_id")
	private Travel travel;
	
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;
	
	@ManyToOne
	@JoinColumn(name = "order_user_id")
	private User orderUser;
	
	@ManyToOne
	@JoinColumn(name = "traveler_user_id")
	private User travelerUser;
	
	private boolean requesterPaid;
	private boolean travelerPaid;
	private BigDecimal amountTravelerPaid = BigDecimal.ZERO;
	private BigDecimal amountRequesterPaid = BigDecimal.ZERO;
	
	private Date createDate;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Travel getTravel() {
		return travel;
	}
	public void setTravel(Travel travel) {
		this.travel = travel;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public User getOrderUser() {
		return orderUser;
	}
	public void setOrderUser(User orderUser) {
		this.orderUser = orderUser;
	}
	public User getTravelerUser() {
		return travelerUser;
	}
	public void setTravelerUser(User travelerUser) {
		this.travelerUser = travelerUser;
	}
	public boolean isRequesterPaid() {
		return requesterPaid;
	}
	public void setRequesterPaid(boolean requesterPaid) {
		this.requesterPaid = requesterPaid;
	}
	public boolean isTravelerPaid() {
		return travelerPaid;
	}
	public void setTravelerPaid(boolean travelerPaid) {
		this.travelerPaid = travelerPaid;
	}
	public BigDecimal getAmountTravelerPaid() {
		return amountTravelerPaid;
	}
	public void setAmountTravelerPaid(BigDecimal amountTravelerPaid) {
		this.amountTravelerPaid = amountTravelerPaid;
	}
	public BigDecimal getAmountRequesterPaid() {
		return amountRequesterPaid;
	}
	public void setAmountRequesterPaid(BigDecimal amountRequesterPaid) {
		this.amountRequesterPaid = amountRequesterPaid;
	}
	
	public Date getCreateDate() {
		return createDate;
	}
	
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}
