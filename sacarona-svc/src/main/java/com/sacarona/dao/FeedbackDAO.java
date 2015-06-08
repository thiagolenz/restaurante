package com.sacarona.dao;

import java.net.UnknownHostException;

import com.sacarona.common.svc.io.ServiceCollectionResponse;
import com.sacarona.common.svc.io.ServiceRequest;
import com.sacarona.model.feedback.Feedback;

public interface FeedbackDAO extends GenericDAO<Feedback> {
	ServiceCollectionResponse<Feedback> findByUser(ServiceRequest<Feedback> request) throws UnknownHostException;
}
