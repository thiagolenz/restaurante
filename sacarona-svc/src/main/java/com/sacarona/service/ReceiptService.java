package com.sacarona.service;

import com.sacarona.common.svc.io.ServiceCollectionResponse;
import com.sacarona.common.svc.io.ServiceRequest;
import com.sacarona.model.dealing.Receipt;

public interface ReceiptService {
	Receipt insert (Receipt receipt);
	
	ServiceCollectionResponse<Receipt> findByUser (ServiceRequest<Receipt> request);
}
