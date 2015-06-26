package com.sacarona.model.world;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import com.sacarona.model.AbstractEntity;

@Entity
public class Location extends AbstractEntity {
	@SequenceGenerator(name="seq_location",
			sequenceName="seq_location",
			allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
	generator="seq_location")
	@Id
	private Long id;
	private String type;
	private String nameEnglish;
	private String namePortuguese;
	private String nameSpanish;
	private Long entityId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getNameEnglish() {
		return nameEnglish;
	}
	public void setNameEnglish(String nameEnglish) {
		this.nameEnglish = nameEnglish;
	}
	public String getNamePortuguese() {
		return namePortuguese;
	}
	public void setNamePortuguese(String namePortuguese) {
		this.namePortuguese = namePortuguese;
	}
	public String getNameSpanish() {
		return nameSpanish;
	}
	public void setNameSpanish(String nameSpanish) {
		this.nameSpanish = nameSpanish;
	}
	public Long getEntityId() {
		return entityId;
	}
	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}
}
