package com.fullerton.edu.cpsc.cpsc476.servlets;

import java.io.IOException;
import java.util.ArrayList;

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
import com.fullerton.edu.cpsc.cpsc476.pojo.URL;

/**
 * Servlet implementation class UpdateUrlCountServlet
 */
public class UpdateUrlCountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @throws IOException 
	 * @throws ServletException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			NewUserDetails userObject = (NewUserDetails) request.getSession().getAttribute("userInsession");
			ApplicationContext context = new ClassPathXmlApplicationContext("dataSources/users.xml");
			JDBCNewUserDao dao = (JDBCNewUserDao)context.getBean("newUserDao");
			ArrayList<URL> myList = dao.getAllUrls(userObject.getUsername());
			request.setAttribute("URLlist", myList);
			request.getRequestDispatcher("UrlCounts.jsp").forward(request, response);
	}

	/**
	 * @throws IOException 
	 * @throws ServletException 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
