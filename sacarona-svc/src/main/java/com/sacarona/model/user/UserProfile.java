package com.sacarona.model.user;

import com.sacarona.model.AbstractEntity;
import com.sacarona.model.world.City;
import com.sacarona.model.world.Country;
import com.sacarona.model.world.Province;

public class UserProfile extends AbstractEntity {
	private Long id;
	private String cellPhoneNumber;
	private String cellPhoneType;
	private String whatsAppNumber;
	private String address;
	private String addressType;
	private City city;
	private Country country;
	private Province province;
	private String zipCode;
	private Long userId; 
	private boolean contactTwitter;
	private boolean contactFacebook;
	private boolean contactGoogle;
	private boolean contactLinkedin;
	
	private String facebookUrl;
	private String googleUrl;
	private String twitterUrl;
	private String linkedinUrl;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCellPhoneNumber() {
		return cellPhoneNumber;
	}
	public void setCellPhoneNumber(String cellPhoneNumber) {
		this.cellPhoneNumber = cellPhoneNumber;
	}
	public String getCellPhoneType() {
		return cellPhoneType;
	}
	public void setCellPhoneType(String cellPhoneType) {
		this.cellPhoneType = cellPhoneType;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddressType() {
		return addressType;
	}
	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	public Province getProvince() {
		return province;
	}
	public void setProvince(Province province) {
		this.province = province;
	}
	
	public String getZipCode() {
		return zipCode;
	}
	
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	public Long getUserId() {
		return userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public boolean isContactTwitter() {
		return contactTwitter;
	}
	public void setContactTwitter(boolean contactTwitter) {
		this.contactTwitter = contactTwitter;
	}
	public boolean isContactFacebook() {
		return contactFacebook;
	}
	public void setContactFacebook(boolean contactFacebook) {
		this.contactFacebook = contactFacebook;
	}
	public boolean isContactGoogle() {
		return contactGoogle;
	}
	public void setContactGoogle(boolean contactGoogle) {
		this.contactGoogle = contactGoogle;
	}
	public String getFacebookUrl() {
		return facebookUrl;
	}
	public void setFacebookUrl(String facebookUrl) {
		this.facebookUrl = facebookUrl;
	}
	public String getGoogleUrl() {
		return googleUrl;
	}
	public void setGoogleUrl(String googleUrl) {
		this.googleUrl = googleUrl;
	}
	public String getTwitterUrl() {
		return twitterUrl;
	}
	public void setTwitterUrl(String twitterUrl) {
		this.twitterUrl = twitterUrl;
	}	
	public String getWhatsAppNumber() {
		return whatsAppNumber;
	}
	public void setWhatsAppNumber(String whatsAppNumber) {
		this.whatsAppNumber = whatsAppNumber;
	}
	public boolean isContactLinkedin() {
		return contactLinkedin;
	}
	public void setContactLinkedin(boolean contactLinkedin) {
		this.contactLinkedin = contactLinkedin;
	}
	public String getLinkedinUrl() {
		return linkedinUrl;
	}
	public void setLinkedinUrl(String linkedinUrl) {
		this.linkedinUrl = linkedinUrl;
	}
}
