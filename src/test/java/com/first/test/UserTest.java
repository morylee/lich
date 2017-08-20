package com.first.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.first.model.User;
import com.first.service.UserService;

public class UserTest {
	private UserService userService;
	
	@Before
	public void before(){
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:conf/spring.xml"
				,"classpath:conf/spring-mybatis.xml"});
		userService = (UserService) context.getBean("userServiceImpl");
	}
	
//	@Test
//	public void addUser(){
//		User user = new User();
//		user.setName("晨辉");
//		user.setAge(25);
//		System.out.println(userService.insertUser(user));
//	}
	
	@Test
	public void findUser(){
		User user = userService.getUserById(2);
		System.out.println(user.getName());
	}
}
