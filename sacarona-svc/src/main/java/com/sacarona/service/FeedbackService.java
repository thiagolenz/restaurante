package com.sacarona.service;

import com.sacarona.common.svc.exception.BusinessException;
import com.sacarona.common.svc.io.ServiceCollectionResponse;
import com.sacarona.common.svc.io.ServiceRequest;
import com.sacarona.model.feedback.Feedback;
import com.sacarona.model.feedback.FeedbackAverage;

public interface FeedbackService {
	Feedback insert (Feedback feedback) throws BusinessException;
	
	Feedback update (Feedback feedback, Long id) throws BusinessException;
	
	ServiceCollectionResponse<Feedback> findByUser(ServiceRequest<Feedback> request) throws BusinessException;
	
	FeedbackAverage findAverageByUser (Long userId) throws BusinessException;
}
