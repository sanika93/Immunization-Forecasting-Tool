<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<!-- <script src="js/jquery.js"></script> -->
	
  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
  <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
	<!-- <script src="js/jquery-1.7.1.min.js"></script>
	<script src="js/formFunctions.js"></script> -->
	
	
	<!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/viewImmunization.css" rel="stylesheet">
	<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
	<link rel="stylesheet" href="http://cdn.datatables.net/1.10.2/css/jquery.dataTables.min.css">
	<script type="text/javascript" src="http://cdn.datatables.net/1.10.2/js/jquery.dataTables.min.js"></script>
	<!-- <script type="text/javascript" src="jquery.jeditable.js"></script> -->
	

	
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

<title>View your history</title>
<script type="text/javascript">

function onLoad()

{       
   $('#vaccine').hide();
   document.getElementById("viewHistory").submit();
   

}

window.onload=function()
{
	$('#vaccine').show();
	}

</script>
</head>
<base target="_self">
<body class="viewHistory">
<c:if test="${loaded==null}">
<body onload="javascript:onLoad()">
</c:if>

<div style="width:100%; height:80%;">
<form action="Demo" method="post" id="viewHistory">
 <table class= "table table-hover" id="vaccine">    
 <thead>  
 <tr>
 							 <th>Vaccine name</th>
 							 <th>Dose</th>
  							 <th>Administration  Date</th>
 </tr>
 </thead>
 <tbody>
 <c:forEach var="element" items="${DTap}">  
                             
                                <tr>
                                <td class="vaccineName">${element.getVaccineName()}</td>
                                <td class="dose">${element.getDose()}</td>
                                <td class="date">${element.getAdmin_date()}</td>  
                                </tr> 
                           
</c:forEach>
</tbody>
</table>
</form>
</div>
</body>
</html>