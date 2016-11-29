package com.fullerton.edu.cpsc.cpsc476.servlets;

import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fullerton.edu.cpsc.cpsc476.Util.ErrorAndMessages;
import com.fullerton.edu.cpsc.cpsc476.Util.ShowErrorPageUtil;
import com.fullerton.edu.cpsc.cpsc476.dao.JDBCNewUserDao;
import com.fullerton.edu.cpsc.cpsc476.pojo.NewUserDetails;

/**
 * Servlet implementation class UrlShortnerServlet
 */
public class UrlShortnerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SecureRandom randomString = new SecureRandom();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ApplicationContext context = new ClassPathXmlApplicationContext("dataSources/users.xml");
		JDBCNewUserDao dao = (JDBCNewUserDao) context.getBean("newUserDao");
		HttpSession thisSession = request.getSession();
		NewUserDetails userObject;
		userObject = (NewUserDetails) thisSession.getAttribute("userInsession");
		if (userObject == null) {
			userObject = new NewUserDetails("GuestUser", "noPassword", true);
			request.getSession().setAttribute("userInsession", userObject);
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
		shortUrl = dao.getShortUrl(longUrl);
		if (shortUrl.equals("")) {
			shortUrl = "http://" + request.getServerName() + ":" + request.getLocalPort() + request.getContextPath()
					+ "/" + "Short/" + new BigInteger(30, randomString).toString(32);
			if (!dao.insertLongAndShortURL(userObject.getUsername(),longUrl, shortUrl,0)) {
				ShowErrorPageUtil.redirectToErrorPage(request, response, "welcome.jsp",
						ErrorAndMessages.URLSHORTNERUNAVAILABLE);
				return;
			}
		}
		if (userObject.getIsGuestUser().equals(Boolean.FALSE)) {
			request.setAttribute("longUrl", longUrl);
			request.setAttribute("shortUrl", shortUrl);
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
