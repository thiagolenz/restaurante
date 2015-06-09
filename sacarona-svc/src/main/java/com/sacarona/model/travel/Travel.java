package com.sacarona.model.travel;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.sacarona.model.AbstractEntity;
import com.sacarona.model.world.City;
import com.sacarona.model.world.Country;
import com.sacarona.model.world.Province;

public class Travel extends AbstractEntity {
	private Long id;
	private Country countryOrigin;
	private Province provinceOrigin;
	private City cityOrigin;
	
	private Country countryDestiny;
	private Province provinceDestiny;
	private City cityDestiny;  
	
	private Date departureDate;
	private Date backDate;
	
	private boolean confirmed;
	private boolean canceled;
	
	private Date createDate;
	private Long userId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Country getCountryOrigin() {
		return countryOrigin;
	}
	public void setCountryOrigin(Country countryOrigin) {
		this.countryOrigin = countryOrigin;
	}
	public Province getProvinceOrigin() {
		return provinceOrigin;
	}
	public void setProvinceOrigin(Province provinceOrigin) {
		this.provinceOrigin = provinceOrigin;
	}
	public City getCityOrigin() {
		return cityOrigin;
	}
	public void setCityOrigin(City cityOrigin) {
		this.cityOrigin = cityOrigin;
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
	public Date getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}
	public Date getBackDate() {
		return backDate;
	}
	public void setBackDate(Date backDate) {
		this.backDate = backDate;
	}
	public boolean isConfirmed() {
		return confirmed;
	}
	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}
	public boolean isCanceled() {
		return canceled;
	}
	public void setCanceled(boolean canceled) {
		this.canceled = canceled;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	private SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	
	@Override
	public String toString() {
		return "id = "+id + " - departureDAte " +format.format(departureDate);
	}
	
}
