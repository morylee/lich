package com.first.service;

import com.first.model.User;

public interface UserService {
	public int insertUser(User user);
	
	public User getUserById(Integer id);
}
