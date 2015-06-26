package com.sacarona.service;

import com.sacarona.common.svc.exception.BusinessException;
import com.sacarona.common.svc.io.ServiceCollectionResponse;
import com.sacarona.common.svc.io.ServiceRequest;
import com.sacarona.model.world.City;

public interface CityService {
	void insertOrUpdate (City city) throws BusinessException;
	ServiceCollectionResponse<City> search (ServiceRequest<City> request) throws BusinessException;
	
	City findById (Long id);
}
