package com.sacarona.dao.impl;

import java.net.UnknownHostException;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.sacarona.common.svc.io.ServiceCollectionResponse;
import com.sacarona.common.svc.io.ServiceRequest;
import com.sacarona.dao.CellPhoneRegisterDAO;
import com.sacarona.model.mobile.CellPhoneRegister;

@Repository
public class CellPhoneRegisterDAOImpl extends AbstractJpaDaoImpl<CellPhoneRegister> implements CellPhoneRegisterDAO {
	
	@Override
	public CellPhoneRegister findExistent(CellPhoneRegister cellPhoneRegister) throws UnknownHostException {		
		TypedQuery<CellPhoneRegister> query = 
				em.createQuery("from CellPhoneRegister o where o.userId = :userId and o.regId = :regId ", CellPhoneRegister.class);
		query.setParameter("userId", cellPhoneRegister.getUserId());
		query.setParameter("regId", cellPhoneRegister.getRegId());
		return singleQuery(query);
	}
	
	@Override
	public List<CellPhoneRegister> findByUser(Long userId) throws UnknownHostException {
		ServiceRequest<CellPhoneRegister> request = new ServiceRequest<>();
		TypedQuery<CellPhoneRegister> query = 
				em.createQuery("from CellPhoneRegister o where o.userId = :userId", CellPhoneRegister.class);
		query.setParameter("userId", userId);
		ServiceCollectionResponse<CellPhoneRegister> result = executeQueryForPagination(query, request);
		return result.getDataList();
	}
}
