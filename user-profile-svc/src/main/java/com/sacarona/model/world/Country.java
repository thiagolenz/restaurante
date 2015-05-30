package com.sacarona.model.world;

import com.sacarona.model.AbstractEntity;

public class Country extends AbstractEntity{
	private Long id;
	private String nameEnglish;
	private String nameSpanish;
	private String namePortuguese;
	private String iso;
	private String un;
	private Long externalId;
	
	public Country() {
	
	}
	
	public Country(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getIso() {
		return iso;
	}
	public void setIso(String iso) {
		this.iso = iso;
	}
	public String getUn() {
		return un;
	}
	public void setUn(String un) {
		this.un = un;
	}
	public String getNameEnglish() {
		return nameEnglish;
	}
	public void setNameEnglish(String nameEnglish) {
		this.nameEnglish = nameEnglish;
	}
	public String getNameSpanish() {
		return nameSpanish;
	}
	public void setNameSpanish(String nameSpanish) {
		this.nameSpanish = nameSpanish;
	}
	public String getNamePortuguese() {
		return namePortuguese;
	}
	public void setNamePortuguese(String namePortuguese) {
		this.namePortuguese = namePortuguese;
	}
	
	public Long getExternalId() {
		return externalId;
	}
	
	public void setExternalId(Long externalId) {
		this.externalId = externalId;
	}
	
	public String getNameByLang (String lang) {
		if (lang == null)
			lang = "en-US";
		if (lang.equals("en-US"))
			return nameEnglish;
		else if (lang.equals("es"))
			return nameSpanish;
		else 
			return namePortuguese;
	}
	
	@Override
	public String toString() {
		return "Country [id=" + id + ", nameEnglish=" + nameEnglish
				+ ", nameSpanish=" + nameSpanish + ", namePortuguese="
				+ namePortuguese + ", iso=" + iso + ", un=" + un + "]";
	}
}
