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
import com.sacarona.model.feedback.Feedback;
import com.sacarona.model.feedback.FeedbackAverage;
import com.sacarona.service.FeedbackService;

@Controller
@RequestMapping(value="/feedback", produces=MediaType.APPLICATION_JSON_VALUE)
public class FeedbackController {

	private RequestContext requestContext;
	@Autowired private ServiceRequestFactory<Feedback> requestFactory;
	@Autowired private FeedbackService feedbackService;
	
	@RequestMapping(value="/", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Feedback create (@RequestBody Feedback feedback) throws BusinessException {
		feedback.setUserGaveId(requestContext.getUser().getId());
		return feedbackService.insert(feedback);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Feedback update (@RequestBody Feedback feedback, @PathVariable ("id") Long id) throws BusinessException {
		feedback.setUserGaveId(requestContext.getUser().getId());
		return feedbackService.update(feedback, id);
	}
	
	@RequestMapping(value="/findByUser/{id}", method = RequestMethod.GET, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ServiceCollectionResponse<Feedback> findByUser (@PathVariable("id") Long id) throws BusinessException {
		Feedback feedback = new Feedback();
		feedback.setUserReceivedId(id);
		return feedbackService.findByUser(requestFactory.createServiceRequest(feedback, requestContext));
	}
	
	@RequestMapping(value="/findAverage/{id}", method = RequestMethod.GET, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public FeedbackAverage findAverage (@PathVariable ("id") Long id) throws BusinessException {
		FeedbackAverage feedback = feedbackService.findAverageByUser(id);
		if (feedback == null)
			feedback = new FeedbackAverage();
		return feedback;
	}
}
