package com.sacarona.common.svc.exception;

public class RequestNotAuthorizedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2535305036946360772L;

	public RequestNotAuthorizedException() {
		super("Request not authorized");
	}
}
