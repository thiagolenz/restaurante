package com.sacarona.service;

import java.util.List;

import com.sacarona.common.svc.exception.BusinessException;
import com.sacarona.common.svc.io.ServiceCollectionResponse;
import com.sacarona.common.svc.io.ServiceRequest;
import com.sacarona.model.world.Country;

public interface CountryService {
	void insertOrUpdate(List<Country> list) throws BusinessException;

	ServiceCollectionResponse<Country> search (ServiceRequest<Country> request) throws BusinessException;

	Country findById(Long id);
}
