package com.first.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.first.model.User;
import com.first.service.UserService;

@Controller
@RequestMapping("/")
public class UserController {
	@Resource  
    private UserService userService; 

	@RequestMapping("user/index")
	public String index(){
		return "user/index";
	}
	
	@RequestMapping("user/show")
	public ModelAndView show(){
		ModelAndView mav = new ModelAndView("user/show");
		User user = userService.getUserById(2);
		mav.addObject("user", user);
		return mav;
	}
}
