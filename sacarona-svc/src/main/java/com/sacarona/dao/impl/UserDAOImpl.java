package com.sacarona.dao.impl;

import java.net.UnknownHostException;

import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.sacarona.dao.UserDAO;
import com.sacarona.model.user.User;

@Repository
public class UserDAOImpl extends AbstractDaoImpl <User> implements UserDAO {

	@Override
	protected String getSequenceName() {
		return "seq_user";
	}

	@Override
	protected String getCollectionName() {
		return "users";
	}

	@Override
	protected void mapObject (User user, BasicDBObject objectDestiny) {
		objectDestiny.append("id", user.getId());
		objectDestiny.append("name", user.getName());
		objectDestiny.append("lang", user.getLang());
		objectDestiny.append("email", user.getEmail());
		objectDestiny.append("socialMediaId", user.getSocialMediaId());
	}
	
	@Override
	public User findBySocialMediaAndEmail (User user) throws UnknownHostException {
		BasicDBObject query = new BasicDBObject();
		query.append("socialMediaId", user.getSocialMediaId());
		query.append("email", user.getEmail());
		return singleQuery(query);
	}

	@Override
	protected User mapResult (BasicDBObject objectDestiny) {
		User user = new User ();
		user.setId(objectDestiny.getLong("id"));
		user.setEmail(objectDestiny.getString("email"));
		user.setName(objectDestiny.getString("name"));
		user.setSocialMediaId(objectDestiny.getString("socialMediaId"));
		user.setLang(objectDestiny.getString("lang"));
		return user;
	}	
}
