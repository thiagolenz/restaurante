package com.sacarona.service;

import com.sacarona.common.svc.exception.BusinessException;
import com.sacarona.model.User;
import com.sacarona.model.mobile.AppUserAuth;

public interface AppUserAuthService {
	AppUserAuth createNew (User user);
	
	AppUserAuth find (AppUserAuth userAuth) throws BusinessException;
}
