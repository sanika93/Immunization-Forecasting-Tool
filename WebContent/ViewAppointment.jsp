<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Appointments</title>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- <script src="js/jquery.js"></script> -->
<script src="js/formFunctions.js"></script>
	
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<link href="css/viewImmunization.css" rel="stylesheet">
<link href="css/bootstrap.min.css" rel="stylesheet">
	 
<link rel="stylesheet" type="text/css" href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="//cdn.datatables.net/plug-ins/1.10.6/integration/bootstrap/3/dataTables.bootstrap.css">

<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<script type="text/javascript" src="//cdn.datatables.net/1.10.6/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="//cdn.datatables.net/plug-ins/1.10.6/integration/bootstrap/3/dataTables.bootstrap.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>

<script>
$(document).ready(function(){  
	
	$('#appointment').dataTable();
	$('#upcomingappointment').dataTable();
	
});
function onLoad()
{	
	
	document.getElementById("GetAppointments").submit();
	
	
}
</script>

</head>
<base target="_self">
<body>
<c:if test="${getdetailsapp==null}">
<body onload="javascript:onLoad()">
</c:if>
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
          	
    		</div>
<div class="navbar-collapse collapse">
<form action="GetDoctorAppointments" method="post" class="form-horizontal">
<table class="table table-hover table-striped">
<tr>
<td style="width:120px;"><label>Select Date</label></td>
<td><input type="date" name="searchDate" id="searchDate" class="form-control" style="width:250px;"></td>
<td><input type="submit" class="btn btn-success pull-right" name="search" id="search" value="Get Schedule"></td></tr>
</table>
</form>
</div>
</div>
</nav>
</div>
<div class="panel panel-primary custom-panel" style="padding-top:5%;">
		<div class="panel-heading">
	        <h3 class="panel-title">Appointments</h3>
	    </div>
	    <div class="panel-body">
	    <div class="bs-example">
				<ul class="nav nav-tabs nav-justified">
				  <li class="active"><a data-toggle="tab" href="#today">Todays Appointments</a></li>
				  <li><a data-toggle="tab" href="#upcoming">Upcoming Appointments</a></li>
				</ul>
				<div class="tab-content">
				
					<div style="width:95%; height:400px;margin-top:2%;margin-left:1%;" id="today" class="tab-pane fade in active">
					<div class="panel panel-default custom-panel" style="border:none;">
					<div class="panel-body custom-tab-pane">	
					<form action="getSchedule" id="GetAppointments" name="GetAppointments" method="post" >
					 <table class= "table table-hover" id="appointment">  
					<thead>  
					 <tr>
					 							 <th>Patient Name</th>
					 							 <th>Vaccine Name</th>
					  							 <th>Dose</th>
					  							 <th>Appointment Date</th>
					  							 <th>Appointment Time</th>
					 </tr>
					 </thead>
					  <tbody>
					 
					<c:forEach var="element" items="${AppointmentList}">  
					                             
					                                <tr>
					                                <td>${element.getPatientName()}</td>
					                                <td>${element.getVaccineName()}</td>
					                                <td>${element.getDose()}</td>
					                                <td>${element.getAppointmentDate()}</td>
					                                <td>${element.getAppointmentTime()}</td>
					                                </tr> 
					                           
					</c:forEach>
					</tbody>
					</table>
					</form>
					</div>
					</div>
					</div>
					
					<div style="width:95%; height:400px; margin-top:3%; margin-left:2%;" id="upcoming" class="tab-pane fade custom-tab">
					<form action="getSchedule" id="GetAppointments" name="GetAppointments" method="post" >
					 <table class= "table table-hover" id="upcomingappointment">  
					<thead>  
					 <tr>
					 							 <th>Patient Name</th>
					 							 <th>Vaccine Name</th>
					  							 <th>Dose</th>
					  							 <th>Appointment Date</th>
					  							 <th>Appointment Time</th>
					 </tr>
					 </thead>
					  <tbody>
					 
					<c:forEach var="element" items="${UpcomingAppointmentList}">  
					                             
					                                <tr>
					                                <td>${element.getPatientName()}</td>
					                                <td>${element.getVaccineName()}</td>
					                                <td>${element.getDose()}</td>
					                                <td>${element.getAppointmentDate()}</td>
					                                <td>${element.getAppointmentTime()}</td>
					                                </tr> 
					                           
					</c:forEach>
					</tbody>
					</table>
					</form>
					</div>
					
</div>			
</div>
</div>
</div>					
</body>
</html>