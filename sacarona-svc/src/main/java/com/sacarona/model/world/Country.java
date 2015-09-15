package com.sacarona.model.world;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import com.sacarona.model.AbstractEntity;

@Entity
public class Country extends AbstractEntity{
	@SequenceGenerator(name="seq_country",
			sequenceName="seq_country",
			allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
	generator="seq_country")
	@Id
	private Long id;
	private String nameEnglish;
	private String nameSpanish;
	private String namePortuguese;
	private String iso;
	private String un;
	private Long externalId;
	private String alternativeNames;
	
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
	
	public String getAlternativeNames() {
		return alternativeNames;
	}
	
	public void setAlternativeNames(String alternativeNames) {
		this.alternativeNames = alternativeNames;
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
				+ namePortuguese + ", iso=" + iso + ", un=" + un
				+ ", externalId=" + externalId + ", alternativeNames="
				+ alternativeNames + "]";
	}
}
