package com.jda.user.services;

import com.jda.user.model.Login;
import com.jda.user.model.User;

public interface UserService {
	void register(User user);
	  User validateUser(Login login);
}
