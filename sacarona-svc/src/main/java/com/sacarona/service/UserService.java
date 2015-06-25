package com.sacarona.service;

import com.sacarona.common.svc.exception.BusinessException;
import com.sacarona.model.user.User;

public interface UserService {
	String DUPLICATE_MESSAGE = "user.service.duplicate.record";
	
	User updateUser (User user, Long id) throws BusinessException;
	
	User findByTokenAndApp (String userToken, String appToken) throws BusinessException;
	
	User findOrCreate (User user) throws BusinessException;
	
}
