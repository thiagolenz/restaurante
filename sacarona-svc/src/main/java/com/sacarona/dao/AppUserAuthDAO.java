package com.sacarona.dao;

import java.net.UnknownHostException;

import com.sacarona.model.mobile.AppUserAuth;

public interface AppUserAuthDAO extends GenericDAO<AppUserAuth> {
	 AppUserAuth find(AppUserAuth userAuth) throws UnknownHostException;
}
