package com.sacarona.service.impl;

import java.net.UnknownHostException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sacarona.common.svc.exception.BusinessException;
import com.sacarona.common.svc.io.ServiceCollectionResponse;
import com.sacarona.common.svc.io.ServiceRequest;
import com.sacarona.dao.LocationTrackDAO;
import com.sacarona.model.track.LocationTrack;
import com.sacarona.service.LocationTrackService;

@Service
public class LocationTrackServiceImpl implements LocationTrackService {
	@Autowired private LocationTrackDAO locationTrackDAO;
	
	public LocationTrack insert(LocationTrack locationTrack) {
		locationTrack.setTrackDate(new Date());
		return locationTrackDAO.insert(locationTrack);
	}

	@Override
	public ServiceCollectionResponse<LocationTrack> findByUser(ServiceRequest<LocationTrack> request) throws BusinessException {
		try {
			return locationTrackDAO.findByUser(request);
		} catch (UnknownHostException e) {
			throw new BusinessException(e);
		}
	}

}
