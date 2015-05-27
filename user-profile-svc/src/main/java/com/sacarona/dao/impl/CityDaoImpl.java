package com.sacarona.dao.impl;

import java.net.UnknownHostException;

import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.sacarona.dao.CityDAO;
import com.sacarona.model.world.City;

@Repository
public class CityDaoImpl extends AbstractDaoImpl<City> implements CityDAO {

	@Override
	protected String getSequenceName() {
		return "seq_city";
	}

	@Override
	protected String getCollectionName() {
		return "city";
	}

	@Override
	protected void mapObject(City source, BasicDBObject destiny) {
		destiny.append("id", source.getId());
		destiny.append("name", source.getName());
		destiny.append("code", source.getCode());
		destiny.append("countryIso", source.getCountryIso());
		destiny.append("provinceAbbreviation", source.getProvinceAbbreviation());
	}

	@Override
	protected City mapResult(BasicDBObject objectDestiny) {
		City city = new City();
		city.setId(objectDestiny.getLong("id"));
		city.setCode(objectDestiny.getString("code"));
		city.setName(objectDestiny.getString("name"));
		city.setCountryIso(objectDestiny.getString("countryIso"));		
		city.setProvinceAbbreviation(objectDestiny.getString("provinceAbbreviation"));
		return city;
	}
	
	@Override
	public City findByStateAndCode(String countryIso, String code) throws UnknownHostException {
		BasicDBObject query = new BasicDBObject();
		query.append("countryIso", countryIso);
		query.append("code", code);
		DBCursor result = getCollection().find(query);
		
		if (result.hasNext()) {
			DBObject next = result.next();
			City city = mapResult((BasicDBObject) next);
			result.close();
			return city;
		}
		return null;
	}
}
