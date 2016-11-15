package com.fullerton.edu.cpsc.cpsc476.servlets;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fullerton.edu.cpsc.cpsc476.Util.ErrorAndMessages;
import com.fullerton.edu.cpsc.cpsc476.Util.ShowErrorPageUtil;

public class LogOut extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession thisSession = request.getSession();
		thisSession.invalidate();
		ShowErrorPageUtil.redirectToErrorPage(request, response, "signUp.jsp", ErrorAndMessages.LOGOUTMESSAGE);
		return;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		doGet(request, response);
	}
}
