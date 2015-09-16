package com.sacarona.dao;

import java.util.List;

import com.sacarona.common.svc.io.ServiceCollectionResponse;
import com.sacarona.common.svc.io.ServiceRequest;
import com.sacarona.model.dealing.Receipt;

public interface ReceiptDAO extends GenericDAO<Receipt> {
	ServiceCollectionResponse<Receipt> findByUser (ServiceRequest<Receipt> request);
	List<Receipt> findAllByUser (Long userId);
}
