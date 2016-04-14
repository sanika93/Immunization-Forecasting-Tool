<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>	
  	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Praemoneo</title>   
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/resetUserPass.css" rel="stylesheet">
	<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
	<script src="//netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
	<script src="js/formFunctions.js"></script>	
	<script>
	$(document).ready(function(){
		resetFn();	  
	});
	</script>
<title>Insert title here</title>
</head>
<base target="_self" />
<body class="bdyReset" id="bdyReset">
<div class="">
<h1> Reset your password </h1>
		<form id="rstPw" action="#">
			<div class="containerReset">
			<table class="table table-striped table-hover tblForm">
				<tbody>
					<tr>
						<td><p>Enter existing password</p></td>
						<td><p><input type="password" name="pw" id="pw" pattern=".{6,10}" required title="6 to 10 characters"/></p></td>
					</tr>
					<tr>
						<td><p>Enter new password</p></td>
						<td><p><input type="password" name="newPw" id="newPw" pattern=".{6,10}" required title="6 to 10 characters"/></p></td>
					</tr>
					<tr>
						<td><p>Re enter existing password</p></td>
						<td><p><input type="password" id="reTypPw" name="reTypPw" pattern=".{6,10}" required title="6 to 10 characters"/></p></td>
					</tr>
					<tr>
						<td><button type="button" class="btn btn-warning" id="clr" onClick="clrReset()">Clear <span class="glyphicon glyphicon-remove"></span></button></td>
						<td><button type="button" class="btn btn-success" id="rest" name="rst" id="rest">Reset Password <span class="glyphicon glyphicon-refresh "></span></button></td>
					</tr>
				</tbody>
			</table>				
			</div>
			<div class="bulletin" id="bulletin">
				<a id="cls"><span class="glyphicon glyphicon-trash close"></span></a>			
			</div>		
		</form>
		<!-- Modal -->
				<div class="modal fade" id="pwdMisMatch" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				  <div class="modal-dialog">
				    <div class="modal-content">
				      <div class="modal-header">
				        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				        <h4 class="modal-title" id="myModalLabel">Attention!</h4>
				      </div>
				      <div class="modal-body">
				        New Password and Retyped Password Don't Match. Please Make sure that the Password and Retyped Password match.
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-warning" data-dismiss="modal">Ok</button>
				      </div>
				    </div><!-- /.modal-content -->
				  </div><!-- /.modal-dialog -->
				</div><!-- /.modal -->
	</div>
	
	<!-- Modal -->
				<div class="modal fade" id="pwdWrong" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display:none;">
				  <div class="modal-dialog">
				    <div class="modal-content">
				      <div class="modal-header">
				        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				        <h4 class="modal-title" id="myModalLabel">Attention!</h4>
				      </div>
				      <div class="modal-body">
				        Original password was typed wrong. Please retype the passwords.
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-warning" data-dismiss="modal">Ok</button>
				      </div>
				    </div><!-- /.modal-content -->
				  </div><!-- /.modal-dialog -->
				</div><!-- /.modal -->
	  <!-- Modal -->
				<div class="modal fade" id="successPwdChanged" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
				<div class="modal fade" id="pwsSame" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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