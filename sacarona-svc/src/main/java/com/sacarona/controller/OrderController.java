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
import com.sacarona.controller.request.SearchLocationType;
import com.sacarona.controller.request.SearchOrdersRequest;
import com.sacarona.model.order.Order;
import com.sacarona.service.OrderService;

@Controller
@RequestMapping(value="/order", produces=MediaType.APPLICATION_JSON_VALUE)
public class OrderController {
	
	private RequestContext requestContext;
	@Autowired private ServiceRequestFactory<Order> requestFactory;
	@Autowired private OrderService orderService;
	
	@RequestMapping(value="/", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Order create (@RequestBody Order order) {
		order.setUserId(requestContext.getUser().getId());
		return orderService.insert(order);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Order update (@RequestBody Order order, @PathVariable("id") Long id) {
		order.setUserId(requestContext.getUser().getId());
		return orderService.update(order, id);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Response update (@PathVariable("id") Long id) {
		orderService.remove(id);
		return Response.newSuccessResponse();
	}
	
	@RequestMapping(value="/findByUser/{id}", method = RequestMethod.GET, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ServiceCollectionResponse<Order> findByUser (@PathVariable("id") Long id) throws BusinessException {
		Order order = new Order();
		order.setUserId(id);
		return orderService.findOrdersByUser(requestFactory.createServiceRequest(order, requestContext));
	}
	
	@RequestMapping(value="/findOrders/{locationType}", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ServiceCollectionResponse<Order> findOrders (@RequestBody Order order, @PathVariable ("locationType") String locationType) throws BusinessException {
		SearchOrdersRequest request = 
				new SearchOrdersRequest(requestFactory.createServiceRequest(order, requestContext), 
				SearchLocationType.valueOf(locationType.toUpperCase()));
		return orderService.findOrders(request);
	}
}
