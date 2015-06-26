package com.sacarona.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sacarona.common.context.RequestContext;
import com.sacarona.common.svc.controller.ServiceRequestFactory;
import com.sacarona.common.svc.exception.BusinessException;
import com.sacarona.common.svc.io.ServiceCollectionResponse;
import com.sacarona.common.svc.io.ServiceRequest;
import com.sacarona.model.world.Location;
import com.sacarona.service.LocationService;

@Controller
@RequestMapping(value="/location",produces=MediaType.APPLICATION_JSON_VALUE)
public class LocationController {
	
	private RequestContext requestContext;
	
	@Autowired
	private ServiceRequestFactory<Location> requestFactory;
	
	@Autowired
	private LocationService locationService;
	
	@RequestMapping(value="/search", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ServiceCollectionResponse<Location> search (@RequestBody Location location) throws BusinessException {
		ServiceRequest<Location> request = requestFactory.createServiceRequest(location, requestContext);
		return locationService.search(request);
	}
}
