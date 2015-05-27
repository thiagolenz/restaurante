package com.sacarona.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sacarona.dao.UserAvatarDAO;
import com.sacarona.model.user.UserAvatar;
import com.sacarona.service.UserAvatarService;

@Service
public class UserAvatarServiceImpl implements UserAvatarService {
	@Autowired
	private UserAvatarDAO avatarDAO;

	public UserAvatar findByUserId(Long id) {
		return avatarDAO.findByUserId(id);
	}

	public void insert(UserAvatar avatar) {
		avatarDAO.insert(avatar);
	}

	public void update(UserAvatar avatar, Long id) {
		avatarDAO.update(avatar, id);
	}
}
