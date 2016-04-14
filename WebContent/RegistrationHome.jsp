<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

	
  	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  	
  	
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Welcome</title>
   
    <script src="js/jquery.js"></script>
	<script src="js/jquery-1.7.1.min.js"></script>
    <script src="js/formFunctions.js"></script>	
    
    <!-- Bootstrap -->
 
	
	
	
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/praemoneo.css" rel="stylesheet">
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
 
    <base target="_self" />
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
    					
    					
    					document.getElementById("home").src = 'AddProfile.jsp';
     					
    				}
    			} 

		});

      
 }
    
  
	

</script>
    
 </head>
<body style="background:url('images/bluealt.png');">

 <div class="container">
    	<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
    		<div class="navbar-header">
          	<img src="images/Cernerlogo.png" height=49 width=190></img>
    		</div>
    		
  
    		
    		
    <div class="navbar-collapse collapse">
    			<ul class="nav navbar-nav">
    				<li><a href="RegistrationHome.jsp">Welcome</a></li>
    				
    				<li class="dropdown">
    					<a href="#" class="dropdown-toggle" data-toggle="dropdown">Add your Health Records<b class="caret"></b></a>
    					<ul class="dropdown-menu">
    						<li><a href="AddProfile.jsp" target="home">Demographics</a></li>
    						<li><a href="InsertAllergy.jsp" target="home">Allergy Details</a></li>
    						<li><a target="home" onclick="javascript:submitParameter();" id="parameter">Health Parameters</a></li>
    						<li><a href="AddImmunization.jsp" target="home">Vaccination History</a></li>
    					</ul>
    				</li>
    				
    				<li ><a href="#">Help</a></li>
    				<li style="float:right;" ><a href="index.jsp">Login</a></li>
    			</ul>
    </div>
    		
    		 </nav>
    </div>
    <div class="intro-block">
	<iframe class ="cerner-block"id="home" name="home" width=1390px height=900px src="welcomenew.jsp">
	</iframe>
	</div>
    
    
    	
 
 
</body>
</html>