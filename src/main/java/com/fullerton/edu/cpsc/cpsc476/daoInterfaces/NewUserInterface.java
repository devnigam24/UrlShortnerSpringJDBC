package com.fullerton.edu.cpsc.cpsc476.daoInterfaces;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fullerton.edu.cpsc.cpsc476.pojo.NewUserDetails;

public interface NewUserInterface {
	
	Boolean inserNewUserInDB(NewUserDetails newUser,HttpServletRequest req,HttpServletResponse res);
	Boolean insertLongAndShortURL(String userName,String longURL,String shortURL,HttpServletRequest req,HttpServletResponse res);
	Boolean updateUrlCount(String longURL,int count,HttpServletRequest req,HttpServletResponse res);
	Boolean authenticateThisUser(NewUserDetails newUser,HttpServletRequest req,HttpServletResponse res);
}
  