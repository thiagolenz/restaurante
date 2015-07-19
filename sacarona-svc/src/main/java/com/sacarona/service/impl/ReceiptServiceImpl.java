package com.sacarona.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sacarona.common.svc.io.ServiceCollectionResponse;
import com.sacarona.common.svc.io.ServiceRequest;
import com.sacarona.dao.ReceiptDAO;
import com.sacarona.model.dealing.Receipt;
import com.sacarona.service.ReceiptService;

@Service
public class ReceiptServiceImpl implements ReceiptService {
	@Autowired private ReceiptDAO receiptDAO;

	@Transactional
	public Receipt insert(Receipt receipt) {
		return receiptDAO.insert(receipt);
	}

	@Transactional
	public ServiceCollectionResponse<Receipt> findByUser(ServiceRequest<Receipt> request) {
		return receiptDAO.findByUser(request);
	}

}
