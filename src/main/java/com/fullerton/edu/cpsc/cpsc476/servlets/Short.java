package com.fullerton.edu.cpsc.cpsc476.servlets;

import java.io.IOException;
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
 * Servlet implementation class Short
 */
public class Short extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NewUserDetails userObject = (NewUserDetails)request.getSession().getAttribute("userInsession");
		if (userObject == null) {
			userObject = new NewUserDetails("GuestUser", "noPassword", true);
			request.getSession().setAttribute("userInsession", userObject);
		}
		StringBuffer shortUrlBuffer = request.getRequestURL();
		String shortUrl = shortUrlBuffer.toString().trim();
		ApplicationContext context = new ClassPathXmlApplicationContext("dataSources/users.xml");
		JDBCNewUserDao dao = (JDBCNewUserDao) context.getBean("newUserDao");
		String longUrl = dao.getLongUrl(shortUrl);
		dao.updateUrlHits(longUrl);
		if(!(longUrl.contains("http://") || longUrl.contains("https://"))){
			longUrl = "http://" + longUrl;
		}		
		request.getSession().setAttribute("longUrlToGo", longUrl);
		response.sendRedirect(request.getContextPath()+"/redirectToLongUrl.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
