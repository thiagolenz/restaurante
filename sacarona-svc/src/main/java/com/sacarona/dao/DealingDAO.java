package com.sacarona.dao;

import java.net.UnknownHostException;

import com.sacarona.common.svc.io.ServiceCollectionResponse;
import com.sacarona.common.svc.io.ServiceRequest;
import com.sacarona.model.dealing.Dealing;

public interface DealingDAO extends GenericDAO<Dealing> {
	ServiceCollectionResponse<Dealing> findByUser(ServiceRequest<Dealing> request) throws UnknownHostException;
}
