package com.sacarona.dao.impl;

import java.net.UnknownHostException;

import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.sacarona.dao.UserAvatarDAO;
import com.sacarona.model.user.UserAvatar;

@Repository
public class UserAvatarDaoImpl extends AbstractDaoImpl<UserAvatar> implements
		UserAvatarDAO {

	@Override
	public UserAvatar findByUserId (Long id) throws UnknownHostException {
		BasicDBObject query = new BasicDBObject();
		query.append("userId", id);
		return singleQuery(query);
	}

	@Override
	protected String getSequenceName() {
		return "seq_user_avatar";
	}

	@Override
	protected String getCollectionName() {
		return "user_avatar";
	}

	@Override
	protected void mapObject(UserAvatar avatar, BasicDBObject objectDestiny) {
		objectDestiny.append("id", avatar.getId());
		objectDestiny.append("userId", avatar.getUserId());
		objectDestiny.append("avatarBase64", avatar.getAvatarBase64());
	}

	@Override
	protected UserAvatar mapResult(BasicDBObject objectDestiny) {
		UserAvatar avatar = new UserAvatar();
		avatar.setId(objectDestiny.getLong("id"));
		avatar.setUserId(objectDestiny.getLong("userId"));
		avatar.setAvatarBase64(objectDestiny.getString("avatarBase64"));
		return avatar;
	}

}
