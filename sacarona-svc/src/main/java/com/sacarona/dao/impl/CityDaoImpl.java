package com.sacarona.dao.impl;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sacarona.common.svc.io.ServiceCollectionResponse;
import com.sacarona.common.svc.io.ServiceRequest;
import com.sacarona.dao.CityDAO;
import com.sacarona.dao.CountryDAO;
import com.sacarona.dao.ProvinceDAO;
import com.sacarona.model.world.City;
import com.sacarona.model.world.Country;
import com.sacarona.model.world.Province;

@Repository
public class CityDaoImpl extends AbstractJpaDaoImpl<City> implements CityDAO {
	
	@Autowired
	private CountryDAO countryDAO;
	
	@Autowired 
	private ProvinceDAO provinceDAO;
	
	@Override
	public City findByCountryAndCode(String countryIso, String code) throws UnknownHostException {
		TypedQuery<City> query = em.createQuery("from City o where o.countryIso = :countryIso and o.code = :code ", City.class);
		query.setParameter("countryIso", countryIso);
		query.setParameter("code", code);
		return singleQuery(query);
	}
	
	@Override
	public City findByExistent(City city) {
		TypedQuery<City> query = em.createQuery("from City o where o.countryIso = :countryIso and o.name = :name " +
				" and o.provinceAbbreviation = :provinceAbbreviation", City.class);
		query.setParameter("countryIso", city.getCountryIso());
		query.setParameter("name", city.getName());
		query.setParameter("provinceAbbreviation", city.getProvinceAbbreviation());
		return singleQuery(query);
	}

	@Override
	public ServiceCollectionResponse<City> search(ServiceRequest<City> request) throws UnknownHostException {
		
		StringBuilder builder = new StringBuilder("from City o where lower(o.name) like :name");
		City city = request.getEntity();
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("name", city.getName().toLowerCase() + "%");
		if (city.getCountryIso() != null){
			builder.append(" and o.countryIso =:iso and o.provinceAbbreviation = :province");
			parameters.put("iso", city.getCountryIso());
			parameters.put("province", city.getProvinceAbbreviation());
		}
			
		builder.append(" order by o.name");

		TypedQuery<City> query = createQueryAndSetParams(builder, parameters, City.class);
		ServiceCollectionResponse<City> result = executeQueryForPagination(query, request);
		for (City cityTemp : result.getDataList()) {
			completeTheName(cityTemp, request.getUser().getLang());
		}
		return result;
	}
	
	public void completeTheName (City cityTemp, String lang) {
		if (cityTemp != null) {
			cityTemp.setCompleteName(cityTemp.getName() + " "+ cityTemp.getProvinceAbbreviation());
			
			Country country = countryDAO.findByIsoCode(cityTemp.getCountryIso());
			cityTemp.setCountry(country);
			if (country != null) {
				cityTemp.setCompleteName(cityTemp.getCompleteName() + ", " + country.getNameByLang(lang));
				Province province = provinceDAO
						.findByAbbreviationAndCountryIso(cityTemp.getProvinceAbbreviation(), cityTemp.getCountryIso());
				cityTemp.setProvince(province);
			}
		}
	}
}
