package com.sacarona.dao;

import com.sacarona.model.feedback.FeedbackAverage;

public interface FeedbackAverageDAO extends GenericDAO<FeedbackAverage> {
	FeedbackAverage findByUser (Long userId);
}
