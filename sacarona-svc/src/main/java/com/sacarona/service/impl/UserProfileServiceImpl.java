package com.sacarona.service.impl;

import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sacarona.common.svc.exception.BusinessException;
import com.sacarona.dao.UserProfileDAO;
import com.sacarona.model.user.UserProfile;
import com.sacarona.service.UserProfileService;

@Service
public class UserProfileServiceImpl implements UserProfileService {
	@Autowired private UserProfileDAO profileDAO;

	@Transactional
	public UserProfile insert(UserProfile userProfile) {
		return profileDAO.insert(userProfile);
	}

	@Transactional
	public UserProfile update(UserProfile userProfile, Long id) {
		profileDAO.update(userProfile, id);
		return userProfile;
	}

	@Transactional
	public UserProfile findByUserId (Long id) throws BusinessException {
		try {
			return profileDAO.findByUserId(id);
		} catch (UnknownHostException e) {
			throw new BusinessException(e);
		}
	}

}
