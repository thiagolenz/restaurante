package com.sacarona.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sacarona.common.context.RequestContext;
import com.sacarona.common.svc.controller.ServiceRequestFactory;
import com.sacarona.common.svc.exception.BusinessException;
import com.sacarona.common.svc.io.ServiceCollectionResponse;
import com.sacarona.model.track.LocationTrack;
import com.sacarona.service.LocationTrackService;

@Controller
@RequestMapping(value="/locationTrack", produces=MediaType.APPLICATION_JSON_VALUE)
public class LocationTrackController {
	private RequestContext requestContext;
	@Autowired private ServiceRequestFactory<LocationTrack> requestFactory;
	@Autowired private LocationTrackService locationTrackService;
	
	@RequestMapping(value="/", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public LocationTrack create (@RequestBody LocationTrack locationTrack) {
		locationTrack.setUserId(requestContext.getUser().getId());
		return locationTrackService.insert(locationTrack);
	}
	
	@RequestMapping(value="/findByUser/{id}", method = RequestMethod.GET, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ServiceCollectionResponse<LocationTrack> findByUser (@PathVariable("id") Long id) throws BusinessException {
		LocationTrack locationTrack = new LocationTrack();
		locationTrack.setUserId(id);
		return locationTrackService.findByUser(requestFactory.createServiceRequest(locationTrack, requestContext));
	}
}
