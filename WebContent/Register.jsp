<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<script src="js/jquery.js"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<script src="js/formFunctions.js"></script>
	<script src="//code.jquery.com/jquery-1.10.2.js"></script>
	<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
	<!-- Bootstrap -->
	
    <link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/register.css" rel="stylesheet">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
<title>Welcome to the Registration Page</title>
<script>

function hide()
{
	
	var value=$('#typeList').val();
	
	if(value==1)
	
	{
		$('#docDiv').hide();
	}
	else if(value==0)
		{
		$('#docDiv').show();
		}
	
}





</script>
</head>
<body class="register">
<div class="container">
    	<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
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
    		</div>
    		</nav>
</div>	
<c:if test="${usernameError != null}">
   <script>
   
   $( document ).ready(function() {
		$('#usernameExists').modal('show');
	});
   </script>    	        	
</c:if>
<c:if test="${InsertionError != null}">
   <script>
   
   $( document ).ready(function() {
		$('#insertionErrors').modal('show');
	});
   
   </script>    	        	
</c:if>
<c:if test="${RenterPassword != null}">
   <script>
   
   $( document ).ready(function() {
		$('#renterPass').modal('show');
	});
   
  </script>    	        	
</c:if>
<div class="containerNew">
<div class="containerInside">
<form action="Register" method="post" class="form-horizontal">
<div class="form-group">
<label class="col-sm-2 control-label" style="color: white;">Username </label>
<div class="col-sm-10">
<input type="text" class="form-control" name="user" id="user" required pattern=".{6,10}" title="Please enter username between 6-10 characters">
</div>
</div>
<div class="form-group">
 <label class="col-sm-2 control-label" style="color: white;">Password</label>
 <div class="col-sm-10">
<input type="password" class="form-control" name="pass" id="pass" required pattern=".{6,10}" title="Please enter password between 6-10 characters">
</div>
</div>
<div class="form-group">
<label class="col-sm-2 control-label" style="color: white;">Re Enter Password</label>
<div class="col-sm-10">
<input type="password" class="form-control" name="repass" class="textColor" id="repass" required pattern=".{6,10}" title="Please enter password between 6-10 characters">
</div>
</div>
<div class="form-group">
<label class="col-sm-2 control-label" style="color: white;">Type</label>
<div class="col-sm-10">
 <select name="type" class="textColor" id="typeList" onchange="hide()">
 <option value="0">Patient</option>
 <option value="1">Doctor</option>
</select>
</div>
</div>
<div class="form-group" id="docDiv">
<label class="col-sm-2 control-label" style="color: white;">Doctor Name</label>
<div class="col-sm-10">
 <select name="doctor" class="textColor" id="DoctorList">
<c:forEach items="${DoctorNames}" var="element" >			
<option value="<c:out value="${element.getFname()}"/>"><c:out value="${element.getFname()}"/></option>
</c:forEach>
</select>
</div>
</div>
 <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <button type="submit"  class="btn btn-primary">Register</button>
     
    </div>
  </div>

</form>
</div>
</div>

<div class="rightContainer">
<p class="signUp" style="font-family: Comic Sans MS; font-size: 38px; color: white">Why Sign Up?</p>
<p style="font-family: Comic Sans MS; font-size: 20px; color: white"> Experience Healthcare like never before!</p>
<p style="font-family: Comic Sans MS; font-size: 20px; color: yellow">
<img src="img/valid.png"> Create an account to avail the exciting features of Praemoneo<br/><br/>
<img src="img/valid.png">  Get your Immunization forecast just on the click of a button<br/><br/>
<img src="img/valid.png">  Know your Vaccines and why you need them<br/><br/>
<img src="img/valid.png"> Make a difference to your life as well your dear ones<br/><br/>
<img src="img/valid.png"> What are you waiting for? Sign Up!</p>




</div>

<div class="modal fade" id="usernameExists" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				  <div class="modal-dialog">
				    <div class="modal-content">
				      <div class="modal-header">
				        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				        <h4 class="modal-title" id="myModalLabel">Alert!</h4>
				      </div>
				      <div class="modal-body">
				        Username Already Exists.
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-warning" data-dismiss="modal">Ok</button>
				      </div>
				    </div><!-- /.modal-content -->
				  </div><!-- /.modal-dialog -->
				</div><!-- /.modal -->

<div class="modal fade" id="insertionErrors" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				  <div class="modal-dialog">
				    <div class="modal-content">
				      <div class="modal-header">
				        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				        <h4 class="modal-title" id="myModalLabel">Alert!</h4>
				      </div>
				      <div class="modal-body">
				        Unable to register. Please try after sometime.
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-warning" data-dismiss="modal">Ok</button>
				      </div>
				    </div><!-- /.modal-content -->
				  </div><!-- /.modal-dialog -->
				</div><!-- /.modal -->

<div class="modal fade" id="renterPass" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				  <div class="modal-dialog">
				    <div class="modal-content">
				      <div class="modal-header">
				        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				        <h4 class="modal-title" id="myModalLabel">Alert!</h4>
				      </div>
				      <div class="modal-body">
				        Password Mismatch. Please re-enter password
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