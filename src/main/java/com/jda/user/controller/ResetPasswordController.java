package com.jda.user.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jda.user.model.Login;
import com.jda.user.model.User;
import com.jda.user.services.UserService;
import com.sun.istack.internal.logging.Logger;
@Controller
public class ResetPasswordController {
	@Autowired
	public UserService userService;
	private static final Logger logger = Logger.getLogger(RegistrationController.class);

	@RequestMapping(value = "/resetPassword", method = RequestMethod.GET)
	public ModelAndView showReset(@RequestParam("resetToken") String resetToken, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = null;
		User user=userService.getUserbyToken(resetToken);
	
		if(user == null){
			mav=new ModelAndView("login");
			mav.addObject("message", "Oops!  This is an invalid password reset link.");
			mav.addObject("login", new Login());
			return mav;
		}
		mav=new ModelAndView("resetPassword");
		mav.addObject("resetToken",resetToken);
		return mav;
	}

	@RequestMapping(value = "/resetPasswordProcess", method = RequestMethod.POST)
	public ModelAndView  addUser(HttpServletRequest request, HttpServletResponse response,
	      @RequestParam ("newPassword")String password ,@RequestParam Map<String, String> requestParams) throws IOException {
		logger.info(password);
		System.out.println();
		String token = requestParams.get("resetToken");
		ModelAndView mav =new ModelAndView("redirect:/");
		 boolean result = userService.newPassword(password, token);
		 if(result){
			 mav.addObject("message", "password reset sucessfully");
			return mav;
		 }
		 mav.addObject("message", "invalid token, try after sometime");
		 return mav;
	 
}
}