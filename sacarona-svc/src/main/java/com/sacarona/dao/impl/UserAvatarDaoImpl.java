package com.sacarona.dao.impl;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.sacarona.dao.UserAvatarDAO;
import com.sacarona.model.user.UserAvatar;

@Repository
public class UserAvatarDaoImpl extends AbstractJpaDaoImpl<UserAvatar> implements
		UserAvatarDAO {

	@Override
	public UserAvatar findByUserId (Long id) {
		TypedQuery<UserAvatar> query = em.createQuery("from UserAvatar o where o.userId = :userId ", UserAvatar.class);
		query.setParameter("userId", id);
		return singleQuery(query);
	}
}
