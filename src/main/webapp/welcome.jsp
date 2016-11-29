<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Home</title>
<%@include file="includes/assets.jsp"%>
<%@ page import="com.fullerton.edu.cpsc.cpsc476.pojo.NewUserDetails"%>
<%@ page import="com.fullerton.edu.cpsc.cpsc476.Util.ErrorAndMessages"%>
<%@ page import="com.fullerton.edu.cpsc.cpsc476.Util.ShowErrorPageUtil"%>
<%! 
	
%>
<%
	String userName = "";
	String longUrl = (String)request.getAttribute("longUrl");
	String shortUrl = (String)request.getAttribute("shortUrl");
	String errorMessage = (String) request.getAttribute("errorMessage");
	String infoMessages = (String) request.getAttribute("MessagesInfo");
	
	NewUserDetails thisUser = (NewUserDetails)session.getAttribute("userInsession");

	if(thisUser == null || thisUser.getUsername().equals(null) || thisUser.getUsername().equals("")){
		session.invalidate();
		ShowErrorPageUtil.redirectToErrorPage(request, response, "signUp.jsp", ErrorAndMessages.PRIVATEPAGEACCESSERROR);
		return;
	}else{
		userName = thisUser.getUsername();
	}
%>
</head>
<body>
	<%
			if (errorMessage != null) {
		%>
	<h3 style="color: orangered; text-align: center;"><%=errorMessage%></h3>
	<%
			}
		%>
	<%
			if (infoMessages != null) {
		%>
	<h3 style="color: Green; text-align: center;"><%=infoMessages%></h3>
	<%
			}
		%>

	<h3>
		Welcome ---->>>><%=userName %></h3>

	<div class="row col s4">
		<form action="LogOut" method="post">
			<div class="input-field col s6 right">
				<input type="submit" value="Logout">
			</div>
		</form>
	</div>
	<div class="row col s4">
		<form action="UpdateUrlCountServlet" method="post">
			<input type="hidden" name="action" value="showPage" />
			<div class="input-field col s6 right">
				<input type="submit" value="View All Urls and its count">
			</div>
		</form>
	</div>
	<div class="row col s4">
		<form action="UrlShortnerServlet" method="post">
			<input type="hidden" name="pageName" value="welcome.jsp">
			<div class="input-field col s6">
				<input id="longUrl" name="longUrl" type="text"
					<%if (longUrl != null) %> value=<%=longUrl%>> <label
					for="longUrl">longUrl</label>
			</div>
			<div class="input-field col s4">
				<a id="shortUrl" href="<%=shortUrl %>"> <%if (shortUrl != null) %><%=shortUrl %></a>
			</div>
			<div class="input-field col s4">
				<div class="input-field col s6">
					<input type="submit" value="Short It">
				</div>
			</div>
		</form>
	</div>
	<div id="someID"></div>
</body>
</html>
