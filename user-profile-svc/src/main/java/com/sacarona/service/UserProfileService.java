package com.sacarona.service;

import com.sacarona.common.svc.exception.BusinessException;
import com.sacarona.model.user.UserProfile;

public interface UserProfileService {
	UserProfile insert (UserProfile userProfile);
	
	UserProfile update (UserProfile userProfile, Long id);
	
	UserProfile findByUserId (Long id) throws BusinessException;
}
