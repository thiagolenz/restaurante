package com.sacarona.common.context;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.base.Strings;
import com.sacarona.common.Constants;
import com.sacarona.common.svc.exception.BusinessException;
import com.sacarona.common.svc.exception.RequestNotAuthorizedException;
import com.sacarona.model.mobile.AppUserAuth;
import com.sacarona.service.AppUserAuthService;

@Aspect
public class RequestTokenValidator {
	private static final String PUBLIC_TOKEN = "739b7a0a-2b73-455b-87b2-dd511afabde5";
	
	@Autowired
	HttpServletRequest httpServletRequest;
	
	@Autowired
	private AppUserAuthService appUserAuthService;
	
	@Before("bean(*Controller)")
	public void validateServiceGateway(JoinPoint joinPoint) 
			throws IllegalArgumentException, IllegalAccessException, IOException, RequestNotAuthorizedException, BusinessException {
		
		String publicToken = httpServletRequest.getHeader(Constants.PUBLIC_REQUEST_TOKEN);
		String appToken = httpServletRequest.getHeader(Constants.APP_CLIENT_TOKEN);
		String userToken = httpServletRequest.getHeader(Constants.USER_TOKEN);
		
		System.out.println("PUBLIC = " + publicToken);
		System.out.println("APPTOKEN = " + appToken);
		System.out.println("USERTOKEN = " + userToken);
		
		boolean isAuthorized = false;
		
		if (PUBLIC_TOKEN.equals(publicToken)) {
			isAuthorized = true;
		} else if (!Strings.isNullOrEmpty(userToken) && !Strings.isNullOrEmpty(appToken)) {
			AppUserAuth auth = appUserAuthService.find(new AppUserAuth(appToken, userToken));
			if (auth != null)
				isAuthorized = true;
		}
		
		if (!isAuthorized)
			throw new RequestNotAuthorizedException();
	}
}
