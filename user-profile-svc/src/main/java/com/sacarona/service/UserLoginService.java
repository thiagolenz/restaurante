package com.sacarona.service;

import com.sacarona.common.svc.exception.LoginFailedException;
import com.sacarona.model.User;

public interface UserLoginService {
	User login (User user) throws LoginFailedException;
	User socialMediaLogin (User user, String appToken) throws LoginFailedException;
}
