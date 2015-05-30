package com.sacarona.model.world;

import com.sacarona.model.AbstractEntity;

public class City extends AbstractEntity{
	private Long id;
	private String name;
	private String code;
	private String countryIso;
	private String provinceAbbreviation;
	private String completeName;
	
	public City() {
		// TODO Auto-generated constructor stub
	}
	
	public City(Long id) {
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
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCountryIso() {
		return countryIso;
	}
	public void setCountryIso(String countryIso) {
		this.countryIso = countryIso;
	}
	public String getProvinceAbbreviation() {
		return provinceAbbreviation;
	}
	public void setProvinceAbbreviation(String provinceAbbreviation) {
		this.provinceAbbreviation = provinceAbbreviation;
	}
	
	public String getCompleteName() {
		return completeName;
	}
	
	public void setCompleteName(String completeName) {
		this.completeName = completeName;
	}
	
	@Override
	public String toString() {
		return "City [id=" + id + ", name=" + name + ", code=" + code
				+ ", countryIso=" + countryIso + ", provinceAbbreviation="
				+ provinceAbbreviation + "]";
	}

}
