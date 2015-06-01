package com.sacarona.service.impl;

import java.net.UnknownHostException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sacarona.common.svc.exception.BusinessException;
import com.sacarona.common.svc.io.ServiceCollectionResponse;
import com.sacarona.common.svc.io.ServiceRequest;
import com.sacarona.dao.TravelDAO;
import com.sacarona.model.travel.Travel;
import com.sacarona.service.TravelService;

@Service
public class TravelServiceImpl implements TravelService {
	@Autowired private TravelDAO travelDAO;

	@Override
	public Travel insert(Travel travel) {
		travel.setCreateDate(new Date());
		return travelDAO.insert(travel);
	}

	@Override
	public Travel update(Travel travel, Long id) {
		travelDAO.update(travel, id);
		return travel;
	}

	@Override
	public ServiceCollectionResponse<Travel> findByUser(ServiceRequest<Travel> request) throws BusinessException {
		try {
			return travelDAO.findByUser(request);
		} catch (UnknownHostException e) {
			throw new BusinessException(e);
		}
	}

}
