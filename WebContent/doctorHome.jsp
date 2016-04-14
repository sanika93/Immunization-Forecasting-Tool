<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
  <head>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  	<script src="js/jquery.js"></script>
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
  	<script src="js/formFunctions.js"></script>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Praemoneo</title>
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/praemoneo.css" rel="stylesheet"> 
<!-- 	<script src="js/logout.js"></script> -->
<script>
	$(document).ready(function(){  
		getAppointment();
	});
	</script>
    <base target="_self" />
  </head>
  <body>
  <div class="container">
    	<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
    	<div class="container-fluid">
    		<div class="navbar-header">
    			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse" >
		            <span class="sr-only">Toggle navigation</span>
		            <span class="icon-bar"></span>
		            <span class="icon-bar"></span>
		            <span class="icon-bar"></span>
          		</button>
          	<img src="images/Cernerlogo.png" height=49 width=190></img>
    		</div>
    		
    		<div class="navbar-collapse collapse">
    			<ul class="nav navbar-nav">  			
    				
    					<li><a href="welcome.jsp" target="doctorhome">Home <span class="glyphicon glyphicon-home"></span></a></li>
    				    				
    				<li class="dropdown">
    					<a href="#" class="dropdown-toggle" data-toggle="dropdown">View <span class="glyphicon glyphicon-eye-open"></span><b class="caret"></b></a>
    					<ul class="dropdown-menu">
    						<li><a href="ViewPatients.jsp" target="doctorhome">My Patients</a></li>
    						<li><a href="View.jsp" target="doctorhome">All Patients</a></li>
    					</ul>
    				</li> 
    				
    				<li><a id="viewAppointment" target="doctorhome">Appointments<span class="glyphicon glyphicon-eye-open"></span><b class="caret"></b></a>
    				
    				</li>
    				
    				
    				<li class="dropdown">
    					<a href="#" class="dropdown-toggle" data-toggle="dropdown" ><c:out value="${uName} "></c:out><span class="glyphicon glyphicon-user"></span><b class="caret"></b></a>
    					<ul class="dropdown-menu">
    						<li> 						
    						<li><a href="resetUserPw.jsp" role="button" target="doctorhome">Reset Password</a></li>
    						<li>
    						<a href="#logoutConfirmation" data-toggle="modal">Log Out</a></li>
    					</ul>   					
    				</li>
    			</ul>
    		</div>   			
		</div> 
		</nav>  			
    </div>
    <!-- Modal -->
				<div class="modal fade" id="logoutConfirmation" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				  <div class="modal-dialog">
				    <div class="modal-content">
				      <div class="modal-header">
				        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				        <h4 class="modal-title" id="logoutConfirmationTitle">Attention!</h4>
				      </div>
				      <div class="modal-body">
				        Are you sure you want to logout?
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-warning" data-dismiss="modal">No</button>
				        <a href="index.jsp" role="button" class="btn btn-success" id="logout">Yes</a>
				      </div>
				    </div><!-- /.modal-content -->
				  </div><!-- /.modal-dialog -->
				</div><!-- /.modal -->
    <div class="intro-block">
	<iframe class ="iframenew-block" id="doctorhome" name="doctorhome" height=650px src="welcome.jsp">
	</iframe>
	</div>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="js/bootstrap.min.js"></script>
</body>
</html>