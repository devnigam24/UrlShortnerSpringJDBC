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

public class SignUpServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		ShowErrorPageUtil.redirectToErrorPage(req, res, ErrorAndMessages.INFORCOMPROMISED);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		String username = "";
		String password = "";
		String confirm = "";
		String email = "";

		if (req.getParameter("username") != null && req.getParameter("username") != "") {
			username = req.getParameter("username");
			req.setAttribute("username", username);
		} else {
			ShowErrorPageUtil.redirectToErrorPage(req, res, "signUp.jsp", ErrorAndMessages.USERNAMENULL);
			return;
		}
		if (req.getParameter("email") != null && req.getParameter("email") != "") {
			email = req.getParameter("email");
			req.setAttribute("email", email);
		} else {
			ShowErrorPageUtil.redirectToErrorPage(req, res, "signUp.jsp", ErrorAndMessages.EMAILNULL);
			return;
		}
		if (req.getParameter("password") != null && req.getParameter("password") != "") {
			password = req.getParameter("password");
		} else {
			ShowErrorPageUtil.redirectToErrorPage(req, res, "signUp.jsp", ErrorAndMessages.PASSWORDNULL);
			return;
		}
		if (req.getParameter("cpassword") != null && req.getParameter("cpassword") != "") {
			confirm = req.getParameter("cpassword");

		} else {
			ShowErrorPageUtil.redirectToErrorPage(req, res, "signUp.jsp", ErrorAndMessages.PASSWORDNULL);
			return;
		}
		if (password.equals(confirm)) {
			try {
				NewUserDetails newUser = new NewUserDetails(username, email, password,false);
				HttpSession session = req.getSession();
				session.setAttribute("userInsession", newUser);
				ApplicationContext context = new ClassPathXmlApplicationContext("users.xml");
				JDBCNewUserDao dao = (JDBCNewUserDao)context.getBean("newUserDao");
				if(dao.inserNewUserInDB(newUser)){
					req.getRequestDispatcher("welcome.jsp").forward(req, res);
				}else{
					ShowErrorPageUtil.redirectToErrorPage(req, res, "signUp.jsp", 
														ErrorAndMessages.DATABASEDOWNNOTRANSACTIONHAPPENED);
					return;
				}
			} catch (ServletException e) {
				ShowErrorPageUtil.redirectToErrorPage(req, res,"");
				return;
			} catch (IOException e) {
				ShowErrorPageUtil.redirectToErrorPage(req, res, e.getMessage());
				return;
			}
		} else {
			req.setAttribute("passwordDonotMatch", "true");
			try {
				req.getRequestDispatcher("signUp.jsp").forward(req, res);
			} catch (ServletException e) {
				System.out.println(e);
			} catch (IOException e) {
				System.out.println(e);
			}
		}
	}
}
