package com.sacarona.dao;

import com.sacarona.model.user.UserProfile;

public interface UserProfileDAO extends GenericDAO<UserProfile> {
	UserProfile findByUserId (Long userId);
}
