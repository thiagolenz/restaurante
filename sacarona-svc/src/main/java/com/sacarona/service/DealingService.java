package com.sacarona.service;

import com.sacarona.common.svc.exception.BusinessException;
import com.sacarona.common.svc.io.ServiceCollectionResponse;
import com.sacarona.common.svc.io.ServiceRequest;
import com.sacarona.model.dealing.Dealing;

public interface DealingService {
	Dealing insert (Dealing dealing);
	Dealing update (Dealing dealing, Long id);
	ServiceCollectionResponse<Dealing> findByUser (ServiceRequest<Dealing> request) throws BusinessException;
	void remove (Long dealingId);
}
