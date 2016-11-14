package com.fullerton.edu.cpsc.cpsc476.daoInterfaces;

import com.fullerton.edu.cpsc.cpsc476.pojo.NewUserDetails;

public interface NewUserInterface {
	
	boolean inserNewUserInDB(NewUserDetails newUser);
	boolean insertLongAndShortURL(String userName,String longURL,String shortURL);
	boolean updateUrlCount(String longURL,int count);
}
  