package com.jda.user.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.jda.user.dao.UserDao;
import com.jda.user.model.Login;
import com.jda.user.model.User;



public class UserServiceImpl implements UserService{
	
	@Autowired
	UserDao userDao;
	@Transactional
	public void register(User user) {
		// TODO Auto-generated method stub
		userDao.register(user);
	}
	@Transactional
	public User validateUser(Login login) {
		// TODO Auto-generated method stub
		User user = userDao.validateUser(login);
		return user;
	}
	@Transactional
	public User findUserByEmail(String email) {
		User user = userDao.findUserByEmail(email);
		if(user == null) {
			return user;
		}
		String token = UUID.randomUUID().toString();
		user.setToken(token);
		userDao.update(token,email);
		return user;
	}
	@Transactional
	public void generateToken(User user) {
		
		
	}
	@Transactional
	public User getUserbyToken(String resetToken) {
		User user=userDao.getUserbyToken(resetToken) ;
		return user;
	}

	@Transactional
	public boolean newPassword(String password, String token) {
		if(userDao.getUserbyToken(token) == null){
			return false;
		}
		userDao.newPassword(password,token);
		return true;
		
		
	}

	
	
}
