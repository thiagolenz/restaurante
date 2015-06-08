package com.sacarona.dao;

import java.net.UnknownHostException;

import com.sacarona.model.feedback.FeedbackAverage;

public interface FeedbackAverageDAO extends GenericDAO<FeedbackAverage> {
	FeedbackAverage findByUser (Long userId) throws UnknownHostException;
}
