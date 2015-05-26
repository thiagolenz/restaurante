package com.sacarona.dao;

import com.sacarona.model.UserAvatar;

public interface UserAvatarDAO extends GenericDAO<UserAvatar> {
	UserAvatar findByUserId (Long id);
}
