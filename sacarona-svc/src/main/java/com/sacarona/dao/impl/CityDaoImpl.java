package com.sacarona.dao.impl;

import java.net.UnknownHostException;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.sacarona.common.svc.io.ServiceCollectionResponse;
import com.sacarona.common.svc.io.ServiceRequest;
import com.sacarona.dao.CityDAO;
import com.sacarona.dao.CountryDAO;
import com.sacarona.model.world.City;
import com.sacarona.model.world.Country;

@Repository
public class CityDaoImpl extends AbstractDaoImpl<City> implements CityDAO {
	
	@Autowired
	private CountryDAO countryDAO;

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
		return singleQuery(query);
	}

	@Override
	public ServiceCollectionResponse<City> search(ServiceRequest<City> request) throws UnknownHostException {
		BasicDBObject query = new BasicDBObject();
		City city = request.getEntity();
		Pattern queryName = Pattern.compile("^" +city.getName());
		query.put("name", queryName);
		BasicDBObject orderBy = new BasicDBObject("name", 1);
		ServiceCollectionResponse<City> result = executeQueryPatination(request, query, orderBy);
		for (City cityTemp : result.getDataList()) {
			cityTemp.setCompleteName(cityTemp.getName() + " "+ cityTemp.getProvinceAbbreviation());
			Country country = countryDAO.findByIsoCode(cityTemp.getCountryIso());
			if (country != null)
				cityTemp.setCompleteName(cityTemp.getCompleteName() + ", " + country.getNameByLang(request.getUser().getLang()));
		}
		return result;
	}
}
