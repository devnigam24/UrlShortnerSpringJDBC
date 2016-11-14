package com.fullerton.edu.cpsc.cpsc476.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fullerton.edu.cpsc.cpsc476.Util.ErrorAndMessages;
import com.fullerton.edu.cpsc.cpsc476.Util.ShowErrorPageUtil;
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
		ShowErrorPageUtil.redirectToErrorPage(request, response, ErrorAndMessages.inforCompromised);
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
			ShowErrorPageUtil.redirectToErrorPage(request, response, "signUp.jsp", ErrorAndMessages.usernameNull);
			return;
		}
		if (request.getParameter("loginPwd") != "" && request.getParameter("loginPwd") != null) {
			password = request.getParameter("loginPwd");
		} else {
			ShowErrorPageUtil.redirectToErrorPage(request, response, "signUp.jsp", ErrorAndMessages.passwordNull);
			return;
		}
		if (checkLoginCredentials(password)) {
			try {
				HttpSession thisSession = request.getSession();
				NewUserDetails newUser = new NewUserDetails(userName, "abc@xyz.com", password, false);
				thisSession.setAttribute("userInsession", newUser);
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
					ErrorAndMessages.loginCredentialMisMatch);
			return;
		}
	}

	private Boolean checkLoginCredentials(String password) {
		if (password.equals("CorrectPassword"))
			return true;
		else
			return false;
	}

}
