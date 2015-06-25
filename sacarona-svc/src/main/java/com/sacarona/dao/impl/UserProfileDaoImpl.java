package com.sacarona.dao.impl;

import java.net.UnknownHostException;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.sacarona.dao.UserProfileDAO;
import com.sacarona.model.user.UserProfile;

@Repository
public class UserProfileDaoImpl extends AbstractJpaDaoImpl<UserProfile> implements UserProfileDAO {
	
	public UserProfile findByUserId (Long userId) throws UnknownHostException {
		TypedQuery<UserProfile> query = em.createQuery("from UserProfile o where o.userId = :userId ", UserProfile.class);
		query.setParameter("userId", userId);
		return singleQuery(query);
	}
}
