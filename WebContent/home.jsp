<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
  <head>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
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
    <base target="_parent" />
    
    <script>
    
    
   
    function submitParameter() {
        
       $.ajax({
    				url: 'beforeAddParameter',
   					type: 'post',
    				success: function(data){
    				if(data=="success")
    				{
  						document.getElementById("home").src = 'AddParameter.jsp';
    				}
    				else if(data=="failure")
    				{
    					$('#failedForecast').modal('show');
    					document.getElementById("home").src = 'AddProfile.jsp';
     				}
    			} 

			});
}
    
    function checkDemographics()

    {
    	
    	


    	

           

      			$.ajax({       

                           		url: 'checkDemographics',
                           		type: 'post',
								success: function(data){
											if(data=="success")
											{
												
												
												document.getElementById("home").src = 'Forecast.jsp';
											}

  										 else if(data=="failure")
											{
  											$('#failedForecast').modal('show');
  											 document.getElementById("home").src = 'AddProfile.jsp';
											}
										} 

                        });

           

   }


</script>
    
    
    
  </head>
  <body>
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
          	<img src="images/Cernerlogo.png" height=49 width=190></img>
    		</div>
    		
    		<div class="navbar-collapse collapse">
    			<ul class="nav navbar-nav">  			
    				
    					<li><a href="welcome.jsp" target="home">Home <span class="glyphicon glyphicon-home"></span></a></li>
    					
    					<li><a href="AddProfile.jsp" target="home">Personal Details</a></li>
    					
    					<li><a href="#" target="home">Allergy Details</a></li>
    					
    					<li><a id="param" target="home">Health Records</a></li>
    					
    					<li><a href="View.jsp" target="home">Immunization History<span class="glyphicon glyphicon-list-alt"></span></a></li>
    					
    					
    				    				
    				   
    				
    				<li><a target="home" id="forecast" onclick="javascript:checkDemographics();">Get Forecast</a></li>    	
    				
    				
    				
    					<li>
    					<a href="#" class="dropdown-toggle" data-toggle="dropdown"><c:out value="${uName}"></c:out><span class="glyphicon glyphicon-user"></span><b class="caret"></b></a>
    					<ul class="dropdown-menu">					
    						<li><a href="resetUserPw.jsp" role="button" target="home">Reset Password</a></li>
    						<li>
    						<a href="#logoutConfirmation" data-toggle="modal">Log Out</a></li>
    					</ul>  					
    					
    				</li>
    				
    			</ul>
    		</div>   			
		</div> 
		</nav>  			
    </div>
    <!-- Modal -->
				<div class="modal fade" id="logoutConfirmation" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				  <div class="modal-dialog">
				    <div class="modal-content">
				      <div class="modal-header">
				        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				        <h4 class="modal-title" id="logoutConfirmationTitle">Attention!</h4>
				      </div>
				      <div class="modal-body">
				        Are you sure you want to logout?
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-warning" data-dismiss="modal">No</button>
				        <a href="index.jsp" role="button" class="btn btn-success">Yes</a>
				      </div>
				    </div><!-- /.modal-content -->
				  </div><!-- /.modal-dialog -->
				</div><!-- /.modal -->
				
	
	
	
	
    <div class="intro-block">
	<iframe class ="iframenew-block" id="home" name="home" height=650px src="welcome.jsp">
	</iframe>
	</div>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <!-- Include all compiled plugins (below), or include individual files as needed -->
   <script src="js/bootstrap.min.js"></script>
</body>
</html>