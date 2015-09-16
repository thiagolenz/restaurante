package com.sacarona.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.sacarona.common.svc.io.ServiceCollectionResponse;
import com.sacarona.common.svc.io.ServiceRequest;
import com.sacarona.dao.ReceiptDAO;
import com.sacarona.model.dealing.Receipt;

@Repository
public class ReceiptDaoImpl extends AbstractJpaDaoImpl<Receipt> implements
		ReceiptDAO {

	@Override
	public ServiceCollectionResponse<Receipt> findByUser(ServiceRequest<Receipt> request) {
		TypedQuery<Receipt> query = 
				em.createQuery("from Receipt o where o.userId = :userId order by o.paymentDate DESC", Receipt.class);
		query.setParameter("userId", request.getUser().getId());
		return executeQueryForPagination(query, request);
	}

	@Override
	public List<Receipt> findAllByUser(Long userId) {
		TypedQuery<Receipt> query = 
				em.createQuery("from Receipt o where o.userId = :userId order by o.paymentDate DESC", Receipt.class);
		query.setParameter("userId", userId);
		return query.getResultList();
	}	
}
