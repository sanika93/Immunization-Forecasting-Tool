<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<script src="js/jquery.js"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<script src="js/formFunctions.js"></script>
	<!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/AddImmunization.css" rel="stylesheet">
	
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
<title>Edit your details here</title>
</head>
<body>
<body class ="register">
<c:if test="${successImmune!=null}">
<script>

$( document ).ready(function() {
	$('#successImmune').modal('show');
});
</script>
</c:if>
<c:if test="${error!=null}">
<script>

$( document ).ready(function() {
	$('#error').modal('show');
});
</script>
</c:if>
<%String vaccineName=session.getAttribute("Vaccine").toString();
  int dose=(Integer)session.getAttribute("Dose");%>
<div class="vContainer">
<form action="AddImmune" method="post" class="form-horizontal">
 <h1>&nbsp;Add your details</h1>
<table class="table table-hover table-striped " style="border: 2px solid black;">
<tr>
<td>Vaccine Name</td><td></td>
<td><%=vaccineName %></td>
</tr>

<tr>
<td>Dose Number</td><td></td>
<td><%=dose %></td>
</tr>

<tr>
<td>Date of Administration</td><td></td><td><input type="date" class="textColor" name="DateOfAdministration" required max="01-01-1990" min= "03-10-2015"></td></tr>


<tr><td><input type="submit" class="btn btn-success" name = " "value="Save"></td>
<td></td>

<td><a class="btn btn-success" href="View.jsp">View</a></td></tr></table>
<input type="hidden" value="0" name="status">
</form>
</div>
<div class="modal fade" id="successImmune" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				  <div class="modal-dialog">
				    <div class="modal-content">
				      <div class="modal-header">
				        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				        <h4 class="modal-title" id="myModalLabel">Success!</h4>
				      </div>
				      <div class="modal-body">
				        You have successfully inserted the updated immunization details.
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-warning" data-dismiss="modal">Ok</button>
				      </div>
				    </div><!-- /.modal-content -->
				  </div><!-- /.modal-dialog -->
				</div><!-- /.modal -->
<div class="modal fade" id="error" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				  <div class="modal-dialog">
				    <div class="modal-content">
				      <div class="modal-header">
				        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				        <h4 class="modal-title" id="myModalLabel">Success!</h4>
				      </div>
				      <div class="modal-body">
				        Record does not exist. Please enter correct dose or correct date.
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