package com.sacarona.dao;

import java.net.UnknownHostException;

import com.sacarona.common.svc.io.ServiceCollectionResponse;
import com.sacarona.common.svc.io.ServiceRequest;
import com.sacarona.model.track.LocationTrack;

public interface LocationTrackDAO extends GenericDAO<LocationTrack> {
	ServiceCollectionResponse<LocationTrack> findByUser (ServiceRequest<LocationTrack> request) throws UnknownHostException;
}
