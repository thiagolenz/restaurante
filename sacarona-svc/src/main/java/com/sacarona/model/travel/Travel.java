package com.sacarona.model.travel;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

import com.sacarona.model.AbstractEntity;
import com.sacarona.model.world.City;
import com.sacarona.model.world.Country;
import com.sacarona.model.world.Province;

@Entity
public class Travel extends AbstractEntity {
	@SequenceGenerator(name="seq_travel",
			sequenceName="seq_travel",
			allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
	generator="seq_travel")
	@Id
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "city_origin_id")
	private City cityOrigin;  
	
	@ManyToOne
	@JoinColumn(name = "province_origin_id")
	private Province provinceOrigin;

	@ManyToOne
	@JoinColumn(name = "country_origin_id")
	private Country countryOrigin;
	
	@ManyToOne
	@JoinColumn(name = "country_destiny_id")
	private Country countryDestiny;
	
	@ManyToOne
	@JoinColumn(name = "province_destiny_id")
	private Province provinceDestiny;
	
	@ManyToOne
	@JoinColumn(name = "city_destiny_id")
	private City cityDestiny;  
	
	private Date departureDate;
	private Date backDate;
	
	private boolean confirmed;
	private boolean canceled;
	
	private Date createDate;
	private Long userId;
	
	@Transient
	private String userName;
	@Transient
	private BigDecimal score;
	
	public Travel() {
		// TODO Auto-generated constructor stub
	}
	
	public Travel(Long id) {
		super();
		this.id = id;
	}
	
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

	public City getCityOrigin() {
		return cityOrigin;
	}

	public void setCityOrigin(City cityOrigin) {
		this.cityOrigin = cityOrigin;
	}

	public Province getProvinceOrigin() {
		return provinceOrigin;
	}

	public void setProvinceOrigin(Province provinceOrigin) {
		this.provinceOrigin = provinceOrigin;
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
