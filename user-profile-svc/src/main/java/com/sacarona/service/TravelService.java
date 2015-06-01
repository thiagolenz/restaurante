package com.sacarona.service;

import com.sacarona.common.svc.exception.BusinessException;
import com.sacarona.common.svc.io.ServiceCollectionResponse;
import com.sacarona.common.svc.io.ServiceRequest;
import com.sacarona.model.travel.Travel;

public interface TravelService {
	Travel insert (Travel travel);
	Travel update (Travel travel, Long id);
	ServiceCollectionResponse<Travel> findByUser (ServiceRequest<Travel> request) throws BusinessException;
}
