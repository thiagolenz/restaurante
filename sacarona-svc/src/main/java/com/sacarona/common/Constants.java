package com.sacarona.common;

/**
 * Common constants of NutriEduc
 * @author thiagolenz
 * @since Aug 25, 2014
 */
public interface Constants {
	/**
	 * The <code>application/json</code>APP_CLIENT_TOKENAPP_CLIENT_TOKEN value constant
	 */
	 String APPLICATION_JSON = "application/json";
	 
	 /**
	  * The user session identification attribute
	  */
	 String USER_ATTRIBUTE = "user.session.id";

	 /**
	  * The user id to send to back end
	  */
	 String USER_TOKEN = "user.token";
	 
	 /**
	  * Used to Inform the backend the key of Service Gateway
	  */
	 String SERVICE_GATEWAY_KEY = "service.gateway.key";
	 
	 /**
	  * This is used on every request to validate if the client app is authorized
	  */
	 String APP_CLIENT_TOKEN = "app.client.token";
	 
	 /**
	  * This represent the property of service gateway url used of app clients app.
	  */
	 String SERVICE_GATEWAY_URL = "service.gateway.url";
	 
	 /**
	  * Represent the header key when the request don't need that user is logged in.
	  */
	 String PUBLIC_REQUEST_TOKEN = "public.request.token";
	 
	 /**
	  * Params to define if the request is a AdminUser (true) os User (false)
	  */
	 String REQUEST_IS_ADMIN = "request.is.admin";
	 
}
