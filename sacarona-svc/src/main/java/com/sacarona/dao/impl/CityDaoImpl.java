package com.sacarona.dao.impl;

import java.net.UnknownHostException;

import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sacarona.common.svc.io.ServiceCollectionResponse;
import com.sacarona.common.svc.io.ServiceRequest;
import com.sacarona.dao.CityDAO;
import com.sacarona.dao.CountryDAO;
import com.sacarona.model.world.City;
import com.sacarona.model.world.Country;

@Repository
public class CityDaoImpl extends AbstractJpaDaoImpl<City> implements CityDAO {
	
	@Autowired
	private CountryDAO countryDAO;
	
	@Override
	public City findByCountryAndCode(String countryIso, String code) throws UnknownHostException {
		TypedQuery<City> query = em.createQuery("from City o where o.countryIso = :countryIso and o.code = :code ", City.class);
		query.setParameter("countryIso", countryIso);
		query.setParameter("code", code);
		return singleQuery(query);
	}

	@Override
	public ServiceCollectionResponse<City> search(ServiceRequest<City> request) throws UnknownHostException {
		TypedQuery<City> query = em.createQuery("from City o where o.name like :name", City.class);
		query.setParameter("name", request.getEntity().getName() + "%");

		ServiceCollectionResponse<City> result = executeQueryForPagination(query, request);
		for (City cityTemp : result.getDataList()) {
			cityTemp.setCompleteName(cityTemp.getName() + " "+ cityTemp.getProvinceAbbreviation());
			Country country = countryDAO.findByIsoCode(cityTemp.getCountryIso());
			if (country != null)
				cityTemp.setCompleteName(cityTemp.getCompleteName() + ", " + country.getNameByLang(request.getUser().getLang()));
		}
		return result;
	}
}
