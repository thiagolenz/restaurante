package com.sacarona.service;

import com.sacarona.common.svc.exception.BusinessException;


public interface NotifyMobileService {
	public void notify (String groupMessage, String message, Long userId) throws BusinessException;
}
