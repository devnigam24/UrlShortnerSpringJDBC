package com.fullerton.edu.cpsc.cpsc476.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.fullerton.edu.cpsc.cpsc476.Util.ErrorAndMessages;
import com.fullerton.edu.cpsc.cpsc476.Util.ShowErrorPageUtil;
import com.fullerton.edu.cpsc.cpsc476.daoInterfaces.NewUserInterface;
import com.fullerton.edu.cpsc.cpsc476.pojo.NewUserDetails;
import com.fullerton.edu.cpsc.cpsc476.pojo.URL;

public class JDBCNewUserDao implements NewUserInterface {

	private DataSource dataSource;
	private JdbcTemplate jdbctemplate;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbctemplate = new JdbcTemplate(this.dataSource);
	}

	public Boolean inserNewUserInDB(NewUserDetails newUser, HttpServletRequest req,HttpServletResponse res) {
		try {
			String CRATENEWUSER = "INSERT INTO ALLUSERSTABLE (USERNAME,PASSWORD) VALUES " + "('"
					+ newUser.getUsername() + "','" + newUser.getPassword() + "')";
			if(jdbctemplate.update(CRATENEWUSER) >0){
				return true;
			}else{
				return false;				
			}
		} catch (DuplicateKeyException dke) {
			ShowErrorPageUtil.redirectToErrorPage(req,res, "signUp.jsp", ErrorAndMessages.COLLECTIONEXISTS);
			return null;
		} catch (Exception e) {
			return false;
		}
	
	}

	public Boolean insertLongAndShortURL(String userName,String longURL, String shortURL,int hits) {
		try {
			String INSERTURL = "INSERT INTO URLHITS (USERNAME,LONGURL,SHORTURL,HITS) VALUES "
					+ "('"+userName+"','"+longURL+"','"+shortURL+"','"+hits+"')";
			if(jdbctemplate.update(INSERTURL) >0){
				return true;
			}else{
				return false;				
			}
		} catch (Exception e) {
			return false;
		}
	
	}

	public Boolean updateUrlHits(String longURL) {
		try{
			String checkHits = "SELECT HITS FROM URLHITS WHERE LONGURL = '"+longURL+"'";
			int hits = jdbctemplate.queryForObject(checkHits,Integer.class);		
			String updateHits = "UPDATE URLHITS SET HITS="+ ++hits +" WHERE LONGURL = '"+longURL+"'";
			if(jdbctemplate.update(updateHits) > 0){
				return true;
			}else{
				return false;
			}
		}catch(Exception e){
			return false;
		}
	}

	public Boolean authenticateThisUser(String username, String password) {
		try {
			String checkUser = "SELECT COUNT(*) FROM ALLUSERSTABLE WHERE USERNAME='" + username + "' AND PASSWORD='"
					+ password + "'";
			if((int)jdbctemplate.queryForObject(checkUser,Integer.class) >= 1){
				return true;
			}else{
				return false;
			}			
		} catch (Exception e) {
			return false;
		}
	}

	public NewUserDetails getUserInfo(String Username) {
		try {
			String getUser = "SELECT * FROM ALLUSERSTABLE WHERE USERNAME='"+Username+"'";
			if (jdbctemplate.queryForList(getUser) != null && jdbctemplate.queryForList(getUser).size() > 0) {
				List<Map<String, Object>> rows = jdbctemplate.queryForList(getUser);
				for (Map row : rows) {
					NewUserDetails newUser = new NewUserDetails();
					newUser.setUsername((String)(row.get("USERNAME")));
					newUser.setPassword((String)(row.get("PASSWORD")));
					newUser.setIsGuestUser(false);
					return newUser;
				}
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
		return null;
	}

	public String getShortUrl(String longUrl) {
		try {
			String getShortUrl = "SELECT SHORTURL FROM URLHITS WHERE LONGURL='"+longUrl+"'";
			String shortUrl = jdbctemplate.queryForObject(getShortUrl,String.class);
			if(null != shortUrl && shortUrl.length() > 0){
				return shortUrl;
			}else{
				return "";
			}		
		}catch(EmptyResultDataAccessException erdae){
			return "";
		} 
		catch (Exception e) {
			return "";
		}
	}

	public Boolean insertUrlHits(String userName,String longUrl,String shortUrl, int i) {
		try {
			String insertHits = "INSERT INTO URLHITS (USERNAME,LONGURL,SHORTURL,HITS) VALUES ('"+userName+"','"+longUrl+"','"+shortUrl+"','"+i+"')";
			if(jdbctemplate.update(insertHits) >0){
				return true;
			}else{
				return false;				
			}
		} catch (Exception e) {
			return false;
		}		
	}

	public String getLongUrl(String shortUrl) {
		try {
			String getLongUrl = "SELECT LONGURL FROM URLHITS WHERE SHORTURL='" + shortUrl + "'";
			String longUrl = jdbctemplate.queryForObject(getLongUrl, String.class);
			if (null != longUrl && longUrl.length() > 0) {
				return longUrl;
			} else {
				return "";
			}
		} catch (EmptyResultDataAccessException erdae) {
			return "";
		} catch (Exception e) {
			return "";
		}

	}

	public ArrayList<URL> getAllUrls(String UserName) {
		try {
			ArrayList<URL> thisList = new ArrayList<URL>();
			String selectAll = "SELECT LONGURL,SHORTURL,HITS FROM URLHITS WHERE USERNAME='"+UserName+"'";
			if (jdbctemplate.queryForList(selectAll) != null && jdbctemplate.queryForList(selectAll).size() > 0) {
				List<Map<String, Object>> rows = jdbctemplate.queryForList(selectAll);				
				for (Map row : rows) {
					URL myUrl = new URL();
					myUrl.setLongName((String)row.get("LONGURL"));
					myUrl.setShortName((String)row.get("SHORTURL"));
					myUrl.setHits((Integer)row.get("HITS"));
					thisList.add(myUrl);
				}
				return thisList;
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}

}
