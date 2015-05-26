package com.sacarona.service.impl;

import java.net.UnknownHostException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sacarona.common.svc.exception.BusinessException;
import com.sacarona.dao.AppUserAuthDAO;
import com.sacarona.model.User;
import com.sacarona.model.mobile.AppUserAuth;
import com.sacarona.service.AppUserAuthService;

@Service
public class AppUserAuthServiceImpl implements AppUserAuthService {
	@Autowired
	private AppUserAuthDAO appUserAuthDAO;
	
	public AppUserAuth createNew (User user) {
		AppUserAuth auth = new AppUserAuth();
		auth.setAppToken(UUID.randomUUID().toString());
		auth.setUserToken(UUID.randomUUID().toString());
		auth.setUserId(user.getId());
		
		return appUserAuthDAO.insert(auth);
	}

	public AppUserAuth find(AppUserAuth userAuth) throws BusinessException {
		try {
			return appUserAuthDAO.find(userAuth);
		} catch (UnknownHostException e) {
			throw new BusinessException(e);
		}
	}
}
