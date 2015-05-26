package com.sacarona.common.context;

import com.sacarona.model.User;

/**
 * Class that contains the information of Request Context 
 * @author thiagolenz
 * @since Aug 26, 2014
 *
 */
public class RequestContext {
	private static final int HIGHER_LIMIT = 1000;
	private User user;
	private int currentPage;
	private int limit;
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public User getUser() {
		return user;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	public void setLimit(Integer limit) {
		if (limit > HIGHER_LIMIT)
			limit = HIGHER_LIMIT;
		this.limit = limit;
	}
	
	public Integer getLimit() {
		return limit;
	}
}
