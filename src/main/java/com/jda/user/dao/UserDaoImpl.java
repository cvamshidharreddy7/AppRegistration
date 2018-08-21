package com.jda.user.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.jda.user.model.Login;
import com.jda.user.model.User;



public class UserDaoImpl implements UserDao {
	  @Autowired
	  DataSource datasource;
	  @Autowired
	  JdbcTemplate jdbcTemplate;
	  
	public void register(User user) {
		// TODO Auto-generated method stub
		String sql = "insert into myusers1(USERNAME,PASSWORD,FIRSTNAME,LASTNAME,EMAIL,PHONE)  values(?,?,?,?,?,?)";
	    jdbcTemplate.update(sql, new Object[] { user.getUsername(), generator(user.getPassword()), user.getFirstname(),
	    user.getLastname(), user.getEmail(), user.getPhone() });
	}

	public User validateUser(Login login) {
		 String sql = "select * from myusers1 where email='" + login.getEmail() + "'";
	    List<User> users = jdbcTemplate.query(sql, new UserMapper());
	    return users.size() > 0 ? users.get(0) : null;
	}
	 public class UserMapper implements RowMapper<User> {
	 	  public User mapRow(ResultSet rs, int arg1) throws SQLException {
	 	    User user = new User();
	 	    user.setUsername(rs.getString("username"));
	 	    user.setPassword(rs.getString("password"));
	 	    user.setFirstname(rs.getString("firstname"));
	 	    user.setLastname(rs.getString("lastname"));
	 	    user.setEmail(rs.getString("email"));
	 	    
	 	    user.setPhone(rs.getString("phone"));
	 	    user.setToken(rs.getString("token"));
	 	    return user;
	 	  }
	 }

	 public User ValidateEmail(String email) {
			 String sql = "select * from myusers1 where email='" + email +  "'";
		    List<User> users = jdbcTemplate.query(sql, new UserMapper());
		    return users.size() > 0 ? users.get(0) : null;
		}

	public void update(String token, String email) {
		// TODO Auto-generated method stub
		String sql = "update myusers1 set token='"+token +"'  where email='" + email +  "'";
		 jdbcTemplate.update(sql);
	}
	
	public User getUserbyToken(String token) {
		 String sql = "select * from myusers1 where token='" + token +  "'";
	    List<User> users = jdbcTemplate.query(sql, new UserMapper());
	    return users.size() > 0 ? users.get(0) : null;
	}

	public void newPassword(String password, String token) {
		String sql="update myusers1 set password='"+password +"'  where token='"+token+"'";
		jdbcTemplate.update(sql);
	}

	public User findUserByEmail(String email) {
		 String sql = "select * from myusers1 where email='" + email +  "'";
	    List<User> users = jdbcTemplate.query(sql, new UserMapper());
	    return users.size() > 0 ? users.get(0) : null;
	}
	public String generator(String password) {

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(11);
		String hashedPassword = passwordEncoder.encode(password);
		System.out.println(hashedPassword);
		return hashedPassword;

	}
}
