package com.sacarona.service;

import com.sacarona.model.UserAvatar;

public interface UserAvatarService {
	UserAvatar findByUserId (Long id);
	void insert (UserAvatar avatar);
	void update (UserAvatar avatar, Long id);
}
