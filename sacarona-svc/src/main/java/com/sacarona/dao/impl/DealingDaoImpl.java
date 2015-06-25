package com.sacarona.dao.impl;

import java.net.UnknownHostException;

import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sacarona.common.svc.io.ServiceCollectionResponse;
import com.sacarona.common.svc.io.ServiceRequest;
import com.sacarona.dao.DealingDAO;
import com.sacarona.dao.OrderDAO;
import com.sacarona.dao.TravelDAO;
import com.sacarona.dao.UserDAO;
import com.sacarona.model.dealing.Dealing;

@Repository
public class DealingDaoImpl extends AbstractJpaDaoImpl<Dealing> implements DealingDAO {
	
	@Autowired private UserDAO userDAO;
	@Autowired private TravelDAO travelDAO;
	@Autowired private OrderDAO orderDAO;
	
	@Override
	public ServiceCollectionResponse<Dealing> findByUser(ServiceRequest<Dealing> request) throws UnknownHostException {
		String strQuery = "from Dealing o where o.travelerUser = :user or o.orderUser = :user order by o.createDate DESC";
		TypedQuery<Dealing> query = em.createQuery(strQuery, Dealing.class);
		query.setParameter("user", request.getEntity().getTravelerUser());
		return executeQueryForPagination(query, request);
	}
}
