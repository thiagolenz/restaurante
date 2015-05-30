package com.sacarona.service;

import com.sacarona.common.svc.exception.BusinessException;
import com.sacarona.model.user.UserAvatar;

public interface UserAvatarService {
	UserAvatar findByUserId (Long id) throws BusinessException;
	void insert (UserAvatar avatar);
	void update (UserAvatar avatar, Long id);
}
