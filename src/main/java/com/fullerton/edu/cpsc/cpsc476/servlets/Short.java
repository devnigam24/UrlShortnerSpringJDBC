package com.fullerton.edu.cpsc.cpsc476.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fullerton.edu.cpsc.cpsc476.pojo.NewUserDetails;

/**
 * Servlet implementation class Short
 */
public class Short extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession thisSession = request.getSession();
		NewUserDetails userObject = (NewUserDetails)thisSession.getAttribute("userInsession");
		if (userObject == null) {
			userObject = new NewUserDetails("GuestUser", "Guest Email Id", "", true);
		}
		int urlCount = 0;
		String longUrl = "";
		StringBuffer shortUrlBuffer = request.getRequestURL();
		String shortUrl = shortUrlBuffer.toString().trim();
		
		/*if (userObject.getUrlShornerUrlCountMap().containsKey(shortUrl)) {
			urlCount = userObject.getUrlShornerUrlCountMap().get(shortUrl);
		}
		userObject.getUrlShornerUrlCountMap().put(shortUrl, ++urlCount);
		for (String oneUrl : userObject.getUrlShornerMap().keySet()) {
			if (shortUrl.equalsIgnoreCase(userObject.getUrlShornerMap().get(oneUrl))) {
				longUrl = oneUrl;
				break;
			}
		}*/
		if(!(longUrl.contains("http://") || longUrl.contains("https://"))){
			longUrl = "http://" + longUrl;
		}
		thisSession.setAttribute("longUrlToGo", longUrl);
		response.sendRedirect("/UrlShortnerCPSC476/redirectToLongUrl.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
