package com.sacarona.dao.impl;

import java.net.UnknownHostException;
import java.util.regex.Pattern;

import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.sacarona.common.svc.io.ServiceCollectionResponse;
import com.sacarona.common.svc.io.ServiceRequest;
import com.sacarona.dao.CountryDAO;
import com.sacarona.model.world.Country;

@Repository
public class CountryDaoImpl extends AbstractDaoImpl<Country> implements CountryDAO {

	@Override
	protected String getSequenceName() {
		return "seq_country";
	}

	@Override
	protected String getCollectionName() {
		return "country";
	}

	@Override
	protected void mapObject(Country source, BasicDBObject destiny) {
		destiny.append("id", source.getId());
		destiny.append("iso", source.getIso());
		destiny.append("nameEnglish", source.getNameEnglish());
		destiny.append("namePortuguese", source.getNamePortuguese());
		destiny.append("nameSpanish", source.getNameSpanish());
		destiny.append("un", source.getUn());
		destiny.append("externalId", source.getExternalId());
	}

	@Override
	protected Country mapResult(BasicDBObject destiny) {
		Country country = new Country();
		country.setId(destiny.getLong("id"));
		country.setIso(destiny.getString("iso"));
		country.setNameEnglish(destiny.getString("nameEnglish"));
		country.setNamePortuguese(destiny.getString("namePortuguese"));
		country.setNameSpanish(destiny.getString("nameSpanish"));
		country.setUn(destiny.getString("un"));
		country.setExternalId(destiny.getLong("externalId"));
		return country;
	}

	@Override
	public Country findByExternalId (Long id) throws UnknownHostException {
		BasicDBObject query = new BasicDBObject();
		query.append("externalId", id);
		DBCursor result = getCollection().find(query);
		
		if (result.hasNext()) {
			DBObject next = result.next();
			Country country = mapResult((BasicDBObject) next);
			result.close();
			return country;
		}
		return null;
	}
	
	@Override
	public ServiceCollectionResponse<Country> search(ServiceRequest<Country> request) throws UnknownHostException {
		BasicDBObject query = new BasicDBObject();
		Country country = request.getEntity();
		Pattern queryName = Pattern.compile("^" +country.getNameEnglish());
		addNameQueryParam(request, query, queryName);
		return executeQueryPatination(request, query);
	}

	private void addNameQueryParam(ServiceRequest<Country> request,
			BasicDBObject query, Pattern queryName) {
		String lang = request.getUser().getLang(); 
		if (lang == null)
			lang = "en-US";
		if (lang.equals("en-US"))
			query.put("nameEnglish", queryName);
		else if (lang.equals("es"))
			query.put("nameSpanish", queryName);
		else 
			query.put("namePortuguese", queryName);
	}
	
	@Override
	public Country findByIsoCode(String iso) throws UnknownHostException {
		BasicDBObject query = new BasicDBObject();
		query.append("iso", iso);
		DBCursor result = getCollection().find(query);
		
		if (result.hasNext()) {
			DBObject next = result.next();
			Country country = mapResult((BasicDBObject) next);
			result.close();
			return country;
		}
		return null;
	}

}
