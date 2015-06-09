package com.sacarona.service.impl;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import com.sacarona.common.svc.exception.BusinessException;
import com.sacarona.dao.CellPhoneRegisterDAO;
import com.sacarona.model.mobile.CellPhoneRegister;
import com.sacarona.service.NotifyMobileService;

@Service
public class NotifyMobileServiceImpl implements NotifyMobileService {
	private static final String GOOGLE_SERVER_KEY = "AIzaSyBOV1p4_eCqLqDTdS_rtha9yCTGCsTAO88";

	@Autowired private CellPhoneRegisterDAO cellPhoneRegisterDAO;

	public void notify(String message, Long userId) throws BusinessException {
		try {
			List<CellPhoneRegister> registers = cellPhoneRegisterDAO.findByUser(userId);

			for (CellPhoneRegister cellPhoneRegister : registers) {
				send(cellPhoneRegister, message);
			}
		}
		catch (UnknownHostException e) {
			throw new BusinessException(e);
		} catch (IOException e) {
			throw new BusinessException(e);
		}

	}

	public void send(CellPhoneRegister bean, String messageAll) throws IOException {
		Sender sender = new Sender(GOOGLE_SERVER_KEY);
		Message message = new Message.Builder()
		.timeToLive(2419200)
		.delayWhileIdle(false)
		.addData("DEFAULT", messageAll).build();

		Result result = sender.send(message, bean.getRegId(), 1);
		System.out.println(result);
	}

}
