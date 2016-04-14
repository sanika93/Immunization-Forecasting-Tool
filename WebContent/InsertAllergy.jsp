<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	
	<link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/allergy.css" rel="stylesheet">
  
</head>
<base target="_self">
<body class="insertallergy">
<c:if test="${success != null}">
<script>
$(document).ready(function()//function to execute after the DOM is ready
{
 	$('#detailsAdded').modal('show');
 }); 
</script>
</c:if>
<c:if test="${error != null}">
<script>
$(document).ready(function()//function to execute after the DOM is ready
{
 $('#detailsExist').modal('show');
 });
</script>    	        	
</c:if>

<h1>&nbsp;
    Select allergic reactions caused due to previous doses below.
 </h1> 
  <form action="AddAllergy" method="post">
<div class="containerNew">
<table class="table table-striped table-hover" style="border: 2px solid black;">
 <tr>
 <td>
  <label> Allergy </label>
  </td>
  <td>
  <select name="Select Allergy" class="form-control">
   <option value="Fever">Fever</option>
   <option value="Seizure">Seizure</option>
   <option value="BNSD">Brain or Nervous System Disorder</option>
  </select>
 </td>
 </tr>
 <tr> 
 <td>
  <label>Vaccine Name</label>
  </td>
  <td>
  <select name="Vaccine Name" class="form-control">
   <option value="DTaP">DTaP</option>
   <option value="Hep-B">Hep-B</option>

   <option value="Hib">Hib</option>

   <option value="Rota Virus">Rota Virus</option>

   <option value="Tdap">TdaP</option>

   
  </select>
 </td>
 </tr>
 <tr>
 <td> 
  <label>Dose</label>
 </td>
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
 <td>
 <label>Description</label>
 </td>
 <td>
  <input type="text" name="descrip" class="form-control">
 </td>
 </tr>
 <tr>
 <td>
 
 </td>
 <td>
 <input type="submit" class="btn btn-success" value="Add and Save">
 </td>
 </tr> 
</table> 
</div>
</form>

				
				<div class="modal fade" id="detailsExist" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				  <div class="modal-dialog">
				    <div class="modal-content">
				      <div class="modal-header">
				        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				        <h4 class="modal-title" id="myModalLabel">Alert <span class="glyphicon glyphicon-alert"></span></h4>
				      </div>
				      <div class="modal-body">
				        Details already exist. Please make sure the dose number is correct or consider checking your existing allergies.
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-warning" data-dismiss="modal">Ok</button>
				      </div>
				    </div><!-- /.modal-content -->
				  </div><!-- /.modal-dialog -->
				</div><!-- /.modal -->	
				
				
				<div class="modal fade" id="detailsAdded" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				  <div class="modal-dialog">
				    <div class="modal-content">
				      <div class="modal-header">
				        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				        <h4 class="modal-title" id="myModalLabel">Alert <span class="glyphicon glyphicon-ok"></span></h4>
				      </div>
				      <div class="modal-body">
				        Allergy details have been successfully added.
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-success" data-dismiss="modal">Ok</button>
				      </div>
				    </div><!-- /.modal-content -->
				  </div><!-- /.modal-dialog -->
				</div><!-- /.modal -->
 <script src="js/bootstrap.min.js"></script>	
</body>
</html>