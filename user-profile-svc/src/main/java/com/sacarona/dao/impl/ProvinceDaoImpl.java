package com.sacarona.dao.impl;

import java.net.UnknownHostException;
import java.util.regex.Pattern;

import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.sacarona.common.svc.io.ServiceCollectionResponse;
import com.sacarona.common.svc.io.ServiceRequest;
import com.sacarona.dao.ProvinceDAO;
import com.sacarona.model.world.Province;

@Repository
public class ProvinceDaoImpl extends AbstractDaoImpl<Province> implements ProvinceDAO {

	@Override
	protected String getSequenceName() {
		return "seq_province";
	}

	@Override
	protected String getCollectionName() {
		return "province";
	}

	@Override
	protected void mapObject(Province source, BasicDBObject destiny) {
		destiny.append("id", source.getId());
		destiny.append("countryId", source.getCountryId());
		destiny.append("name", source.getName());
		destiny.append("abbreviation", source.getAbbreviation());
	}

	@Override
	protected Province mapResult(BasicDBObject objectDestiny) {
		Province province = new Province();
		province.setId(objectDestiny.getLong("id"));
		province.setCountryId(objectDestiny.getLong("countryId"));
		province.setName(objectDestiny.getString("name"));
		province.setAbbreviation(objectDestiny.getString("abbreviation"));
		return province;
	}
	
	@Override
	public Province findByAbbreviationAndCountry(String abbreviation, Long id) throws UnknownHostException {
		BasicDBObject query = new BasicDBObject();
		query.append("countryId", id);
		query.append("abbreviation", abbreviation);
		return singleQuery(query);
	}

	@Override
	public ServiceCollectionResponse<Province> search(ServiceRequest<Province> request) throws UnknownHostException {
		BasicDBObject query = new BasicDBObject();
		Province province = request.getEntity();
		Pattern queryName = Pattern.compile("^" +province.getName());
		query.append("name", queryName);
		query.append("countryId", province.getCountryId());
		return executeQueryPatination(request, query);
	}
}
