package com.fullerton.edu.cpsc.cpsc476.Util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowErrorPageUtil extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void reditectToErrorPage() {

	}

	public static void redirectToErrorPage(HttpServletRequest req, HttpServletResponse res, String errorMessage) {
		try {
			req.setAttribute("errorMessage", errorMessage);
			req.getRequestDispatcher("error.jsp").forward(req, res);
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ServletException se) {
			se.printStackTrace();
		}
	}

	public static void redirectToErrorPage(HttpServletRequest req, HttpServletResponse res, String pageName,
			String errorMessage) {
		try {
			req.setAttribute("errorMessage", errorMessage);
			req.getRequestDispatcher(pageName).forward(req, res);
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ServletException se) {
			se.printStackTrace();
		}
	}

}
