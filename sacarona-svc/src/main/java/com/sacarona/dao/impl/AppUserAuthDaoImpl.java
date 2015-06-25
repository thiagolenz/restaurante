package com.sacarona.dao.impl;

import java.net.UnknownHostException;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.sacarona.dao.AppUserAuthDAO;
import com.sacarona.model.mobile.AppUserAuth;

@Repository
public class AppUserAuthDaoImpl extends AbstractJpaDaoImpl<AppUserAuth> implements AppUserAuthDAO {

	@Override
	public AppUserAuth find(AppUserAuth userAuth) throws UnknownHostException {
		TypedQuery<AppUserAuth> query = em.createQuery("from AppUserAuth o where o.appToken = :appToken and o.userToken = :userToken ", AppUserAuth.class);
		query.setParameter("appToken", userAuth.getAppToken());
		query.setParameter("userToken", userAuth.getUserToken());
		return singleQuery(query);
	}
}
