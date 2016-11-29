<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="com.fullerton.edu.cpsc.cpsc476.pojo.URL"%>
<html>
<head>
<%@include file="includes/assets.jsp"%>
<%@ page import="com.fullerton.edu.cpsc.cpsc476.pojo.NewUserDetails"%>
<%@ page import="com.fullerton.edu.cpsc.cpsc476.Util.ErrorAndMessages"%>
<%@ page import="com.fullerton.edu.cpsc.cpsc476.Util.ShowErrorPageUtil"%>
<%
	NewUserDetails thisUser = (NewUserDetails) session.getAttribute("userInsession");
	String userName = "";

	if (thisUser == null || thisUser.getUsername().equals(null) || thisUser.getUsername().equals("")) {
		session.invalidate();
		ShowErrorPageUtil.redirectToErrorPage(request, response, "signUp.jsp",
				ErrorAndMessages.PRIVATEPAGEACCESSERROR);
		return;
	} else {
		userName = thisUser.getUsername();
	}
	ArrayList<URL> UrlMap = (ArrayList) request.getAttribute("URLlist");
	Iterator<URL> url = UrlMap.iterator();
%>
</head>
<body>
	<h3>
		Welcome ---->>>><%=userName%></h3>
	<table>
		<thead>
			<tr>
				<th data-field="longUrl">Long Url</th>
				<th data-field="ShortUrl">Short Url</th>
				<th data-field="urlCount">Hits</th>
			</tr>
		</thead>

		<tbody>
			<%
				while (url.hasNext()) {
					URL key = (URL)url.next();
			%>
			<tr>
				<td><%=key.getLongName()%></td>
				<td><%=key.getShortName()%></td>				
				<td><%=key.getHits()%></td>				
				<%
					}
				%>
			
		</tbody>
	</table>

	<div class="row col s4">
		<form action="LogOut" method="post">
			<div class="input-field col s6 right">
				<input type="submit" value="Logout">
			</div>
		</form>
	</div>
</body>
</html>