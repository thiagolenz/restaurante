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
import com.sacarona.model.world.Country;
import com.sacarona.service.CountryService;

@Controller
@RequestMapping(value="/country",produces=MediaType.APPLICATION_JSON_VALUE)
public class CountryController {
	
	private RequestContext requestContext;
	
	@Autowired
	private ServiceRequestFactory<Country> requestFactory;
	
	@Autowired
	private CountryService countryService;
	
	@RequestMapping(value="/search", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ServiceCollectionResponse<Country> search (@RequestBody Country country) throws BusinessException {
		ServiceRequest<Country> request = requestFactory.createServiceRequest(country, requestContext);
		return countryService.search(request);
	}
}
