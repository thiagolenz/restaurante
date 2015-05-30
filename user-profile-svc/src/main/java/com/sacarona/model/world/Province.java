package com.sacarona.model.world;

import com.sacarona.model.AbstractEntity;

public class Province extends AbstractEntity {
	private Long id;
	private String name;
	private String abbreviation;
	private Long countryId;
	
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
	@Override
	public String toString() {
		return "Province [id=" + id + ", name=" + name + ", abbreviation="
				+ abbreviation + ", countryId=" + countryId + "]";
	}
	
}
