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
import com.sacarona.controller.request.SearchLocationType;
import com.sacarona.controller.request.SearchTravelersRequest;
import com.sacarona.model.travel.Travel;
import com.sacarona.service.TravelService;

@Controller
@RequestMapping(value="/travel", produces=MediaType.APPLICATION_JSON_VALUE)
public class TravelController {

	private RequestContext requestContext;
	@Autowired private ServiceRequestFactory<Travel> requestFactory;
	@Autowired private TravelService travelService;

	@RequestMapping(value="/", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Travel create (@RequestBody Travel travel) {
		travel.setUserId(requestContext.getUser().getId());
		return travelService.insert(travel);
	}

	@RequestMapping(value="/{id}", method = RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Travel update (@RequestBody Travel travel, @PathVariable("id") Long id) {
		travel.setUserId(requestContext.getUser().getId());
		return travelService.update(travel, id);
	}

	@RequestMapping(value="/findByUser/{id}", method = RequestMethod.GET, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ServiceCollectionResponse<Travel> findByUser (@PathVariable("id") Long id) throws BusinessException {
		Travel travel = new Travel();
		travel.setUserId(id);
		return travelService.findByUser(requestFactory.createServiceRequest(travel, requestContext));
	}

	@RequestMapping(value="/findTravelers/{locationType}", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ServiceCollectionResponse<Travel> findTravelers (@RequestBody Travel travel, @PathVariable String locationType) throws BusinessException {
		SearchTravelersRequest request = 
				new SearchTravelersRequest(requestFactory.createServiceRequest(travel, requestContext), 
				SearchLocationType.valueOf(locationType.toUpperCase()));
		return travelService.findTravelers(request);
	}
}
