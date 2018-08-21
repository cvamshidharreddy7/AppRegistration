package com.jda.user.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jda.user.dao.UserDao;
import com.jda.user.model.User;
import com.jda.user.services.MailService;
import com.jda.user.services.UserService;

@Controller
public class ForgotPasswordController {
	@Autowired
	UserDao userDao;
	@Autowired
	UserService userService ;
	@Autowired
	MailService mailService;
	@RequestMapping(value = "/ForgotPassword")
	public ModelAndView showRegister(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("ForgotPassword");
	//	mav.addObject("user", new User());
		
		return mav;
	}
	
/*	@RequestMapping(value = "/forgotProcess", method = RequestMethod.POST)
	  public void loginProcess(HttpServletRequest request, HttpServletResponse response,
			  @ModelAttribute("forget") String email) {
		 ModelAndView mav ;
		 boolean emailExist=userDao.validateEmail(email);
		 if(emailExist){
			 System.out.println("true");
		 }
		 else{
			 System.out.println("false");
		 }
	 }*/
	@RequestMapping(value = "/forgotPasswordProcess", method = RequestMethod.POST)
	  public void addUser(HttpServletRequest request, HttpServletResponse response,
			  @RequestParam("email") String email) {
		System.out.println(email);
	ModelAndView mav ;
		 /*User emailExist=userDao.ValidateEmail(email);
		 if(emailExist != null){*/
		//	 userService.generateToken(emailExist);
			// String token = UUID.randomUUID().toString();
			 User user = userService.findUserByEmail(email);
			 if(user!=null){
			 String appUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getServletContext().getContextPath();
			 
			 mailService.sendSimpleMessage(email, "Reset password link ", "To reset your password, click the link below:\n" + appUrl + "/resetPassword?resetToken="
						+ user.getToken());
						 
			 }
		 else{
			 System.out.println("false");
		 }
	 
	}}
