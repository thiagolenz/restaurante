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
import com.sacarona.model.dealing.Receipt;
import com.sacarona.service.ReceiptService;

@Controller
@RequestMapping(value="/receipt", produces=MediaType.APPLICATION_JSON_VALUE)
public class ReceiptController {
	private RequestContext requestContext;
	
	@Autowired private ReceiptService receiptService;
	@Autowired private ServiceRequestFactory<Receipt> requestFactory;
	
	@RequestMapping(value="/", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Receipt create (@RequestBody Receipt receipt) throws BusinessException {
		receipt.setUserId(requestContext.getUser().getId());
		return receiptService.insert(receipt);
	}
	

	@RequestMapping(value="/retrieve", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ServiceCollectionResponse<Receipt> findByUser (@RequestBody Receipt receipt) throws BusinessException {
		ServiceRequest<Receipt> request = requestFactory.createServiceRequest(receipt, requestContext);
		return receiptService.findByUser(request);
	}
}
