<%@ page errorPage="ErrorPage.jsp" %>
<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Time-Table Login</title>

<link rel="stylesheet" href="css/loginpage.css">

</head>

<body>
<html lang="en-US">
<head>

<meta charset="utf-8">

<title>Login</title>

<link rel="stylesheet"
	href="http://fonts.googleapis.com/css?family=Open+Sans:400,700">

<!--[if lt IE 9]>
  <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
 <![endif]-->

</head>

<body>

	<div class="container">

		<div id="login">
			<%
  String  server_msg=(String)request.getAttribute("msg");
  if(server_msg!=null){
    %>
			<p1 style="color:red;font-size:medium"><%=server_msg%></p1><br/><br/>
			<%}
 %>

			<form action="authenticate;" onsubmit="return validateForm();" method="post">

				<fieldset class="clearfix">

					<p>
						<span  class="fontawesome-user"></span><input id="userid" type="text"
							name="userid"  placeholder="Username">
					<p>
						<span  class="fontawesome-lock"></span><input id="password" type="password"
							name="userpass"  placeholder="Password">
					<p>
						<input class="submit" type="submit" value="Sign In">
					</p>

				</fieldset>

			</form>
			<%
  String msg=(String)request.getAttribute("err");
  if(msg!=null){
    %>
			<p1 style="color:red;font-size:medium">**<%=msg%></p1>
			<%}%>


		</div>
		<!-- end login -->

	</div>
<script src="js/jquery.js"></script>	
<script src="js/Login.js"></script>
</body>
</html>

</body>

</html>
