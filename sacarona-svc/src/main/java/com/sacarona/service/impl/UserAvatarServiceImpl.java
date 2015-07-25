package com.sacarona.service.impl;

import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sacarona.common.svc.exception.BusinessException;
import com.sacarona.dao.UserAvatarDAO;
import com.sacarona.model.user.UserAvatar;
import com.sacarona.service.UserAvatarService;

@Service
public class UserAvatarServiceImpl implements UserAvatarService {
	@Autowired
	private UserAvatarDAO avatarDAO;

	@Transactional
	public UserAvatar findByUserId(Long id) throws BusinessException {
		try {
			return avatarDAO.findByUserId(id);
		} catch (UnknownHostException e) {
			throw new BusinessException(e);
		}
	}

	@Transactional
	public void insert(UserAvatar avatar) {
		avatarDAO.insert(avatar);
	}

	@Transactional
	public void update(UserAvatar avatar, Long id) {
		avatarDAO.update(avatar, id);
	}
}
