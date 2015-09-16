package com.sacarona.dao.impl;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.sacarona.dao.FeedbackAverageDAO;
import com.sacarona.model.feedback.FeedbackAverage;

@Repository
public class FeedbackAverageDaoImpl extends AbstractJpaDaoImpl<FeedbackAverage> implements FeedbackAverageDAO {

	@Override
	public FeedbackAverage findByUser(Long userId){
		TypedQuery<FeedbackAverage> query = 
				em.createQuery("from FeedbackAverage o where o.userId = :userId ", FeedbackAverage.class);
		query.setParameter("userId", userId);
		return singleQuery(query);
	}
}
