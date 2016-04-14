<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<script src="js/jquery.js"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<script src="js/formFunctions.js"></script>
	
	 <link href="css/bootstrap.min.css" rel="stylesheet">
	 <link href="css/AddImmunization.css" rel="stylesheet">
	
	
	<base target="_self" />
	
<title>Add to your History</title>
</head>
<base target="_self">
<body class ="addIm">
<c:if test="${status!=null}">
<script>
$( document ).ready(function() {
	$('#successImmune').modal('show');
});
</script>
</c:if>
<c:if test="${error!=null}">
<script>
$( document ).ready(function() {
	$('#errorDate').modal('show');
});
</script>
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
<form action="AddImmune" method="post" class="form-horizontal">
<table class="table table-hover">
<tr>
<td>Vaccine Name</td>
<td><select name="Vaccine" class="form-control">
<option value="DTaP">DTaP</option>
<option value="HepB" disabled="disabled">Hepatitis-B</option>
<option value="TDap">TDaP</option>
<option value="Rotavirus1">Rotavirus-1</option>
<option value="Rotavirus5">Rotavirus-5</option>
<option value="Hib">Haemophilus Influenza</option>
<option value="PCV13">PCV13</option>
</select></td>
<td>Dose</td>
<td>
<select name="Dose" class="form-control">
<option value="1">1</option>
<option value="2">2</option>
<option value="3">3</option>
<option value="4">4</option>
<option value="5">5</option>
</select>
</td>
<td>Date of Administration</td>
<td><input type="date" class="form-control" name="DateOfAdministration" required max="01-01-1990" min= "03-10-2015"></td>
<td><input type="submit" class="btn btn-success" name="addImmunizations" value="Save"></td></tr>
</table>
</form>
</div>
</div>
</nav>
</div>   		
    
<!-- <div class="vContainer">
<form action="AddImmune" method="post" class="form-horizontal">

<h1>&nbsp; Add you previous Vaccination History</h1>

<table class="table table-striped table-hover" style="border: 2px solid black;">
<tr>
<td>Vaccine Name</td>
<td><select name="Vaccine" class="form-control">
<option value="DTaP">DTaP</option>
<option value="HepB" disabled="disabled">Hepatitis-B</option>
<option value="TDap">TDaP</option>
<option value="Rotavirus1">Rotavirus-1</option>
<option value="Rotavirus5">Rotavirus-5</option>
<option value="Hib">Haemophilus Influenza</option>
<option value="PCV13">PCV13</option>
</select></td>
</tr>
<tr>
<td>Dose</td>
<td>
<select name="Dose" class="form-control">
<option value="1">1</option>
<option value="2">2</option>
<option value="3">3</option>
<option value="4">4</option>
<option value="5">5</option>
</select>
</td>
</tr>
<tr>
<td>Date of Administration</td>
<td><input type="date" class="form-control" name="DateOfAdministration" required max="01-01-1990" min= "03-10-2015"></td>
</tr>
<tr>
<td><input type="submit" class="btn btn-success" name="addImmunizations" value="Add Immunizations"></td>
<td><a class="btn btn-success" href="Forecast.jsp">Get Forecast</a></td>
</tr>
</table>
<input type ="hidden" value="1" name="status">
</form>
</div> -->
<div class="modal fade" id="successImmune" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				  <div class="modal-dialog">
				    <div class="modal-content">
				      <div class="modal-header">
				        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				        <h4 class="modal-title" id="myModalLabel">Success!</h4>
				      </div>
				      <div class="modal-body">
				        You have successfully inserted the immunization details.
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-warning" data-dismiss="modal">Ok</button>
				      </div>
				    </div><!-- /.modal-content -->
				  </div><!-- /.modal-dialog -->
				</div><!-- /.modal -->
<div class="modal fade" id="errorDate" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				  <div class="modal-dialog">
				    <div class="modal-content">
				      <div class="modal-header">
				        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				        <h4 class="modal-title" id="myModalLabel">Alert!</h4>
				      </div>
				      <div class="modal-body">
				        Incorrect dose or invalid date.
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-warning" data-dismiss="modal">Ok</button>
				      </div>
				    </div><!-- /.modal-content -->
				  </div><!-- /.modal-dialog -->
				</div><!-- /.modal -->


<script src="js/bootstrap.min.js"></script>

</body>
</html>