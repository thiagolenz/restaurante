package com.sacarona.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.sacarona.common.svc.io.ServiceRequest;
import com.sacarona.model.world.City;
import com.sacarona.service.CityService;

@Controller
@RequestMapping(value="/city",produces=MediaType.APPLICATION_JSON_VALUE)
public class CityController {
	
	private RequestContext requestContext;
	
	@Autowired
	private ServiceRequestFactory<City> requestFactory;
	
	@Autowired
	private CityService cityService;
	
	@RequestMapping(value="/search", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ServiceCollectionResponse<City> search (@RequestBody City city) throws BusinessException {
		ServiceRequest<City> request = requestFactory.createServiceRequest(city, requestContext);
		return cityService.search(request);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	@ResponseBody
	public City findById (@PathVariable Long id) {
		return cityService.findById(id);
	}
	
	@RequestMapping(value="/insert", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public City insert (@RequestBody City city) throws BusinessException {
		List<City> list = new ArrayList<>();
		list.add(city);
		for (City city2 : list) {
			cityService.insertOrUpdate(city2);
		}
		return city;
	}
	

}
