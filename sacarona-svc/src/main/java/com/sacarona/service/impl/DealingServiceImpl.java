package com.sacarona.service.impl;

import java.net.UnknownHostException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sacarona.common.svc.exception.BusinessException;
import com.sacarona.common.svc.io.ServiceCollectionResponse;
import com.sacarona.common.svc.io.ServiceRequest;
import com.sacarona.dao.DealingDAO;
import com.sacarona.model.dealing.Dealing;
import com.sacarona.service.DealingService;
import com.sacarona.service.NotifyMobileService;

@Service
public class DealingServiceImpl implements DealingService {
	@Autowired private DealingDAO dealingDAO;
	private static final String GROUP_NAME = "DEALING";
	private static final String MESSAGE_NEW_DEALING = "sacarona.notification.newdealing";
	private static final String MESSAGE_DEALING_UPDATE = "sacarona.notification.dealingupdate";
	
	@Autowired
	private NotifyMobileService mobileService;
	
	@Transactional
	public Dealing insert(Dealing dealing) throws BusinessException {
		dealing.setCreateDate(new Date());
		mobileService.notify(GROUP_NAME, MESSAGE_NEW_DEALING, dealing.getOrderUser().getId());
		mobileService.notify(GROUP_NAME, MESSAGE_NEW_DEALING, dealing.getTravelerUser().getId());
		return dealingDAO.insert(dealing);
	}

	@Transactional
	public Dealing update(Dealing dealing, Long id) throws BusinessException {
		dealingDAO.update(dealing, id);
		mobileService.notify(GROUP_NAME, MESSAGE_DEALING_UPDATE, dealing.getOrderUser().getId());
		mobileService.notify(GROUP_NAME, MESSAGE_DEALING_UPDATE, dealing.getTravelerUser().getId());
		return dealing;
	}

	@Transactional
	public ServiceCollectionResponse<Dealing> findByUser(ServiceRequest<Dealing> request) throws BusinessException {
		try {
			return dealingDAO.findByUser(request);
		} catch (UnknownHostException e) {
			throw new BusinessException(e);
		}
	}

	@Transactional
	public void remove(Long dealingId) {
		dealingDAO.remove(dealingDAO.findById(Dealing.class, dealingId));
	}
	
	@Transactional
	public boolean existDealingFinished(Dealing dealing) {
		Dealing result = dealingDAO.findDealingFinished(dealing);
		return result != null;
	}

}
