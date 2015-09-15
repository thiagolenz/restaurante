package com.sacarona.service;

import java.io.File;

import com.sacarona.common.svc.exception.BusinessException;
import com.sacarona.model.user.User;
import com.sacarona.model.user.UserAvatar;

public interface UserAvatarService {
	UserAvatar findByUserId (Long id) throws BusinessException;
	UserAvatar save (User user, File file);
}
