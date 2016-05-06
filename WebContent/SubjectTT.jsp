<!DOCTYPE html>
<html lang="en">
<%@page import="java.util.ArrayList"%>
<%@page import="com.timetable.DataModels.*"%>

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Subject Wise TimeTable</title>

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
	<%
		if (session.getAttribute("tid") == null) {
			response.sendRedirect("ErrorPage.jsp");
		}
		String role = (String) session.getAttribute("role");
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
						<h1 class="page-header">Subject Wise Time Table</h1>

					</div>
				</div>
				<!-- /.row -->

			</div>
			<!-- /.container-fluid -->
			<div class="form-group" style="background-color: #FFFFFF">
				<label>Select Subject</label> <select class="form-control"
					name="subject" id="subjectSelector">
					<%
						ArrayList<Subject> subList = (ArrayList<Subject>) session
								.getAttribute("subjectList");

						for (Subject s : subList) {
					%>
					<option value=<%=s.getSubject_id()%>><%=s.getSubject_id() + ":" + s.getSubject_name()%></option>
					<%
						}
					%>

				</select> <input id="display" type="button" value="OK"
					class="btn btn-default" style="margin: 0 auto; display: table">

				<div id="content">
					<h1>Subject Information</h1>
					<h3 id="subName"></h3>
					<p id="lecCredit"></p>
					<p id="tutCredit"></p>
					<p id="pracCredit"></p>


					<table class="table table-bordered table-hover">
						<thead>
							<tr>
								<th>FACULTY</th>
								<th>LECTURE</th>
								<th>TUTORIAL</th>
								<th>PRACTICAL</th>
							</tr>
						</thead>
						<tbody id="infoBody">


						</tbody>
					</table>
					<div id="daywise"></div>

				</div>

			</div>
		</div>
		<!-- /#page-wrapper -->

	</div>
	<!-- /#wrapper -->

	<!-- jQuery -->
	<script src="js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>
	<script src="js/SubjectTT.js"></script>
</body>

</html>
