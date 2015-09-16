package com.sacarona.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sacarona.common.svc.exception.BusinessException;
import com.sacarona.dao.UserDAO;
import com.sacarona.dao.UserProfileDAO;
import com.sacarona.model.mobile.AppUserAuth;
import com.sacarona.model.user.User;
import com.sacarona.service.AppUserAuthService;
import com.sacarona.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private AppUserAuthService appUserAuthService;
	
	@Autowired
	private UserProfileDAO userProfileDAO;
	
	@Transactional
	public User findOrCreate (User user) throws BusinessException {
		User userTemp;
		userTemp = userDAO.findBySocialMediaAndEmail(user);
		if (userTemp == null){
			userTemp = userProfileDAO.findBySocialMediaAndEmailSecondary(user);
			if (userTemp == null)
				return insert(user);
		}
		return userTemp;
	}

	@Transactional
	public User insert(User user) throws BusinessException {
		userDAO.insert(user);
		return user;
	}

	@Transactional
	public User updateUser(User user, Long id) throws BusinessException {
		userDAO.update(user, id);
		return user;
	}

	@Transactional
	public User findByTokenAndApp(String userToken, String appToken) throws BusinessException {
		AppUserAuth auth = appUserAuthService.find(new AppUserAuth(appToken, userToken));
		if (auth != null)
			return userDAO.findById(User.class, auth.getUserId());
		return null;
	}
}
