<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Health Parameter</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Bootstrap -->

 <link href="css/parameter.css" rel="stylesheet">
 
 <link href="css/bootstrap.min.css" rel="stylesheet">
 <link href="css/styleforParameter&Profile.css" rel="stylesheet">
 
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

 
</head>

<base target="_self" />
<body>
<c:if test="${parameter!=null}">
<script>
$( document ).ready(function() {
	$('#successParam').modal('show');
});
</script>
</c:if>

<div class="containerParameter" id="containerParameter">
<form method="post" action="AfterAddParameter">
<h1>&nbsp; Please verify if you have any of the following health conditions:</h1>
<table class="table table-striped table-hover" style="border:2px solid black;">

<c:forEach items="${listparameter}" var="object">
<tr><td>
<input type="checkbox" value="${object}" name="Parameter"><c:out value="${object}"></c:out></td></tr>
</c:forEach> 
<tr><td>
<input type="submit" value="Save" class="btn btn-success" name="addParameters"></td></tr>
</table>
</form>
</div>
<div class="modal fade" id="successParam" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				  <div class="modal-dialog">
				    <div class="modal-content">
				      <div class="modal-header">
				        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				        <h4 class="modal-title" id="myModalLabel">Success!</h4>
				      </div>
				      <div class="modal-body">
				        You have successfully added your parameters.
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