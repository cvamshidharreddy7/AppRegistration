package com.jda.user.dao;



import com.jda.user.model.Login;
import com.jda.user.model.User;

public interface UserDao {
  void register(User user);
  User validateUser(Login login);

User ValidateEmail(String email);
void update(String token, String email);
User getUserbyToken(String resetToken);
void newPassword(String password, String token);
User findUserByEmail(String email);
  
}