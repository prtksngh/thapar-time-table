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

<title>Day Slot Wise Timetable</title>

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
						<h1 class="page-header">Day Slot Wise Time Table</h1>

					</div>
				</div>
				<!-- /.row -->

			</div>
			<!-- /.container-fluid -->
			<div class="form-group" style="background-color: #FFFFFF">
				<label>Select Day</label> <select class="form-control" id="dayid"
					name="day">
					<%
						for (Day d : Day.values()) {
					%>
					<option value=<%=d.id%>><%=d%></option>
					<%
						}
					%>
				</select>

				<div class="form-group" style="background-color: #FFFFFF">
					<label>Select Time-Slot</label> <select class="form-control"
						id="slot">
						<%
							ArrayList<Timeslots> timeSlotList = (ArrayList<Timeslots>) session
									.getAttribute("timeSlotList");

							for (Timeslots t : timeSlotList) {
						%>
						<option value=<%=t.getId()%>><%=t.getStart_time() + ":00" + "-" + t.getEnd_time()
						+ ":00"%></option>
						<%
							}
						%>

					</select> <input type="button" id="display" value="OK"
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
			<script src="js/DaySlotTT.js"></script>
</body>

</html>
