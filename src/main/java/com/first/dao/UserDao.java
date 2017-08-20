package com.first.dao;

import com.first.model.User;

public interface UserDao {
	/**
	 * 添加新用户
	 * @param user
	 * @return
	 */
	public int insertUser(User user);
	
	/**
	 * 根据id查找用户
	 * @param id
	 * @return
	 */
	public User selectUserById(Integer id);
}
