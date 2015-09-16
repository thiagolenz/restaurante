package com.sacarona.dao;

import com.sacarona.model.user.User;

public interface UserDAO extends GenericDAO<User> {
	User findBySocialMediaAndEmail (User user);
}
