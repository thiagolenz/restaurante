package com.sacarona.service;

import com.sacarona.common.svc.exception.LoginFailedException;
import com.sacarona.model.user.User;

public interface UserLoginService {
	User login (User user) throws LoginFailedException;
	User socialMediaLogin (User user, String appToken) throws LoginFailedException;
}
