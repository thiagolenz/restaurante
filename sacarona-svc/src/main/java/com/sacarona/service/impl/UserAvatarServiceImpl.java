package com.sacarona.service.impl;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sacarona.dao.UserAvatarDAO;
import com.sacarona.model.user.User;
import com.sacarona.model.user.UserAvatar;
import com.sacarona.service.FileStorageService;
import com.sacarona.service.UserAvatarService;

@Service
public class UserAvatarServiceImpl implements UserAvatarService {
	@Autowired
	private UserAvatarDAO avatarDAO;
	
	@Autowired private CredentialsFactory credentialsFactory;
	@Autowired private FileStorageService fileStorageService;

	@Transactional
	public UserAvatar save(User user, File file) {
		UserAvatar avatar = findByUserId(user.getId());
		if (avatar == null) {
			avatar = new UserAvatar();
			avatar.setUserId(user.getId());
		}
		String pathNameUser = "user_images/" + user.getId() + "/profile";
		String pathName = fileStorageService.upload(file, pathNameUser, credentialsFactory.getCredentials());
		avatar.setAvatarBase64(pathName);
		if (avatar.getId() == null)
			avatarDAO.insert(avatar);
		else 
			avatarDAO.update(avatar, avatar.getId());
		return avatar;
	}
	
	@Transactional
	public UserAvatar findByUserId(Long id) {
		return avatarDAO.findByUserId(id);
	}
}
