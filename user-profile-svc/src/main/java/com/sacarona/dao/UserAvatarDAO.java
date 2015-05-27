package com.sacarona.dao;

import com.sacarona.model.user.UserAvatar;

public interface UserAvatarDAO extends GenericDAO<UserAvatar> {
	UserAvatar findByUserId (Long id);
}
