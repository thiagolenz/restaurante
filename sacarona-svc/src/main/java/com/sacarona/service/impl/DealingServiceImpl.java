package com.sacarona.service.impl;

import java.net.UnknownHostException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sacarona.common.svc.exception.BusinessException;
import com.sacarona.common.svc.io.ServiceCollectionResponse;
import com.sacarona.common.svc.io.ServiceRequest;
import com.sacarona.dao.DealingDAO;
import com.sacarona.model.dealing.Dealing;
import com.sacarona.service.DealingService;

@Service
public class DealingServiceImpl implements DealingService {
	@Autowired private DealingDAO dealingDAO;

	@Override
	public Dealing insert(Dealing dealing) {
		dealing.setCreateDate(new Date());
		return dealingDAO.insert(dealing);
	}

	@Override
	public Dealing update(Dealing dealing, Long id) {
		dealingDAO.update(dealing, id);
		return dealing;
	}

	@Override
	public ServiceCollectionResponse<Dealing> findByUser(ServiceRequest<Dealing> request) throws BusinessException {
		try {
			return dealingDAO.findByUser(request);
		} catch (UnknownHostException e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public void remove(Long dealingId) {
		dealingDAO.remove(dealingDAO.findById(Dealing.class, dealingId));
	}

}
