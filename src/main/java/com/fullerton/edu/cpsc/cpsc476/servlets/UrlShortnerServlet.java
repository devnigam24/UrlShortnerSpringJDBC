package com.fullerton.edu.cpsc.cpsc476.servlets;

import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fullerton.edu.cpsc.cpsc476.Util.ErrorAndMessages;
import com.fullerton.edu.cpsc.cpsc476.Util.ShowErrorPageUtil;
import com.fullerton.edu.cpsc.cpsc476.pojo.NewUserDetails;

/**
 * Servlet implementation class UrlShortnerServlet
 */
public class UrlShortnerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SecureRandom randomString = new SecureRandom();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession thisSession = request.getSession();
		NewUserDetails userObject;
		userObject = (NewUserDetails) thisSession.getAttribute("userInsession");
		if (userObject == null) {
			userObject = new NewUserDetails("GuestUser", "Guest@guest.com", "noPassword", true);
		}
		
		String longUrl = request.getParameter("longUrl");
		String shortUrl = "";
		String pageName = request.getParameter("pageName");
		if (pageName == null || pageName == "")
			pageName = "welcome.jsp";
		if (longUrl.length() <= 0) {
			ShowErrorPageUtil.redirectToErrorPage(request, response, pageName, ErrorAndMessages.URLNULLMESSAGE);
			return;
		}
		/*for (String oneUrl : userObject.getUrlShornerMap().keySet()) {
			if (oneUrl.equalsIgnoreCase(longUrl)) {
				shortUrl = userObject.getUrlShornerMap().get(oneUrl);
			}
		}*/
		if (shortUrl.equals("")) {
			shortUrl = "http://" + request.getServerName() + ":" + request.getLocalPort() 
					+ request.getContextPath() + "/" + "Short/" + new BigInteger(30, randomString).toString(32);
		}
		request.setAttribute("longUrl", longUrl);
		request.setAttribute("shortUrl", shortUrl);
		if (userObject.getIsGuestUser().equals(Boolean.FALSE)) {
			//userObject.getUrlShornerMap().put(longUrl, shortUrl);
			request.getRequestDispatcher("welcome.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("publicUrlShortner.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
