<!DOCTYPE html>
<%@page import="java.util.ArrayList"%>
<%@page import="com.timetable.DataModels.*"%>

<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Faculty TimeTable</title>

<!-- Bootstrap Core CSS -->
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="css/userhome.css" rel="stylesheet">

<!-- Morris Charts CSS -->
<link href="bootstrap/css/plugins/morris.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="font-awesome/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body>
<% if(session.getAttribute("tid") == null){
    response.sendRedirect("ErrorPage.jsp");
}
String role=(String)session.getAttribute("role");
%>

	<div id="wrapper">
	<%@ include file="Header.jsp"%>
		<%
			if (role.equals("admin")) {
		%>
		<%@ include file="adminnavbar.jsp"%>
		<%
			} else {
		%>
		<%@ include file="Navbar.jsp"%>
		<%
			}
		%>
		<div id="page-wrapper">


			<div class="container-fluid">

				<!-- Page Heading -->
				<div class="row" style="background-color: #FFFFFF">
					<div class="col-lg-12">
						<h1 class="page-header">Faculty Time Table</h1>

					</div>
				</div>
				<!-- /.row -->

			</div>
			<!-- /.container-fluid -->
			<div class="form-group" style="background-color: #FFFFFF">
				<label>Select Faculty</label> <select class="form-control"
					id="faculty" name="teacher">
					<%
						ArrayList<Teacher> teacherList = (ArrayList<Teacher>) session
								.getAttribute("teacherList");
						for (Teacher t : teacherList) {
					%>
					<option value=<%=t.getId()%>><%=t.getName() + "(" + t.getId() + ")"%></option>
					<%
						}
					%>

				</select> <input id="display" type="submit" value="OK"
					class="btn btn-default" style="margin: 0 auto; display: table">
			
			<div id="content" style="background-color: #FFFFFF"></div>
		</div>
		<!-- /#page-wrapper -->

	</div>
	<!-- /#wrapper -->

	<!-- jQuery -->
	<script src="js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>
	<script src="js/FacultyTT.js"></script>

</body>

</html>
