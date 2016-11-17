package com.fullerton.edu.cpsc.cpsc476.dao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.fullerton.edu.cpsc.cpsc476.Util.ErrorAndMessages;
import com.fullerton.edu.cpsc.cpsc476.Util.ShowErrorPageUtil;
import com.fullerton.edu.cpsc.cpsc476.daoInterfaces.NewUserInterface;
import com.fullerton.edu.cpsc.cpsc476.pojo.NewUserDetails;

public class JDBCNewUserDao implements NewUserInterface {

	private DataSource dataSource;
	private JdbcTemplate jdbctemplate;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbctemplate = new JdbcTemplate(this.dataSource);
	}

	public Boolean inserNewUserInDB(NewUserDetails newUser, HttpServletRequest req,HttpServletResponse res) {
		try {
			String CRATENEWUSER = "insert into AllUsersTable (USERNAME,PASSWORD,EMAILID) values " + "('"
					+ newUser.getUsername() + "','" + newUser.getPassword() + "','" + newUser.getEmailID() + "')";
			jdbctemplate.update(CRATENEWUSER);
			return true;
		} catch (DuplicateKeyException dke) {
			ShowErrorPageUtil.redirectToErrorPage(req,res, "signUp.jsp", ErrorAndMessages.COLLECTIONEXISTS);
			return null;
		} catch (Exception e) {
			return false;
		}
	
	}

	public Boolean insertLongAndShortURL(String userName, String longURL, String shortURL, HttpServletRequest req,
			HttpServletResponse res) {
		// TODO Auto-generated method stub
		return null;
	}

	public Boolean updateUrlCount(String longURL, int count, HttpServletRequest req, HttpServletResponse res) {
		// TODO Auto-generated method stub
		return null;
	}

	public Boolean authenticateThisUser(NewUserDetails newUser, HttpServletRequest req, HttpServletResponse res) {
		// TODO Auto-generated method stub
		return null;
	}

}
