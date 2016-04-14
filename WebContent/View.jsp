<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<script src="js/jquery.js"></script>
	
	<!-- <script src="js/jquery-1.7.1.min.js"></script>
	<script src="js/formFunctions.js"></script> -->
	
	
	<!-- Bootstrap -->
    <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
	<link href="css/viewImmunization.css" rel="stylesheet">
	 <link href="css/bootstrap.min.css" rel="stylesheet">
	
	<!-- <link rel="stylesheet" href="http://cdn.datatables.net/1.10.2/css/jquery.dataTables.min.css">
	<script src="//code.jquery.com/jquery-1.10.2.js"></script>
  	<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
	<script type="text/javascript" src="http://cdn.datatables.net/1.10.2/js/jquery.dataTables.min.js"></script> -->
	
	
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    
    <link rel="stylesheet" type="text/css" href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css">

<link rel="stylesheet" type="text/css" href="//cdn.datatables.net/plug-ins/1.10.6/integration/bootstrap/3/dataTables.bootstrap.css">

<script src="//code.jquery.com/jquery-1.10.2.js"></script>

<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

<script type="text/javascript" src="//cdn.datatables.net/1.10.6/js/jquery.dataTables.min.js"></script>

<script type="text/javascript" src="//cdn.datatables.net/plug-ins/1.10.6/integration/bootstrap/3/dataTables.bootstrap.js"></script>

    

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



$(document).ready(function()
		
		{
	
		  
	
	      $('#vaccine').dataTable();
	      
	     
	      $("#datepicker").datepicker({maxDate: "+0d",minDate:"-20Y"});
	    	
	     
	      
	      
	      $('#vaccine tr').click(function() 
	    		    {
	    		    		
	    		    	 	var vaccine = $(this).find(".vaccineName").html();
	    		    	 	var dose = $(this).find(".dose").html();
	    		    	 
	    		    	 	var date=$(this).find(".date").html();
	    		    	 	
	    		    	 	
	    		    	 	
	    		    	 	
	    		    	 	
	    		    	 	
	    		    	 	$("#Vaccine").val(vaccine);
	    		    	 	$("#Dose").val(dose);
	    		    	 	/* $("#DateOfAdministration").attr("value",newDate); */
	    		    	 	
	    		    	 	
	    		    	 	dialog= $("#updateDialog" ).attr("title","Edit").dialog({
	    		    	 	      autoOpen: false,
	    		    	 	      height: 300,
	    		    	 	      width: 450,
	    		    	 	      modal: true,
	    		    	 	      
	    		    	 	      
	    		    	 	    });
	    		    	 	
	    		    	 	dialog.dialog("open");
				});
	      
	      $('#vaccine tr').hover(function()
	    		  
	      {
	    	  var tip = $(this).attr('title');

	          $('#myToolTip').html(tip).fadeIn();
	      }
	      );
	      
	
		}
);

</script>
</head>
<base target="_self">
<body class="viewHistory">
<c:if test="${loaded==null}">
<body onload="javascript:onLoad()">
</c:if>
<c:if test="${addedSuccess!=null}">
<body onload="javascript:onLoad()">
</c:if>

<!-- <div id="myToolTip"></div> -->



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
          	
    		</div>
    		
<div class="navbar-collapse collapse">
<form action="AddImmune" method="post" class="form-horizontal">
<table class="table table-hover table-striped">
<tr>
<td><b>Vaccine Name</b></td>
<td><select name="Vaccine" class="form-control" id="vaccineNameSelect">
<option value="DTap">DTap</option>
<option value="HepB" disabled="disabled">Hepatitis-B</option>
<option value="TDap">TDaP</option>
<option value="Rotavirus1">Rotavirus-1</option>
<option value="Rotavirus5">Rotavirus-5</option>
<option value="Hib">Haemophilus Influenza</option>
<option value="PCV13">PCV13</option>
</select></td>
<td><b>Dose</b></td>
<td>
<select name="Dose" class="form-control" id="dosage">
<option value="1">1</option>
<option value="2">2</option>
<option value="3">3</option>
<option value="4">4</option>
<option value="5">5</option>
</select>
</td>
<td><b>Date of Administration</b></td>
<td><input type="date" class="form-control" name="DateOfAdministration" required max="01-01-1990" min= "03-10-2015"></td>
<td><input type="submit" class="btn btn-success" name="addImmunizations" value="Save"></td></tr>
</table>
</form>
</div>
</div>
</nav>
</div>   	
<div style="width:95%; height:400px; margin-top:6%; margin-left:2%;">
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
                             
                                <tr data-toggle="tooltip" title="Click to update your Vaccination Date.">
                                <td class="vaccineName">${element.getVaccineName()}</td>
                                <td class="dose">${element.getDose()}</td>
                                <td class="date">${element.getAdmin_date()}</td>  
                                </tr> 
                           
</c:forEach>
</tbody>
</table>
</form>



</div>

<div id="updateDialog" style="display:none;">
<form action="AddImmune" method="post" id="updateHistory">
<table class="table table-hover">
<tr>
<td><b>Vaccine Name</b></td><td></td>
<td><input type="text" readonly  id="Vaccine" name="Vaccine" class="form-control"></td>
</tr>

<tr>
<td><b>Dose Number</b></td><td></td>
<td><input type="text" readonly  id="Dose" name="Dose" class="form-control"></td>
</tr>

<tr>
<td><b>Date of Administration</b></td>
<td></td>
<td><input type="date" class="textColor" id="DateOfAdministration" name="DateOfAdministration" required max="2020-03-02" min= "1990-01-01" value="2015-04-10" class="form-control"></td>
</tr>


<tr><td></td><td></td>
<td><input type="submit" class="btn btn-success" name="addImmunizations" id="addImmunizations" value="Update"></td>
</tr>
</table>
</form>
</div>







































</body>
</html>