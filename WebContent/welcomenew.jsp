<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<script src="js/jquery-1.7.1.min.js"></script>
	
  	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Praemoneo</title>
    <script src="js/jquery-1.7.1.min.js"></script>
	
    <script src="js/formFunctions.js"></script>	
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/praemoneo.css" rel="stylesheet">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
	<script src="//netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
    <base target="_self" />
<title></title>
</head>
<base target="_self">
<body class="welcome">
  	<c:if test="${pwdChanged != null}">
       <script>
       		$(document).ready(function()//function to execute after the DOM is ready
    		{
       			$('#pwdChangedSuccessfully').modal('show');
    		});
        </script>
   </c:if>
   <!-- Modal -->
				<div class="modal fade" id="invalidPws" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				  <div class="modal-dialog">
				    <div class="modal-content">
				      <div class="modal-header">
				        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				        <h4 class="modal-title" id="myModalLabel">Success!</h4>
				      </div>
				      <div class="modal-body">
				        Password has been changed successfully.
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-warning" data-dismiss="modal">Ok</button>
				      </div>
				    </div><!-- /.modal-content -->
				  </div><!-- /.modal-dialog -->
				</div><!-- /.modal -->
	<!-- Modal -->
				<div class="modal fade" id="invalidPws" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				  <div class="modal-dialog">
				    <div class="modal-content">
				      <div class="modal-header">
				        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				        <h4 class="modal-title" id="myModalLabel">Alert!</h4>
				      </div>
				      <div class="modal-body">
				        Old and new passwords are same.
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-warning" data-dismiss="modal">Ok</button>
				      </div>
				    </div><!-- /.modal-content -->
				  </div><!-- /.modal-dialog -->
				</div><!-- /.modal -->
</body>
</html>