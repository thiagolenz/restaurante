package com.sacarona.dao.impl;

import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sacarona.dao.CityDAO;
import com.sacarona.dao.UserProfileDAO;
import com.sacarona.model.user.UserProfile;

@Repository
public class UserProfileDaoImpl extends AbstractJpaDaoImpl<UserProfile> implements UserProfileDAO {
	@Autowired
	private CityDAO cityDao;
	
	public UserProfile findByUserId (Long userId, String lang) {
		TypedQuery<UserProfile> query = em.createQuery("from UserProfile o where o.userId = :userId ", UserProfile.class);
		query.setParameter("userId", userId);
		UserProfile result = singleQuery(query);
		cityDao.completeTheName(result.getCity(), lang);
		return result;
	}
}
