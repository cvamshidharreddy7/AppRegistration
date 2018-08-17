package com.jda.user.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.jda.user.model.Login;
import com.jda.user.model.User;



public class UserDaoImpl implements UserDao {
	  @Autowired
	  DataSource datasource;
	  @Autowired
	  JdbcTemplate jdbcTemplate;
	@Override
	public void register(User user) {
		// TODO Auto-generated method stub
		String sql = "insert into myusers1  values(?,?,?,?,?,?)";
	    jdbcTemplate.update(sql, new Object[] { user.getUsername(), user.getPassword(), user.getFirstname(),
	    user.getLastname(), user.getEmail(), user.getPhone() });
	}

	@Override
	public User validateUser(Login login) {
		 String sql = "select * from myusers1 where username='" + login.getEmail() + "' and password='" + login.getPassword()
	    + "'";
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
	 	    return user;
	 	  }
	 }
}
