package com.first.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.first.dao.UserDao;
import com.first.model.User;
import com.first.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	
	@Override
	public int insertUser(User user) {
		return userDao.insertUser(user);
	}

	@Override
	public User getUserById(Integer id) {
		return userDao.selectUserById(id);
	}
}
