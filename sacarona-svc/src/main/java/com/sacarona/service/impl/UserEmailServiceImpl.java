package com.sacarona.service.impl;

import org.springframework.stereotype.Service;

import com.sacarona.service.UserEmailService;

@Service
public class UserEmailServiceImpl implements UserEmailService {

//	@Autowired
//	private EmailIntegration emailIntegration;
//	
//	@Value("${nutrieduc.web.url}" )
//	private String urlWeb;
//	
//	@Override
//	public void sendChangePasswordRequest(ChangePasswordToken changePasswordToken) {
//		String link = urlWeb+ "changePassword?token="+ changePasswordToken.getToken();
//				
//	 	String content = "<h1>Titulo</h1> corpo do texto " +
//						"<a href='"+link+"'>Clique aqui </a>" +
//						" <h1> outro texto</h1>";
//	 	User user = changePasswordToken.getUser();
//	 	EmailBean emailBean = new EmailBean();
//	 	emailBean.setToName(user.getName());
//	 	emailBean.addToEmail(user.getEmail());
//	 	emailBean.setSubject("Change Password");
//	 	emailBean.setContent(content);
//	 	try {
//			emailIntegration.sendEmail(emailBean);
//		} catch (RestClientException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

}
