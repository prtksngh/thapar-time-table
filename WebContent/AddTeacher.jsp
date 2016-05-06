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

<title>Add Teacher</title>

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
	%>

	<div id="wrapper">
		<%@ include file="Header.jsp"%>
		<%@ include file="adminnavbar.jsp"%>

		<div id="page-wrapper">

			<div class="container-fluid">

				<!-- Page Heading -->
				<div class="row" style="background-color: #FFFFFF">
					<div class="col-lg-12">
						<h1 class="page-header">Add Teacher</h1>

					</div>
				</div>
				<!-- /.row -->

			</div>
			<!-- /.container-fluid -->
			<form action="AddTeacher" method="post">
				<div class="form-group" style="background-color: #FFFFFF">
					<%
						String msg = (String)request.getAttribute("msg");
						if (msg != null) {
							if (msg.equals("success")) {
					%><p style="color: green">Entry Successful</p>
					<%
						} else {
					%>
					<p style="color: red">Entry Failed</p>
					<%
						}
						}
					%>

					<label>Teacher Id</label> <input name="tid"type="text" class="form-control"
						placeholder="TeacherId"></input> <label>Teacher Name</label> <input name="tname"
						type="text" class="form-control" placeholder="Teacher Name"></input>

					<br>
					<div style="margin-left: 500px;">
						<button type="submit" class="btn btn-success">GO</button>
					</div>
				</div>
			</form>

			<!-- /#page-wrapper -->

		</div>
		<!-- /#wrapper -->

		<!-- jQuery -->
		<script src="js/jquery.js"></script>

		<!-- Bootstrap Core JavaScript -->
		<script src="js/bootstrap.min.js"></script>
</body>

</html>
