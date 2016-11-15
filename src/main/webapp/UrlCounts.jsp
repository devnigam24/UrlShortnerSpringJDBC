<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Iterator"%>
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
	HashMap UrlMap = (HashMap) request.getAttribute("UserUrls");
	HashMap UrlCountsMap = (HashMap) request.getAttribute("userUrlsCount");
	Iterator<String> url = UrlMap.keySet().iterator();
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
					String key = (String) url.next();
			%>
			<tr>
				<td><%=key%></td>
				<td><%=UrlMap.get(key)%></td>
				<%Integer hitsCount = (Integer)UrlCountsMap.get(UrlMap.get(key)); %>
				<td><%if(hitsCount == null){hitsCount = 0;}%><%=hitsCount%></td>
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