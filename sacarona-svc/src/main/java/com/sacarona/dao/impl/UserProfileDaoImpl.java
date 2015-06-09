package com.sacarona.dao.impl;

import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.sacarona.dao.CityDAO;
import com.sacarona.dao.CountryDAO;
import com.sacarona.dao.ProvinceDAO;
import com.sacarona.dao.UserProfileDAO;
import com.sacarona.model.user.UserProfile;
import com.sacarona.model.world.City;
import com.sacarona.model.world.Country;
import com.sacarona.model.world.Province;

@Repository
public class UserProfileDaoImpl extends AbstractDaoImpl<UserProfile> implements UserProfileDAO {
	@Autowired private CityDAO cityDAO;
	@Autowired private CountryDAO countryDAO;
	@Autowired private ProvinceDAO provinceDAO;

	@Override
	protected String getSequenceName() {
		return "seq_user_profile";
	}

	@Override
	protected String getCollectionName() {
		return "user_profile";
	}

	@Override
	protected void mapObject(UserProfile profile, BasicDBObject destiny) {
		destiny.append("id", profile.getId());
		destiny.append("cellPhoneNumber", profile.getCellPhoneNumber());
		destiny.append("whatsAppNumber", profile.getWhatsAppNumber());
		destiny.append("cellPhoneType", profile.getCellPhoneType());
		destiny.append("address", profile.getAddress());
		destiny.append("addressType", profile.getAddressType());
		destiny.append("cityId", profile.getCity().getId());
		destiny.append("countryId", profile.getCountry().getId());
		destiny.append("provinceId", profile.getProvince().getId());
		destiny.append("zipCode", profile.getZipCode());
		destiny.append("userId", profile.getUserId());
		destiny.append("contactTwitter", profile.isContactTwitter());
		destiny.append("contactFacebook", profile.isContactFacebook());
		destiny.append("contactGoogle", profile.isContactTwitter());
		destiny.append("contactLinkedin", profile.isContactLinkedin());
		destiny.append("facebookUrl", profile.getFacebookUrl());
		destiny.append("twitterUrl", profile.getTwitterUrl());
		destiny.append("googleUrl", profile.getGoogleUrl());
		destiny.append("linkedinUrl", profile.getLinkedinUrl());
	}

	@Override
	protected UserProfile mapResult(BasicDBObject source) {
		UserProfile profile = new UserProfile();
		profile.setId(source.getLong("id"));
		profile.setCellPhoneNumber(source.getString("cellPhoneNumber"));
		profile.setWhatsAppNumber(source.getString("whatsAppNumber"));
		profile.setCellPhoneType(source.getString("cellPhoneType"));
		profile.setAddress(source.getString("address"));
		profile.setAddressType(source.getString("addressType"));
		profile.setCity(new City(source.getLong("cityId")));
		profile.setZipCode(source.getString("zipCode"));
		profile.setUserId(source.getLong("userId"));
		profile.setCountry(new Country(source.getLong("countryId")));
		profile.setProvince(new Province(source.getLong("provinceId")));
		profile.setContactFacebook(source.getBoolean("contactFacebook"));
		profile.setContactGoogle(source.getBoolean("contactGoogle"));
		profile.setContactTwitter(source.getBoolean("contactTwitter"));
		profile.setContactLinkedin(source.getBoolean("contactLinkedin"));
		profile.setFacebookUrl(source.getString("facebookUrl"));
		profile.setGoogleUrl(source.getString("googleUrl"));
		profile.setTwitterUrl(source.getString("twitterUrl"));
		profile.setLinkedinUrl(source.getString("linkedinUrl"));
		return profile;
	}
	

	public UserProfile findByUserId (Long userId) throws UnknownHostException {
		BasicDBObject query = new BasicDBObject();
		query.append("userId", userId);
		UserProfile profile = singleQuery(query);
		fillData(profile);
		return profile;
	}
	
	private void fillData (UserProfile profile) {
		profile.setCity(cityDAO.findById(City.class, profile.getCity().getId()));
		profile.setProvince(provinceDAO.findById(Province.class, profile.getProvince().getId()));
		profile.setCountry(countryDAO.findById(Country.class, profile.getCountry().getId()));
	}
}
