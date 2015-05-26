package com.sacarona.dao.impl;

import java.net.UnknownHostException;

import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.sacarona.dao.AppUserAuthDAO;
import com.sacarona.model.mobile.AppUserAuth;

@Repository
public class AppUserAuthDaoImpl extends AbstractDaoImpl<AppUserAuth> implements AppUserAuthDAO {
	
	@Override
	public AppUserAuth find(AppUserAuth userAuth) throws UnknownHostException {
		BasicDBObject query = new BasicDBObject();
		query.append("appToken", userAuth.getAppToken());
		query.append("userToken", userAuth.getUserToken());
		DBCursor result = getCollection().find(query);
		
		if (result.hasNext()) {
			DBObject next = result.next();
			return mapResult((BasicDBObject) next);
		}
		return null;
	}

	protected String getSequenceName() {
		return "seq_app_user_auth";
	}

	protected String getCollectionName() {
		return "app_user_auth";
	}

	protected void mapObject(AppUserAuth auth, BasicDBObject destiny) {
		destiny.append("id", auth.getId());
		destiny.append("appToken", auth.getAppToken());
		destiny.append("userToken", auth.getUserToken());
		destiny.append("userId", auth.getUserId());
	}

	protected AppUserAuth mapResult(BasicDBObject objectDestiny) {
		AppUserAuth result = new AppUserAuth();
		result.setAppToken(objectDestiny.getString("appToken"));
		result.setUserToken(objectDestiny.getString("userToken"));
		result.setId(objectDestiny.getLong("id"));
		result.setUserId(objectDestiny.getLong("userId"));
		return result;
	}
}
