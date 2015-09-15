package com.sacarona.service.impl;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import com.sacarona.common.svc.exception.BusinessException;
import com.sacarona.dao.CellPhoneRegisterDAO;
import com.sacarona.model.mobile.CellPhoneRegister;
import com.sacarona.service.NotifyMobileService;

@Service
public class NotifyMobileServiceImpl implements NotifyMobileService {
	private static final String GOOGLE_SERVER_KEY = "AIzaSyBiHlkZBX5jCadjXkADp10533RS0EDzSTc";

	@Autowired private CellPhoneRegisterDAO cellPhoneRegisterDAO;

	@Transactional
	public void notify(String groupMessage, String message, Long userId) throws BusinessException {
		try {
			List<CellPhoneRegister> registers = cellPhoneRegisterDAO.findByUser(userId);

			for (CellPhoneRegister cellPhoneRegister : registers) {
				System.out.println("cellReg "+ cellPhoneRegister.getRegId() + ", group "+ groupMessage + ", message: "+ message);
				send(cellPhoneRegister, message, groupMessage);
			}
		}
		catch (UnknownHostException e) {
			throw new BusinessException(e);
		} catch (IOException e) {
			throw new BusinessException(e);
		}

	}

	public void send(CellPhoneRegister bean, String messageAll, String group) throws IOException {
		Sender sender = new Sender(GOOGLE_SERVER_KEY);
		Message message = new Message.Builder()
											.timeToLive(2419200)
											.delayWhileIdle(false)
											.addData(group, messageAll).build();

		Result result = sender.send(message, bean.getRegId(), 1);
		System.out.println(result);
	}

}
