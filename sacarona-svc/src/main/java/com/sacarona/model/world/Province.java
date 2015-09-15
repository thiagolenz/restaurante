package com.sacarona.model.world;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

import com.sacarona.model.AbstractEntity;

@Entity
public class Province extends AbstractEntity {
	@SequenceGenerator(name="seq_province",
			sequenceName="seq_province",
			allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
	generator="seq_province")
	@Id
	private Long id;
	private String name;
	private String abbreviation;
	private String countryIso;
	private Long countryId;
	private String provinceId;
	
	@Transient
	private Country country;
	
	public Province() {
	}
	
	public Province(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		if (name == null)
			name = "";
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAbbreviation() {
		return abbreviation;
	}
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public Long getCountryId() {
		return countryId;
	}
	
	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}
	
	public void setCountry(Country country) {
		this.country = country;
	}
	
	public Country getCountry() {
		return country;
	}
	
	public String getCountryIso() {
		return countryIso;
	}
	public void setCountryIso(String countryIso) {
		this.countryIso = countryIso;
	}
	public String getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	@Override
	public String toString() {
		return "Province [id=" + id + ", name=" + name + ", abbreviation="
				+ abbreviation + ", countryIso=" + countryIso + ", countryId="
				+ countryId + ", country=" + country + "]";
	}
}
