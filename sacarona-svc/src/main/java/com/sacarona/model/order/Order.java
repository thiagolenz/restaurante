package com.sacarona.model.order;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.sacarona.model.AbstractEntity;
import com.sacarona.model.world.City;
import com.sacarona.model.world.Country;
import com.sacarona.model.world.Province;

@Entity
@Table(name="orders")
public class Order extends AbstractEntity {
	@SequenceGenerator(name="seq_order",
			sequenceName="seq_order",
			allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
	generator="seq_order")
	@Id
	private Long id;
	private String productName;
	private String productBrand;
	private String productPrice;
	private String productDescription;
	
	@ManyToOne
	@JoinColumn(name = "country_destiny_id")
	private Country countryDestiny;
	
	@ManyToOne
	@JoinColumn(name = "province_destiny_id")
	private Province provinceDestiny;
	
	@ManyToOne
	@JoinColumn(name = "city_destiny_id")
	private City cityDestiny;
	private Date wishDeliveryDate;

	@ManyToOne
	@JoinColumn(name = "country_origin_id")
	private Country countryOrigin;
	private BigDecimal bonus;
	private Long userId;
	private Date createDate;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;
	
	@Transient
	private String userName;
	@Transient
	private BigDecimal score;
	
	public Order() {
		
	}
	
	public Order(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductBrand() {
		return productBrand;
	}
	public void setProductBrand(String productBrand) {
		this.productBrand = productBrand;
	}
	public String getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public Country getCountryDestiny() {
		return countryDestiny;
	}
	public void setCountryDestiny(Country countryDestiny) {
		this.countryDestiny = countryDestiny;
	}
	public Province getProvinceDestiny() {
		return provinceDestiny;
	}
	public void setProvinceDestiny(Province provinceDestiny) {
		this.provinceDestiny = provinceDestiny;
	}
	public City getCityDestiny() {
		return cityDestiny;
	}
	public void setCityDestiny(City cityDestiny) {
		this.cityDestiny = cityDestiny;
	}
	public Date getWishDeliveryDate() {
		return wishDeliveryDate;
	}
	public void setWishDeliveryDate(Date wishDeliveryDate) {
		this.wishDeliveryDate = wishDeliveryDate;
	}
	public BigDecimal getBonus() {
		return bonus;
	}
	public void setBonus(BigDecimal bonus) {
		this.bonus = bonus;
	}
	
	public Long getUserId() {
		return userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Country getCountryOrigin() {
		return countryOrigin;
	}
	public void setCountryOrigin(Country countryOrigin) {
		this.countryOrigin = countryOrigin;
	}
	
	public OrderStatus getOrderStatus() {
		return orderStatus;
	}
	
	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public BigDecimal getScore() {
		return score;
	}

	public void setScore(BigDecimal score) {
		this.score = score;
	}	
}
