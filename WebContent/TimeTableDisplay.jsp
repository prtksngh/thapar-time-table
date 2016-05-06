<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>SB Admin - Bootstrap Admin Template</title>

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

	<div id="wrapper">
		<%@ include file="Header.jsp"%>
		<%@ include file="Navbar.jsp"%>

		<div id="page-wrapper">
   <div class="container-fluid">

                <!-- Page Heading -->
                <div class="row" style="background-color:#FFFFFF">
                    <div class="col-lg-12">
                        <h1 class="page-header">
                            Time Tables
                        </h1>
                        <ol class="breadcrumb">
                            <li>
                                <i class="fa fa-dashboard"></i>  <a href="index.html">Dashboard</a>
                            </li>
                            <li class="active">
                                <i class="fa fa-table"></i> Tables
                            </li>
                        </ol>
                    </div>
                </div>
                <!-- /.row -->

                <div class="row" style="background-color:#FFFFFF">
                    <div >
                        
                        <div >
                            <table class="table table-bordered table-hover">
                                <thead>
                                    <tr>
                                        <div id="Day"><th>Day</th></div>
                                       
                                        <div id="8-9"><th>8-9</th></div>
                                        <div id="9-10"><th>9-10</th></div>
                                        
                                        <div id="10-11"><th>10-11</th></div>
                                        <div id="11-12"><th>11-12</th></div>
                                        <div id="12-1"><th>12-1</th></div>
                                        <div id="1-2"><th>1-2</th></div>
                                        <div id="2-3"><th>2-3</th></div>
                                        <div id="3-4"> <th>3-4</th></div>
                                        <div id="4-5"><th>4-5</th></div>
                                        <div id="5-6"><th>5-6</th></div>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <div id=""><th>Monday</th></div>
                                        <div id=""><th></th></div>
                                        <div id=""><th></th></div>
                                        <div id=""><th></th></div>
                                        
                                        <div id=""><th>UCS803 <br> L <br> B209</th></div>
                                        <div id=""><th>UEI623 <br> 2EE1(T) <br> E201</th></div>
                                        <div id=""><th></th></div>
                                        <div id=""><th></th></div>
                                        <div id=""><th></th></div>
                                        <div id=""><th></th></div>
                                        <div id=""><th></th></div>
                                    </tr>
                                    <tr>
                                        <div id=""><th>Tuesday</th></div>
                                        <div id=""><th></th></div>
                                        <div id=""><th></th></div>
                                        <div id=""><th></th></div>
                                        
                                        <div id=""><th>UCS803 <br> L <br> B209</th></div>
                                        <div id=""><th></th></div>
                                        <div id=""><th></th></div>
                                        <div id=""><th></th></div>
                                        <div id=""><th></th></div>
                                        <div id=""><th>UEI623 <br>3EIC <br> UG-CSI</th></div>
                                        <div id=""><th>UEI623 <br>3EIC <br> UG-CSI1</th></div>
                                    </tr>
                                    <tr>
                                        <div id=""><th>Wednesday</th></div>
                                        <div id=""><th></th></div>
                                        <div id=""><th></th></div>
                                        <div id=""><th></th></div>
                                        
                                        <div id=""><th>DEPTT SEMINAR</th></div>
                                        <div id=""><th>DEPTT SEMINAR</th></div>
                                        <div id=""><th>UCS803 <br> T <br>B209</th></div>
                                        <div id=""><th></th></div>
                                        <div id=""><th>UEI623 <br>2EE2 <br>UG-CS2</th></div>
                                        <div id=""><th>UEI623 <br>2EE2 <br>UG-CS2</th></div>
                                        <div id=""><th></th></div>
                                    </tr>
                                    <tr>
                                        <div id=""><th>Thrusday</th></div>
                                        <div id=""><th></th></div>
                                        <div id=""><th></th></div>
                                        <div id=""><th>UEI623 <br>2EEI <br>UG-CS2</th></div>
                                        
                                        <div id=""><th>UCS803 <br> L <br> B209</th></div>
                                        <div id=""><th></th></div>
                                        <div id=""><th></th></div>
                                        <div id=""><th></th></div>
                                        <div id=""><th></th></div>
                                        <div id=""><th></th></div>
                                        <div id=""><th></th></div>
                                    </tr>
                                    <tr>
                                        <div id=""><th>Friday</th></div>
                                        <div id=""><th></th></div>
                                        <div id=""><th></th></div>
                                        <div id=""><th></th></div>
                                        
                                        <div id=""><th></th></div>
                                        <div id=""><th></th></div>
                                        <div id=""><th></th></div>
                                        <div id=""><th></th></div>
                                        <div id=""><th></th></div>
                                        <div id=""><th></th></div>
                                        <div id=""><th></th></div>
                                    </tr>
                                    
                                </tbody>
                            </table>
                        </div>
                    </div>
                   
                <!-- /.row -->

                
                    
                <!-- /.row -->

                
                    
                </div>
                <!-- /.row -->

            </div>
            <!-- /.container-fluid -->
            
		</div>
		<!-- /#page-wrapper -->

	</div>
	<!-- /#wrapper -->

	<!-- jQuery -->
	<script src="js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>

</body>

</html>
