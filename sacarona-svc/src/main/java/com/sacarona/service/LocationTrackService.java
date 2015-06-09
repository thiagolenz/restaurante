package com.sacarona.service;

import com.sacarona.common.svc.exception.BusinessException;
import com.sacarona.common.svc.io.ServiceCollectionResponse;
import com.sacarona.common.svc.io.ServiceRequest;
import com.sacarona.model.track.LocationTrack;

public interface LocationTrackService {
	LocationTrack insert (LocationTrack locationTrack);
	
	ServiceCollectionResponse<LocationTrack> findByUser (ServiceRequest<LocationTrack> request) throws BusinessException;
}
