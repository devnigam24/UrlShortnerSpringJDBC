<html>
<head>
<%@include file="includes/assets.jsp"%>
</head>
<body>
	<%
		String username = (String) request.getAttribute("username");
		String errorMessage = (String) request.getAttribute("errorMessage");
		String passwordDonotMatch = (String) request.getAttribute("passwordDonotMatch");
	%>
	<fieldset>
		<%
			if (errorMessage != null) {
		%>
		<h3 style="color: orangered; text-align: center;"><%=errorMessage%></h3>
		<%
			}
		%>
		<legend align="left">Signup</legend>
		<div class="row">
			<div class="col s8">
				<form action="SignUpServlet" method="post">
					<div class="row">
						<div class="input-field col s6">
							<input name="username" <%if (username != null){%>
								value=<%=username%><%} %> id="username" type="text" class="validate">
							<label for="username">Username</label>
						</div>
					</div>
					<div class="row">
						<div class="input-field col s6">
							<input id="password" name="password" type="password"
								class="validate"> <label for="password">Password</label>
						</div>
						<div class="col s4">
							<%
								if (passwordDonotMatch == "true") {
							%><h6>password not a match</h6>
							<%
								}
							%>
						</div>
					</div>
					<div class="row">
						<div class="input-field col s6">
							<input id="cpassword" name="cpassword" type="password"
								class="validate"> <label for="cpassword">Confirm
								Password</label>
						</div>
					</div>
					<div class="row">
						<div class="input-field col s6">
							<input type="submit" value="Signup">
						</div>
					</div>
				</form>
			</div>


			<div class="row col s4">
				<form action="LoginServlet" method="post">
					<div class="input-field col s10">
						<input id="loginName" name="loginName" type="text"> <label
							for="loginName">Username</label>
					</div>
					<div class="input-field col s10">
						<input id="loginPwd" name="loginPwd" type="password"> <label
							for="loginPwd">Password</label>
					</div>
					<div class="row">
						<div class="input-field col s6">
							<input type="submit" value="Login">
						</div>
					</div>
				</form>

				<br>
				<br>
				<br>
				<div class="row">
					<div class="input-field col s6">
						<input type="button" value="Guest User"
							onclick="window.location.href='publicUrlShortner.jsp'">
					</div>
				</div>
			</div>


		</div>
	</fieldset>
</body>
</html>
