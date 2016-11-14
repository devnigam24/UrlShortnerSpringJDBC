package com.fullerton.edu.cpsc.cpsc476.dao;

import javax.sql.DataSource;

import com.fullerton.edu.cpsc.cpsc476.daoInterfaces.NewUserInterface;
import com.fullerton.edu.cpsc.cpsc476.pojo.NewUserDetails;

public class JDBCNewUserDao implements NewUserInterface{
	
	private DataSource dataSource;
	
	public void setDataSource(DataSource ds){
		this.dataSource=ds;
	}

	public boolean inserNewUserInDB(NewUserDetails newUser) {
		// TODO Auto-generated method stub
		return false;
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
