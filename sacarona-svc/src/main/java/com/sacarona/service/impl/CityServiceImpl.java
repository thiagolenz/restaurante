package com.sacarona.service.impl;

import java.net.UnknownHostException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sacarona.common.svc.exception.BusinessException;
import com.sacarona.common.svc.io.ServiceCollectionResponse;
import com.sacarona.common.svc.io.ServiceRequest;
import com.sacarona.dao.CityDAO;
import com.sacarona.dao.CountryDAO;
import com.sacarona.model.world.City;
import com.sacarona.model.world.Country;
import com.sacarona.service.CityService;

@Service
public class CityServiceImpl implements CityService {
	@Autowired
	private CityDAO cityDAO;
	
	@Autowired private CountryDAO countryDAO;
	
	@Transactional
	public void insertOrUpdate (City city) throws BusinessException {
		City existent = cityDAO.findByExistent(city);
		if (city.getAlternativeNames() != null && city.getAlternativeNames().length() >= 10000)
			city.setAlternativeNames(city.getAlternativeNames().substring(0, 9998));
		if (existent == null)
			cityDAO.insert(city);
		else 
			cityDAO.update(city, existent.getId());
	}
	
	@Transactional
	public void insertOrUpdate(List<City> cities) throws BusinessException {
		for (City city : cities) {
			City existent = cityDAO.findByExistent(city);
			if (city.getAlternativeNames() != null && city.getAlternativeNames().length() >= 10000)
				city.setAlternativeNames(city.getAlternativeNames().substring(0, 9998));
			if (existent == null)
				cityDAO.insert(city);
			else 
				cityDAO.update(city, existent.getId());
		}
	}

	@Transactional
	public ServiceCollectionResponse<City> search(ServiceRequest<City> request) throws BusinessException {
		try {
			return cityDAO.search(request);
		} catch (UnknownHostException e) {
			throw new BusinessException(e);
		}
	}
	
	@Transactional
	public City findById(Long id, String lang) {
		City cityTemp = cityDAO.findById(City.class, id);
		cityDAO.completeTheName(cityTemp, lang);
		return cityTemp;
	}
	
	@Transactional
	public Country getCountryByCity(City city) {
		return countryDAO.findByIsoCode(city.getCountryIso());
	}
}
