package com.sacarona.service;

import java.util.List;

import com.sacarona.common.svc.exception.BusinessException;
import com.sacarona.common.svc.io.ServiceCollectionResponse;
import com.sacarona.common.svc.io.ServiceRequest;
import com.sacarona.model.world.City;
import com.sacarona.model.world.Country;

public interface CityService {
	void insertOrUpdate (City city) throws BusinessException;
	void insertOrUpdate (List<City> cities) throws BusinessException;
	
	ServiceCollectionResponse<City> search (ServiceRequest<City> request) throws BusinessException;
	
	City findById (Long id, String lang);
	
	Country getCountryByCity (City city);
}
