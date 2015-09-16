package com.sacarona.dao.impl;

import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sacarona.dao.CityDAO;
import com.sacarona.dao.UserDAO;
import com.sacarona.dao.UserProfileDAO;
import com.sacarona.model.user.User;
import com.sacarona.model.user.UserProfile;

@Repository
public class UserProfileDaoImpl extends AbstractJpaDaoImpl<UserProfile> implements UserProfileDAO {
	@Autowired
	private CityDAO cityDao;
	
	@Autowired
	private UserDAO userDAO;
	
	public UserProfile findByUserId (Long userId, String lang) {
		TypedQuery<UserProfile> query = em.createQuery("from UserProfile o where o.userId = :userId ", UserProfile.class);
		query.setParameter("userId", userId);
		UserProfile result = singleQuery(query);
		if (result != null)
			cityDao.completeTheName(result.getCity(), lang);
		return result;
	}
	
	@Override
	public User findBySocialMediaAndEmailSecondary(User user) {
		StringBuilder strQuery = new StringBuilder("from UserProfile o where o.secondarySocialMediaId = :socialMediaId ");
		strQuery.append(" and o.secondaryEmail = :email");
		TypedQuery<UserProfile> query = em.createQuery(strQuery.toString(), UserProfile.class);
		query.setParameter("socialMediaId", user.getSocialMediaId());
		query.setParameter("email", user.getEmail());
		UserProfile result = singleQuery(query);
		if (result != null)
			return userDAO.findById(User.class, result.getUserId());
		return null;
	}
}
