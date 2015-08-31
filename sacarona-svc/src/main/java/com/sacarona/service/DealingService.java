package com.sacarona.service;

import com.sacarona.common.svc.exception.BusinessException;
import com.sacarona.common.svc.io.ServiceCollectionResponse;
import com.sacarona.common.svc.io.ServiceRequest;
import com.sacarona.model.dealing.Dealing;

public interface DealingService {
	Dealing insert (Dealing dealing) throws BusinessException;
	Dealing update (Dealing dealing, Long id) throws BusinessException;
	ServiceCollectionResponse<Dealing> findByUser (ServiceRequest<Dealing> request) throws BusinessException;
	void remove (Long dealingId);
	
	boolean existDealingFinished (Dealing dealing);
}
