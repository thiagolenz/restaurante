package com.sacarona.dao.impl;

import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.sacarona.dao.UserAvatarDAO;
import com.sacarona.model.user.UserAvatar;

@Repository
public class UserAvatarDaoImpl extends AbstractDaoImpl<UserAvatar> implements
		UserAvatarDAO {

	@Override
	public UserAvatar findByUserId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getSequenceName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getCollectionName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void mapObject(UserAvatar object, BasicDBObject objectDestiny) {
		// TODO Auto-generated method stub

	}

	@Override
	protected UserAvatar mapResult(BasicDBObject objectDestiny) {
		// TODO Auto-generated method stub
		return null;
	}

}
