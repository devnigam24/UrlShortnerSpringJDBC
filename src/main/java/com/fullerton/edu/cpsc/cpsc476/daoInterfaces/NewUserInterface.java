package com.fullerton.edu.cpsc.cpsc476.daoInterfaces;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fullerton.edu.cpsc.cpsc476.pojo.NewUserDetails;
import com.fullerton.edu.cpsc.cpsc476.pojo.URL;

public interface NewUserInterface {
	
	Boolean inserNewUserInDB(NewUserDetails newUser,HttpServletRequest req,HttpServletResponse res);
	Boolean insertLongAndShortURL(String userName,String longURL, String shortURL,int hits);
	Boolean updateUrlHits(String longURL);
	Boolean authenticateThisUser(String userName,String password);
	NewUserDetails getUserInfo(String Username);
	String getShortUrl(String longUrl);
	Boolean insertUrlHits(String userName,String longUrl,String shortUrl, int i);
	String getLongUrl(String shortUrl);
	ArrayList<URL> getAllUrls(String userName);
}
  