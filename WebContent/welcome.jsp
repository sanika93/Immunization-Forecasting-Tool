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
    <script src="js/formFunctions.js"></script>	
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/praemoneo.css" rel="stylesheet">
    
    <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
	
	<link href="css/style.css" rel="stylesheet" type="text/css" />
	<link href="css/styleGallery.css" rel="stylesheet" type="text/css" /> 
	<script src="js/jquery-1.2.6.min.js" type="text/javascript"></script> 
	<script src="js/jquery.easing.1.3.js" type="text/javascript"></script>

<script>
$(document).ready(function(){
	$("button").click(function(){
        $.getJSON("http://www.acsyshealthguide.appspot.com/api/healthupdates/format/json", function(result){
            $.each(result, function(i, field){
                $("div").append(field + " ");
            });
        });
    });
	//horizontal
    $("ul#horizontal li").mouseover(function(){
        $(this).stop().animate({width:'550px', height:'300px;'},{queue:false, duration:600, easing: 'easeOutBounce'})
    });

    $("ul#horizontal li").mouseout(function(){
        $(this).stop().animate({width:'40px',height:'300px;'},{queue:false, duration:600, easing: 'easeOutBounce'})
    });
    
});
</script> 
    <base target="_self" />
	<title></title>
</head>
<base target="_self">
<body>
<div>
<h1>&nbsp;Whats new at cerner?</h1>
<div class="Quick-Links">
<div class="list-group">
  <a href="http://www.cdc.gov/vaccines/vac-gen/why.htm" target="_blank" class="list-group-item active"> 
    <h4 class="list-group-item-heading">Why immunize our children?</h4>
    <p class="list-group-item-text">Sometimes we are confused by the messages in the media. First we are assured that, thanks to vaccines, some diseases are almost gone from the U.S. But we are also warned to immunize our children, ourselves as adults, and the elderly.</p>
 </a>
  <a href="http://www.cdc.gov/about/default.htm" target="_blank" class="list-group-item">
  <h4 class="list-group-item-heading">What is CDC?</h4>
  <p class="list-group-item-text">The Centers for Disease Control and Prevention is the leading national public health institute of the United States.</p>
  </a>
  <a href="http://en.wikipedia.org/wiki/Vaccination_schedule" target="_blank" class="list-group-item">
  <h4 class="list-group-item-heading">What is a vaccination schedule?</h4>
  <p class="list-group-item-text">A vaccination schedule is a series of vaccinations, including the timing of all doses, which may be either recommended or compulsory, depending on the country of residence.</p>
  </a>
</div>	
</div>

<div id="vnav">
	<ul id="horizontal"> 
		<li><img src="images/cerner5.jpg" width="782" alt="" height="440" /></li> 
		<li><img src="images/cerner6.jpg" width="782" alt="" height="440" /></li> 
		<li><img src="images/cerner7.jpg" width="782" alt="" height="440" /></li> 
		<li><img src="images/cerner9.jpg" width="782" alt="" height="440" /></li> 
		<li><img src="images/cerner10.jpg" width="782" alt="" height="440" /></li>
		<li><img src="images/cerner8.jpg" width="782" alt="" height="440" /></li>  
	</ul>
</div>
  	<c:if test="${pwdChanged != null}">
       <script>
       		$(document).ready(function()//function to execute after the DOM is ready
    		{
       			$('#pwdChangedSuccessfully').modal('show');
    		});
        </script>   </c:if>


</body>
</html>