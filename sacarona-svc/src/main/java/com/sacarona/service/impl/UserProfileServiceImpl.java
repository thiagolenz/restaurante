package com.sacarona.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sacarona.common.svc.exception.BusinessException;
import com.sacarona.dao.UserDAO;
import com.sacarona.dao.UserProfileDAO;
import com.sacarona.model.user.User;
import com.sacarona.model.user.UserProfile;
import com.sacarona.service.UserProfileService;
import com.sacarona.service.UserRatingService;

@Service
public class UserProfileServiceImpl implements UserProfileService {
	@Autowired private UserProfileDAO profileDAO;
	@Autowired private UserDAO userDAO;
	@Autowired private UserRatingService ratingService;

	@Transactional
	public UserProfile insert(UserProfile userProfile) {
		userProfile.setCreateDate(new Date ());
		profileDAO.insert(userProfile);
		ratingService.recalculate(userProfile.getUserId());
		return userProfile;
	}

	@Transactional
	public UserProfile update(UserProfile userProfile, Long id) {
		profileDAO.update(userProfile, id);
		ratingService.recalculate(userProfile.getUserId());
		return userProfile;
	}

	@Transactional
	public UserProfile findByUserId (Long id,  String lang) throws BusinessException {
		UserProfile userProfile = profileDAO.findByUserId(id, lang);
		if (userProfile != null)
			userProfile.setUser(userDAO.findById(User.class, id));
		return userProfile;
	}

}
