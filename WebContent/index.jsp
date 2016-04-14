<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
  <head>
  	<script src="js/jquery.js"></script>
  	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Praemoneo</title>
    <script src="js/formFunctions.js"></script>	
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/praemoneo.css" rel="stylesheet">
	<link href="css/style2.css" rel="stylesheet">
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <script src="//code.jquery.com/jquery-1.10.2.js"></script>
	<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
    <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
	<script src="//netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
    <base target="_self" />
   <script>
   $(document).ready(function()//function to execute after the DOM is ready
			{
					getDoctorName();
			});
   
   
   
   </script>
  </head>
  <body>  		
  
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
    			<ul class="nav navbar-nav">
    				<li ><a href="#ind">Introduction</a></li>
    				<li ><a href="#cern">About Cerner</a></li>
    				<li ><a href="#contact">Contact</a></li>
    			</ul>
    			<form id="login" class="navbar-form navbar-right" action="ValCredServ" method="POST">
            	<div class="form-group">
            		<div class="inner-addon left-addon">
    				<i class="glyphicon glyphicon-user"></i>
              		<input type="text" value="Enter User Name Here" name="Uname" id="Uname" class="form-control" onFocus="gotFcsUn()" pattern=".{6,10}" required title="6 to 10 characters"/>
            	</div>
            	</div>
            	<div class="form-group">
              		<input type="password" value="Password" class="form-control" name="Passwd" id="Passwd" onFocus="gotFcsPw()" pattern=".{6,10}" required title="6 to 10 characters">
            	</div>
            	<button type="submit" class="btn btn-success" id="login" name="login">Log In <span class="glyphicon glyphicon-log-in"></span></button>
            	<a id="registerForm" class="btn btn-success btn-md" role="button">Register <span class="glyphicon glyphicon-flash"></span></a>
				<c:if test="${regSuccess != null}">
   					<script>
   						$(document).ready(function()//function to execute after the DOM is ready
						{
   							$('#regSuccess').modal('show');
						});
					</script>    	        	
				</c:if>
				<c:if test="${invalidCred != null}">
					<script>
							$(document).ready(function()//function to execute after the DOM is ready
							{
									$('#invalidCredentials').modal('show');
							});
					</script>
				</c:if> 
          </form>
    		</div>
    	</nav>
    </div>
    <div class="container" id="ind">
	    <div class="intro-block">
	    	<div class="container">
	    		<div class="row">
	    			<div class="col-xs-12">
	    				<h1>Praemoneo<span class="text-muted">&raquo;Immunization Forecasting Tool</span></h1>
	    				<p class="lead">Praemoneo in Latin means to foretell, predict, or warn. Cerner's Praemoneo is an immunization forecasting tool that
	    				 can be used by both the patient and the care provider.	The capabilities of this application are driven by the requirements and rules defined 
	    				 by the Center for Disease Control and Prevention's (CDC) Immunization schedule.</p>
	    			</div>
	    		</div>
	    	</div>    	
	    </div>
    </div>
    
    <div class="c-wrapper">
	    <div id="myCarousel" class="carousel slide">
		   <!-- Carousel indicators -->
		   <ol class="carousel-indicators">
			      <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
			      <li data-target="#myCarousel" data-slide-to="1"></li>
			      <li data-target="#myCarousel" data-slide-to="2"></li>
		   </ol>   
		   <!-- Carousel items -->
		   <div class="carousel-inner">
			      <div class="item active">
			         <img src="images/cerner1.png" alt="First slide">
			      </div>
			      <div class="item" >
			         <img src="images/cerner2.png" alt="Second slide">
			      </div>
			      <div class="item">
			         <img src="images/cerner3.png" alt="Third slide">
			      </div>
		   </div>
		   <!-- Carousel nav -->
		   <a class="carousel-control left" href="#myCarousel" 
		      data-slide="prev">&lsaquo;</a>
		   <a class="carousel-control right" href="#myCarousel" 
		      data-slide="next">&rsaquo;</a>
		</div> 
	</div>
	
	
	<div class="container" id="cern">
		<div class="cerner-block">
	    	<div class="container">
	    		<div class="row">
	    			<div class="col-xs-12">
	    				<h1>Cerner<span class="text-muted">&raquo;Health Care is too Important to stay the same.</span></h1>
	    				<p class="lead">We at Cerner supply health care information technology (HCIT) solutions, services, devices and hardware. 
	    				Cerner solutions optimize processes for health care organizations. 
	    				These solutions are currently licensed by approximately 9,300 facilities around the world, including more than 2,650 hospitals, 
	    				3,750 physician practices, 40,000 physicians, 500 ambulatory facilities, 800 home health facilities, 40 employer sites, and 1,600 retail pharmacies.
	    				As of December 2013, the company had more than 14,200 employees globally.</p>
	    			</div>
	    		</div>
	    	</div>    	
	    </div>
    </div>
    
    
    
    <div class="container">
	    <div class="cerner-contact-block" id="contact">
	    	<div class="container">
	    		<div class="row">
	    			<div class="col-xs-12">
	    				<h1>Cerner Contacts</h1>
	    				<p>
	    				Address: Cerner Healthcare Solutions Private Ltd.
	    				</p>
						<p>Ground Floor, Wing B, Block H2, Mountain Ash</p>
						<p>Manyata Embassy Business Park</p>
						<p>Nagawara</p>
						<p>Bangalore - 560 045, India</p>
						<p>+91 80 3301 0400</p>
	    			</div>
	    		</div>
	    		<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d7773.691823007719!2d77.6226240841008!3d13.045478370974756!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3bae176fecf9ae29%3A0x59284b5666888137!2sCerner+Healthcare+Solutions!5e0!3m2!1sen!2sin!4v1425884719137" width="600" height="450" style="border:0"></iframe>
	    	</div>    	
	    </div>
    </div>
    
    
    <!-- Modal -->
				<div class="modal fade" id="regSuccessful" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				  <div class="modal-dialog">
				    <div class="modal-content">
				      <div class="modal-header">
				        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				        <h4 class="modal-title" id="myModalLabel">Success!</h4>
				      </div>
				      <div class="modal-body">
				        Congratulations. You have been successfuly registered.
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-warning" data-dismiss="modal">Ok</button>
				      </div>
				    </div><!-- /.modal-content -->
				  </div><!-- /.modal-dialog -->
				</div><!-- /.modal -->
    
    
    <!-- Modal -->
				<div class="modal fade" id="invalidCredentials" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				  <div class="modal-dialog">
				    <div class="modal-content">
				      <div class="modal-header">
				        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				        <h4 class="modal-title" id="myModalLabel">Alert <span class="glyphicon glyphicon-alert"></span></h4>
				      </div>
				      <div class="modal-body">
				        Invalid username or password
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-warning" data-dismiss="modal">Ok</button>
				      </div>
				    </div><!-- /.modal-content -->
				  </div><!-- /.modal-dialog -->
				</div><!-- /.modal -->
    
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	    <!-- Include all compiled plugins (below), or include individual files as needed -->
	      
  </body>
</html>