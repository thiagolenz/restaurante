package com.sacarona.common.context;

import java.io.IOException;
import java.lang.reflect.Field;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.base.Strings;
import com.sacarona.common.Constants;
import com.sacarona.common.svc.exception.BusinessException;
import com.sacarona.model.User;
import com.sacarona.service.UserService;

/**
 * Aspect that is executed before each Controller or BundleService. It reads the headers and configure the {@link RequestContext} object
 * @author thiagolenz
 * @since Aug 26, 2014
 *
 */
@Aspect
public class RequestContextAspect {
	@Autowired
	HttpServletRequest httpServletRequest;
	@Autowired
	private UserService userService;
	
	@Before("bean(*Controller) or bean(*BundleService)")
	public void logBefore(JoinPoint joinPoint) throws IllegalArgumentException, IllegalAccessException, IOException, BusinessException {
		for (Field field : joinPoint.getTarget().getClass().getDeclaredFields()) {
			if (field.getType().equals(RequestContext.class)) {
				field.setAccessible(true);
				field.set(joinPoint.getTarget(), createRequestContext());
				break;
			}
		}
	}
	public RequestContext createRequestContext () throws IOException, BusinessException {
		RequestContext context = new RequestContext();
		confitureUserContext(context);
		String currentPage = httpServletRequest.getHeader("sys-pagination-currentpage");
		if (currentPage == null)
			currentPage = "0";
		context.setCurrentPage(Integer.valueOf(currentPage));
		String limit = httpServletRequest.getHeader("sys-pagination-recordsrange");
		if (limit == null)
			limit = "0";
		context.setLimit(Integer.valueOf(limit));
		return context;
	}
	
	private void confitureUserContext(RequestContext context) throws IOException, BusinessException {
		context.setUser(getUserContext());
	}
	
	private User getUserContext () throws IOException, BusinessException {
		String userToken = httpServletRequest.getHeader(Constants.USER_TOKEN);
		String appClientToken = httpServletRequest.getHeader(Constants.APP_CLIENT_TOKEN);
		
		if (!Strings.isNullOrEmpty(userToken) && !Strings.isNullOrEmpty(appClientToken)) {
			return userService.findByTokenAndApp(userToken, appClientToken);
		}
		return null;
	}
}
