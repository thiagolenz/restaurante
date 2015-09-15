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
import com.sacarona.common.response.Response;
import com.sacarona.common.svc.controller.ServiceRequestFactory;
import com.sacarona.common.svc.exception.BusinessException;
import com.sacarona.common.svc.io.ServiceCollectionResponse;
import com.sacarona.common.svc.io.ServiceRequest;
import com.sacarona.model.world.Province;
import com.sacarona.service.ProvinceService;

@Controller
@RequestMapping(value="/province",produces=MediaType.APPLICATION_JSON_VALUE)
public class ProvinceController {
	private RequestContext requestContext;

	@Autowired
	private ServiceRequestFactory<Province> requestFactory;

	@Autowired
	private ProvinceService provinceService;

	@RequestMapping(value="/search", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ServiceCollectionResponse<Province> search (@RequestBody Province province) throws BusinessException {
		ServiceRequest<Province> request = requestFactory.createServiceRequest(province, requestContext);
		return provinceService.search(request);
	}
	
	@RequestMapping(value="/importAll", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Response importAll (@RequestBody List<Province> provinces) throws BusinessException {
		provinceService.insertOrUpdate(provinces);		
		return Response.newSuccessResponse();
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Province findById (@PathVariable Long id) {
		return provinceService.findById(id);
	}
}
