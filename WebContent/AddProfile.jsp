<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sign Up</title>
<script src="js/jquery.js"></script>
<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  
  <link href="css/bootstrap.min.css" rel="stylesheet">
  <link href="css/profile.css" rel="stylesheet">
  <script>
  $(document).ready(function() {
		$("#datepicker").datepicker({maxDate: "+0d",minDate:"-20Y"});
	});
 </script>
</head>
<body>
<h1>&nbsp; Add Your Profile Details</h1>
<c:if test="${addProfile!= null}">
   <script>
   
   $( document ).ready(function() {
		$('#alreadyPresent').modal('show');
	});
   window.location='home.jsp'; </script>    	        	
</c:if>
<c:if test="${profileError!= null}">
<script>
   
$( document ).ready(function() {
	$('#errorAdd').modal('show');
});
</script>    	        	
</c:if>
<c:if test="${profileSuccess!= null}">
<script>
$(document).ready(function()//function to execute after the DOM is ready
{
 $('#success').modal('show');
 });
</script>    	        	
</c:if>
<div class="containerProfile">
<form action="AddProfile" method="post" id="demographics">
<table class="table table-striped table-hover" style="border: 2px solid black;">
<tr><td>
<h4>First Name:</h4></td><td><input class="form-control" type="text" name="fname" pattern="[A-Za-z]+" required autofocus id="fname" title="Name should contain only letters"/>
</td></tr>
<tr><td>
<h4>Middle Name:</h4></td><td><input class="form-control" type="text" name="mname" id="mname" pattern="[A-Za-z]+" title="Name should contain only letters" />
</td></tr>
<tr><td>
<h4>Last Name:</h4></td><td><input class="form-control" type="text" name="lname" pattern="[A-Za-z]+" required id="lname" title="Name should contain only letters"/>
</td></tr>
<tr><td>
<h4>Date Of Birth:</h4></td><td><input type="text" id="datepicker" name="dob" required />
</td></tr>
<tr><td>
<h4>Gender :</h4></td>
<td><input type="radio" name="gender" value="m" />Male <input type="radio" name="gender" value="f" checked="checked"/>Female </td>    
</tr>
<tr><td>
<h4>Email ID:</h4></td><td><input class="form-control" type="email" name="email" id="email" required title="Enter a valid email address" placeholder="myexample@cerner.com"/>
</td></tr>
<tr><td>
<h4>Contact number:</h4></td><td><input type="text" class="form-control" pattern="[0-9]{10}" maxlength=10 required title="Enter 10 digit contact number" name="contactnum"/>                                                                                                              


</td></tr>
<tr><td>
<h4>Weight(in pounds):</h4></td><td><input class="form-control" type="number" name="weight" min=5 step="any" title="Weight must contain only numbers" required/>
</td></tr>
<tr><td>
<h4>Present Address:</h4></td><td><textarea class="form-control" form="demographics" name="address" rows="4" maxlength="200" cols="25" required></textarea>
</td></tr>
<tr><td>
<input type="hidden" name="addOredit" value="add">
<button type="submit" name="ok" class="btn btn-success">Save</button></td>
<td>
<input type="reset" name="reset" value="Reset" class="btn btn-success"></td></tr>
</table>
</form>
</div>
<div class="modal fade" id="alreadyPresent" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				  <div class="modal-dialog">
				    <div class="modal-content">
				      <div class="modal-header">
				        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				        <h4 class="modal-title" id="myModalLabel">Success!</h4>
				      </div>
				      <div class="modal-body">
				        Profile details already exist.
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-warning" data-dismiss="modal">Ok</button>
				      </div>
				    </div><!-- /.modal-content -->
				  </div><!-- /.modal-dialog -->
				</div><!-- /.modal -->
				
<div class="modal fade" id="errorAdd" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				  <div class="modal-dialog">
				    <div class="modal-content">
				      <div class="modal-header">
				        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				        <h4 class="modal-title" id="myModalLabel">Success!</h4>
				      </div>
				      <div class="modal-body">
				        Could not add demographics succesfully. Please try again.
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-warning" data-dismiss="modal">Ok</button>
				      </div>
				    </div><!-- /.modal-content -->
				  </div><!-- /.modal-dialog -->
				</div><!-- /.modal -->
<div class="modal fade" id="success" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				  <div class="modal-dialog">
				    <div class="modal-content">
				      <div class="modal-header">
				        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				        <h4 class="modal-title" id="myModalLabel">Alert <span class="glyphicon glyphicon-ok"></span></h4>
				      </div>
				      <div class="modal-body">
				        You have successfully added your demographics.
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-success" data-dismiss="modal">Ok</button>
				      </div>
				    </div><!-- /.modal-content -->
				  </div><!-- /.modal-dialog -->
				</div><!-- /.modal -->
<script src="js/bootstrap.min.js">
</script>
</body>
</html>