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
import com.sacarona.common.response.Response;
import com.sacarona.common.svc.controller.ServiceRequestFactory;
import com.sacarona.common.svc.exception.BusinessException;
import com.sacarona.common.svc.io.ServiceCollectionResponse;
import com.sacarona.model.dealing.Dealing;
import com.sacarona.model.user.User;
import com.sacarona.service.DealingService;

@Controller
@RequestMapping(value="/dealing", produces=MediaType.APPLICATION_JSON_VALUE)
public class DealingController {
	
	private RequestContext requestContext;
	@Autowired private ServiceRequestFactory<Dealing> requestFactory;
	@Autowired private DealingService dealingService;
	
	@RequestMapping(value="/", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Dealing create (@RequestBody Dealing dealing) throws BusinessException {
		return dealingService.insert(dealing);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Dealing update (@RequestBody Dealing dealing, @PathVariable("id") Long id) throws BusinessException {
		return dealingService.update(dealing, id);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Response update (@PathVariable("id") Long id) {
		dealingService.remove(id);
		return Response.newSuccessResponse();
	}
	
	@RequestMapping(value="/findByUser/{id}", method = RequestMethod.GET, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ServiceCollectionResponse<Dealing> findByUser (@PathVariable("id") Long id) throws BusinessException {
		Dealing dealing = new Dealing();
		dealing.setTravelerUser(new User(id));
		return dealingService.findByUser(requestFactory.createServiceRequest(dealing, requestContext));
	}
}
