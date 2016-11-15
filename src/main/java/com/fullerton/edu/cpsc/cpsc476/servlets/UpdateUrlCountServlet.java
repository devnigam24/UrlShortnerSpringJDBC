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
 * Servlet implementation class UpdateUrlCountServlet
 */
public class UpdateUrlCountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
			HttpSession thisSession = request.getSession();
			NewUserDetails userObject = (NewUserDetails) thisSession.getAttribute("userInsession");
			if (request.getParameter("action").trim().equals("incrementCount")) {
				String shortUrl;
				int urlCount = 0;

				if (request.getParameter("urlClicked") != null && request.getParameter("urlClicked") != "") {
					shortUrl = request.getParameter("urlClicked");
				} else {
					ShowErrorPageUtil.redirectToErrorPage(request, response, "welcome.jsp",
							ErrorAndMessages.SERVERDOWN);
					return;
				}
				/*if (userObject.getUrlShornerMap().containsValue(shortUrl)) {
					urlCount = userObject.getUrlShornerUrlCountMap().get(shortUrl);
				}
				userObject.getUrlShornerUrlCountMap().put(shortUrl, ++urlCount);*/
			} else {
				//request.setAttribute("UserUrls", userObject.getUrlShornerMap());
				//request.setAttribute("userUrlsCount", userObject.getUrlShornerUrlCountMap());
				//request.getRequestDispatcher("UrlCounts.jsp").forward(request, response);
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}

}
