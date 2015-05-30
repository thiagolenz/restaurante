package com.sacarona.dao;

import java.net.UnknownHostException;

import com.sacarona.model.user.UserProfile;

public interface UserProfileDAO extends GenericDAO<UserProfile> {
	UserProfile findByUserId (Long userId) throws UnknownHostException;
}
