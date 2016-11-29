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
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		String userName = "";
		String password = "";

		if (request.getParameter("loginName") != "" && request.getParameter("loginName") != null) {
			userName = request.getParameter("loginName");
		} else {
			ShowErrorPageUtil.redirectToErrorPage(request, response, "signUp.jsp", ErrorAndMessages.USERNAMENULL);
			return;
		}
		if (request.getParameter("loginPwd") != "" && request.getParameter("loginPwd") != null) {
			password = request.getParameter("loginPwd");
		} else {
			ShowErrorPageUtil.redirectToErrorPage(request, response, "signUp.jsp", ErrorAndMessages.PASSWORDNULL);
			return;
		}
		if (checkLoginCredentials(userName,password)) {
			try {
				HttpSession thisSession = request.getSession();
				NewUserDetails newUser = this.getUserData(userName);
				thisSession.setAttribute("userInsession", newUser);
				request.setAttribute("MessagesInfo", "Log in Successfull");
				request.getRequestDispatcher("welcome.jsp").forward(request, response);
			} catch (ServletException e) {
				ShowErrorPageUtil.redirectToErrorPage(request, response, e.getMessage());
				return;
			} catch (IOException e) {
				ShowErrorPageUtil.redirectToErrorPage(request, response, e.getMessage());
				return;
			}
		} else {
			ShowErrorPageUtil.redirectToErrorPage(request, response, "signUp.jsp",
					ErrorAndMessages.LOGINCREDENTIALMISMATCH);
			return;
		}
	}

	private NewUserDetails getUserData(String userName) {
		ApplicationContext context = new ClassPathXmlApplicationContext("dataSources/users.xml");
		JDBCNewUserDao dao = (JDBCNewUserDao)context.getBean("newUserDao");
		return dao.getUserInfo(userName);
	}

	private Boolean checkLoginCredentials(String username, String password) {
		ApplicationContext context = new ClassPathXmlApplicationContext("dataSources/users.xml");
		JDBCNewUserDao dao = (JDBCNewUserDao)context.getBean("newUserDao");
		return dao.authenticateThisUser(username,password);
	}

}
