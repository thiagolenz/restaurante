package com.sacarona.service.impl;

import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sacarona.common.svc.exception.BusinessException;
import com.sacarona.dao.UserDAO;
import com.sacarona.model.User;
import com.sacarona.model.mobile.AppUserAuth;
import com.sacarona.service.AppUserAuthService;
import com.sacarona.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private AppUserAuthService appUserAuthService;
	
	@Override
	public User findOrCreate (User user) throws BusinessException {
		User userTemp;
		try {
			userTemp = userDAO.findBySocialMediaAndEmail(user);
		} catch (UnknownHostException e) {
			throw new BusinessException(e);
		}
		if (userTemp == null)
			return insert(user);
		return userTemp;
	}

	public User insert(User user) throws BusinessException {
		userDAO.insert(user);
		return user;
	}

	@Override
	public User updateUser(User user, Long id) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(Long id) throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public User findByTokenAndApp(String userToken, String appToken) throws BusinessException {
		AppUserAuth auth = appUserAuthService.find(new AppUserAuth(appToken, userToken));
		if (auth != null)
			return userDAO.findById(User.class, auth.getUserId());
		return null;
	}

	

}
