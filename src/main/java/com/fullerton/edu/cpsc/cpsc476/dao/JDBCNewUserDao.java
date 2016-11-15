package com.fullerton.edu.cpsc.cpsc476.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.fullerton.edu.cpsc.cpsc476.daoInterfaces.NewUserInterface;
import com.fullerton.edu.cpsc.cpsc476.pojo.NewUserDetails;

public class JDBCNewUserDao implements NewUserInterface{
	
	private DataSource dataSource;
	private JdbcTemplate jdbctemplate;
	
	public void setDataSource(DataSource dataSource){
		this.dataSource=dataSource;
		this.jdbctemplate = new JdbcTemplate(this.dataSource);
	}

	public boolean inserNewUserInDB(NewUserDetails newUser) {
		try{
			String CRATENEWUSER = "insert into AllUsersTable (USERNAME,PASSWORD,EMAILID) values "
					+ "("+newUser.getUsername()+","+newUser.getPassword()+","+newUser.getEmailID()+")"; 
			jdbctemplate.update(CRATENEWUSER);
			//jdbctemplate.execute(CRATENEWUSER);
			return Boolean.TRUE;
		}catch(Exception e){
			e.printStackTrace();
			return Boolean.FALSE;
		}
	}

	public boolean insertLongAndShortURL(String userName, String longURL, String shortURL) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean updateUrlCount(String longURL, int count) {
		// TODO Auto-generated method stub
		return false;
	}

}
