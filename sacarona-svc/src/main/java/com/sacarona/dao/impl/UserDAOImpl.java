package com.sacarona.dao.impl;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.sacarona.dao.UserDAO;
import com.sacarona.model.user.User;

@Repository
public class UserDAOImpl extends AbstractJpaDaoImpl<User> implements UserDAO {
	@Override
	public User findBySocialMediaAndEmail (User user){
		TypedQuery<User> query = em.createQuery("from User o where o.socialMediaId = :socialMediaId and o.email = :email ", User.class);
		query.setParameter("socialMediaId", user.getSocialMediaId());
		query.setParameter("email", user.getEmail());
		return singleQuery(query);
	}
}
