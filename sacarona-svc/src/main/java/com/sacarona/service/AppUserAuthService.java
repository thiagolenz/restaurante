package com.sacarona.service;

import com.sacarona.common.svc.exception.BusinessException;
import com.sacarona.model.mobile.AppUserAuth;
import com.sacarona.model.user.User;

public interface AppUserAuthService {
	AppUserAuth createNew (User user);
	
	AppUserAuth find (AppUserAuth userAuth) throws BusinessException;
}
