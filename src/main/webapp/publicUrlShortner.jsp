<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Public page</title>
<%@include file="includes/assets.jsp"%>
<%@ page import="com.fullerton.edu.cpsc.cpsc476.pojo.NewUserDetails"%>
<%@ page import="com.fullerton.edu.cpsc.cpsc476.Util.ErrorAndMessages"%>
<%@ page import="com.fullerton.edu.cpsc.cpsc476.Util.ShowErrorPageUtil"%>
<%! 
	
%>
<%
	String longUrl = (String)request.getAttribute("longUrl");
	String shortUrl = (String)request.getAttribute("shortUrl");
	String errorMessage = (String) request.getAttribute("errorMessage");
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
	<h3>Welcome ---->>>>Guest User</h3>
	<div class="row col s4">
		<form action="UrlShortnerServlet" method="post">
			<input type="hidden" name="pageName" value="publicUrlShortner.jsp">
			<div class="input-field col s6">
				<input id="longUrl" name="longUrl" type="text"
					<%if (longUrl != null) %> value=<%=longUrl%>> <label
					for="longUrl">longUrl</label>
			</div>
			<div class="input-field col s4">
				<a id="shortUrl" onclick="updateClickCount(this)" href="#"> <%if (shortUrl != null) %><%=shortUrl %></a>
			</div>
			<div class="input-field col s4">
				<div class="input-field col s6">
					<input type="submit" value="Short It">
				</div>
			</div>
		</form>
	</div>
</body>
</html>