package com.sacarona.common.svc.io;

import java.io.Serializable;

import com.sacarona.model.User;

/**
 * Encapsulate the request, records range, current page and entity filter
 * @author thiagolenz
 * @since Aug 26, 2014
 *
 * @param <T>
 */
public class ServiceRequest<T> implements Serializable {

	private static final long serialVersionUID = 2895009578384768374L;
	private T entity;
	private int recordsRange;
	private int currentPage;
	private boolean admin;
	private User user;

	public T getEntity() {
		return entity;
	}
	public void setEntity(T entity) {
		this.entity = entity;
	}
	public int getRecordsRange() {
		return recordsRange;
	}
	public void setRecordsRange(Integer recordsRange) {
		this.recordsRange = recordsRange;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}	
	public int getOffset () {
		return currentPage * recordsRange;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public User getUser() {
		return user;
	}
}
