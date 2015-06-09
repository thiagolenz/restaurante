package com.sacarona.model.order;

import java.math.BigDecimal;
import java.util.Date;

import com.sacarona.model.AbstractEntity;
import com.sacarona.model.world.City;
import com.sacarona.model.world.Country;
import com.sacarona.model.world.Province;

public class Order extends AbstractEntity {
	private Long id;
	private String productName;
	private String productBrand;
	private String productPrice;
	private String productDescription;
	private String productImageBase64;
	private Country countryDestiny;
	private Province provinceDestiny;
	private City cityDestiny;
	private Date wishDeliveryDate;
	private Country countryOrigin;
	private BigDecimal bonus;
	private Long userId;
	private Date createDate;
	
	private OrderStatus orderStatus;
	
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
	public String getProductImageBase64() {
		return productImageBase64;
	}
	public void setProductImageBase64(String productImageBase64) {
		this.productImageBase64 = productImageBase64;
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
}
