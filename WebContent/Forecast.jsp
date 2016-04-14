<!-- 	Author:Gaurav
	Associate ID: GS035496
	Owner: Cerner
	Team: Vikings -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="js/jquery.js"></script>
<script src="js/formFunctions.js"></script>


<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<link rel="stylesheet" href="css/jquery-ui-timepicker-addon.css">
<link rel="stylesheet" type="text/css" href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="//cdn.datatables.net/plug-ins/1.10.6/integration/bootstrap/3/dataTables.bootstrap.css">
<script type="text/javascript" src="//code.jquery.com/jquery-1.10.2.min.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<script src="js/jquery-ui-timepicker-addon.js"></script>

<script type="text/javascript" src="//cdn.datatables.net/1.10.6/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="//cdn.datatables.net/plug-ins/1.10.6/integration/bootstrap/3/dataTables.bootstrap.js"></script>
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/StatusColor.css" rel="stylesheet">
<title>Praemoneo - Forecast</title>
	<script>
	
    
	
	window.onload = function() {
		$('#forecastBody').show();
		};	
	function onLoad()
	{	
		$('#forecastBody').hide();
		document.getElementById("forecastForm").submit();
	}
	
	
		$(document).ready(function()
			
			{
		
			  
		
		      $('#forecastTable').dataTable();
		     
		    
		      $('#forecastTable tr').click(function() 
		    		    {
		    		    		
		    		    	 	var vaccine = $(this).find(".vaccineName").html();
		    		    	 	var dose = $(this).find(".dose").html();
		    		    	 	
		    		    	 	
		    		    	 	$('#dateTimePicker').datetimepicker({
		    		    	 		timeFormat: "hh:mm:ss",
		    		    	 		dateFormat: "yy-mm-dd"});

		    		    	 	
		    		    	 	
		    		    	 	
		    		    	 	
		    		    	 	
		    		    	 	
		    		    	 	
		    		    	 	
		    		    	 	$("#Vaccine").val(vaccine);
		    		    	 	$("#Dose").val(dose);
		    		    	 	
		    		    	 	/* $("#DateOfAdministration").attr("value",newDate); */
		    		    	 	
		    		    	 	
		    		    	 	dialog= $("#updateDialog" ).attr("title","Schedule").dialog({
		    		    	 	      autoOpen: false,
		    		    	 	      height: 350,
		    		    	 	      width: 500,
		    		    	 	      modal: true,
		    		    	 	      
		    		    	 	      
		    		    	 	    });
		    		    	 	
		    		    	 	dialog.dialog("open");
					});
		      
		      $('#forecastTable tr').hover(function()
		    		  
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
<body class="forecastForm" id="vaccineBody">
<c:if test="${loaded==null}">
<body onload="javascript:onLoad()">
</c:if>

<body>
	<form name="forecastForm" id="forecastForm" action="forecastServ" method="post" >
	<div class="table-responsive" id="forecastBody">
	<div class="panel panel-default" style="height:95%;margin-top:2%;">
  	<!-- Default panel contents -->
  	<div class="panel-heading"><b>Immunization Forecast List</b></div>
  	<div style="width:95%;height:100%;margin-top:1%; margin-left:2%;">
		<table class="table table-hover table-striped" id="forecastTable">
		<thead>
				<tr>
					<th>Vaccine</th>
			         <th>Dose</th>
			         <th>Recommended Date</th>
			         <th>Type</th>
			         <th>Vaccine Brand Name</th>
			         <th>Status</th>
      			</tr>
		</thead>
			<tbody>      			
    			<c:forEach items="${dTapForecastList}" var="element" > <!-- iterate through the forecasted list and add each forecast as a row  --> 				
        			<tr>
        				<td class="vaccineName"><c:out value="${element.getvName()}" /></td>
            			<td class ="dose"><c:out value="${element.getDose()}" /></td>
          				<td class="forecastDate"><c:out value="${element.getForecast_Date()}" /></td>
          				<td><c:out value="${element.getType()}"/></td>
          				<td><c:out value="${element.getVaccineName()}" /></td>
          				<c:choose>
          				<c:when test = "${fn:contains(element.getStatus(),'Safe But Not Recommended')}"><td class="Safe-But-Not-Recommended"><span class="glyphicon glyphicon-thumbs-down"> </span><c:out value="${element.getStatus()}" /></td>
          				</c:when>
          				<c:when test = "${fn:contains(element.getStatus(),'Recommended')}"><td class="Recommended"><span class="glyphicon glyphicon-thumbs-up"></span><c:out value="${element.getStatus()}" /></td>
          				</c:when>
          				<c:when test = "${fn:contains(element.getStatus(),'Final Booster Dose')}"><td class="Booster-Dose"><span class="glyphicon glyphicon-bullhorn"> </span><c:out value="${element.getStatus()}" /></td>
          				</c:when>          				
          				<c:otherwise>
          				<td><c:out value="${element.getStatus()}" /></td>
          				</c:otherwise>
          				</c:choose>
        			</tr> 
    			</c:forEach>
    			
    			<c:forEach items="${hibForecastList}" var="element" > <!-- iterate through the forecasted list and add each forecast as a row  -->  				
        			<tr>
        				<td class="vaccineName"><c:out value="${element.getvName()}"/></td>
            			<td class ="dose"><c:out value="${element.getDose()}"/></td>
          				<td class="forecastDate"><c:out value="${element.getForecast_Date()}"/></td>
          				<td><c:out value="${element.getType()}"/></td>
          				<td><c:out value="${element.getVaccineName()}"/></td>
          				<c:choose>
          				<c:when test = "${fn:contains(element.getStatus(),'Safe But Not Recommended')}"><td class="Safe-But-Not-Recommended"><td class="Safe-But-Not-Recommended"><span class="glyphicon glyphicon-thumbs-down"> </span><c:out value="${element.getStatus()}"/></td>
          				</c:when>
          				<c:when test = "${fn:contains(element.getStatus(),'Recommended')}"><td class="Recommended"><span class="glyphicon glyphicon-thumbs-up"></span><c:out value="${element.getStatus()}"/></td>
          				</c:when>
          				<c:when test = "${fn:contains(element.getStatus(),'Final Booster Dose')}"><td class="Booster-Dose"><span class="glyphicon glyphicon-bullhorn"> </span><c:out value="${element.getStatus()}"/></td>
          				</c:when>
          				<c:otherwise>
          				<td><c:out value="${element.getStatus()}"/></td>
          				</c:otherwise>
          				</c:choose>
        			</tr>
    			</c:forEach>
    			
    			<c:forEach items="${pcvf}" var="iterator" > <!-- iterate through the forecasted list and add each forecast as a row  -->  				
        			<tr>
        				<td class="vaccineName"><c:out value="${iterator.getvName()}"/></td>
            			<td class ="dose"><c:out value="${iterator.getDose()}"/></td>
          				<td class="forecastDate"><c:out value="${iterator.getForecast_Date()}"/></td>
          				<td><c:out value="${iterator.getType()}"/></td>
          				<td><c:out value="${iterator.getVaccineName()}"/></td>
          				<c:choose>
          				<c:when test = "${fn:contains(iterator.getStatus(),'Safe But Not Recommended')}"><td class="Safe-But-Not-Recommended"><span class="glyphicon glyphicon-thumbs-down"> </span><c:out value="${iterator.getStatus()}"/> </td>
          				</c:when>
          				<c:when test = "${fn:contains(iterator.getStatus(),'Recommended')}"><td class="Recommended"><span class="glyphicon glyphicon-thumbs-up"></span><c:out value="${iterator.getStatus()}"/></td>
          				</c:when>
          				<c:when test = "${fn:contains(iterator.getStatus(),'Final Booster Dose')}"><td class="Booster-Dose"><span class="glyphicon glyphicon-bullhorn"> </span><c:out value="${iterator.getStatus()}"/></td>
          				</c:when>
          				<c:otherwise>
          				<td><c:out value="${iterator.getStatus()}"/></td>
          				</c:otherwise>
          				</c:choose>
        			</tr> 
    			</c:forEach>
    			
    			<c:forEach items="${rotaList}" var="element" > <!-- iterate through the forecasted list and add each forecast as a row  -->  				
        			<tr>
        				<td class="vaccineName"><c:out value="${element.getvName()}"/></td>
            			<td class ="dose"><c:out value="${element.getDose()}"/></td>
          				<td class="forecastDate"><c:out value="${element.getForecast_Date()}"/></td>
          				<td><c:out value="${element.getType()}"/></td>
          				<td><c:out value="${element.getVaccineName()}"/></td>
          				<c:choose>
          				<c:when test = "${fn:contains(element.getStatus(),'Safe But Not Recommended')}"><td class="Safe-But-Not-Recommended"><td class="Safe-But-Not-Recommended"><span class="glyphicon glyphicon-thumbs-down"> </span><c:out value="${element.getStatus()}"/></td>
          				</c:when>
          				<c:when test = "${fn:contains(element.getStatus(),'Recommended')}"><td class="Recommended"><span class="glyphicon glyphicon-thumbs-up"></span><c:out value="${element.getStatus()}"/></td>
          				</c:when>
          				<c:when test = "${fn:contains(element.getStatus(),'Not recommended due to SCID')}"><td class="Not-Recommended"><span class="glyphicon glyphicon-bullhorn"> </span><c:out value="${element.getStatus()}"/></td>
          				</c:when>
          				<c:otherwise>
          				<td><c:out value="${element.getStatus()}"/></td>
          				</c:otherwise>
          				</c:choose>
        			</tr>
    			</c:forEach>
    			
    			<c:forEach items="${tdap}" var="element" > <!-- iterate through the forecasted list and add each forecast as a row  --> 				
        			<tr>
        				<td class="vaccineName"><c:out value="${element.getvName()}"/></td>
            			<td class ="dose"><c:out value="${element.getDose()}"/></td>
          				<td class="forecastDate"><c:out value="${element.getForecast_Date()}"/></td>
          				<td><c:out value="${element.getType()}"/></td>
          				<td><c:out value="${element.getVaccineName()}"/></td>
          				<c:choose>
          				<c:when test = "${fn:contains(element.getStatus(),'Safe But Not Recommended')}"><td class="Safe-But-Not-Recommended"><td class="Safe-But-Not-Recommended"><span class="glyphicon glyphicon-thumbs-down"> </span><c:out value="${element.getStatus()}"/></td>
          				</c:when>
          				<c:when test = "${fn:contains(element.getStatus(),'Recommended')}"><td class="Recommended"><span class="glyphicon glyphicon-thumbs-up"></span><c:out value="${element.getStatus()}"/></td>
          				</c:when>
          				<c:when test = "${fn:contains(element.getStatus(),'Final Booster Dose')}"><td class="Booster-Dose"><span class="glyphicon glyphicon-bullhorn"> </span><c:out value="${element.getStatus()}"/></td>
          				</c:when>
          				<c:otherwise>
          				<td><c:out value="${element.getStatus()}"/></td>
          				</c:otherwise>
          				</c:choose>
        			</tr> 
    			</c:forEach>
    			
    			
    			<c:forEach items="${hepBList}" var="element" > <!-- iterate through the forecasted list and add each forecast as a row  --> 				
        			<tr>
        				<td class="vaccineName"><c:out value="${element.getvName()}"/></td>
        				<c:if test="${element.getDose()==-1}">
        					<td data-toggle="tooltip" data-placement="top" title="Patient's weight is less than 2,000 grams."><c:out value="${element.getDose()}"/></td>
        				</c:if>
            			<c:if test="${element.getDose()==0}">
        					<td data-toggle="tooltip" data-placement="top" title="Patient has Surface Antigen."><c:out value="${element.getDose()}"/></td>
        				</c:if>
        				<c:if test="${element.getDose()>0}">
        					<td class ="dose"><c:out value="${element.getDose()}"/></td>
        				</c:if>
          				<td class="forecastDate"><c:out value="${element.getForecast_Date()}"/></td>
          				<td><c:out value="${element.getType()}"/></td>
          				<td><c:out value="${element.getVaccineName()}"/></td>
          				<c:choose>
          				<c:when test = "${fn:contains(element.getStatus(),'Safe But Not Recommended')}"><td class="Safe-But-Not-Recommended"><td class="Safe-But-Not-Recommended"><span class="glyphicon glyphicon-thumbs-down"></span><c:out value="${element.getStatus()}"/></td>
          				</c:when>
          				<c:when test = "${fn:contains(element.getStatus(),'Recommended')}"><td class="Recommended"><span class="glyphicon glyphicon-thumbs-up"></span><c:out value="${element.getStatus()}"/></td>
          				</c:when>
          				<c:when test = "${fn:contains(element.getStatus(),'Final Booster Dose')}"><td class="Booster-Dose"><span class="glyphicon glyphicon-bullhorn"> </span><c:out value="${element.getStatus()}"/></td>
          				</c:when>
          				<c:otherwise>
          				<td><c:out value="${element.getStatus()}"/></td>
          				</c:otherwise>
          				</c:choose>
        			</tr> 
    			</c:forEach>
    		</tbody>
		</table>
		</div>
	</div>
	</div>
				<div class="modal fade" id="regSuccessful" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				  <div class="modal-dialog">
				    <div class="modal-content">
				      <div class="modal-header">
				        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				        <h4 class="modal-title" id="myModalLabel">Note!</h4>
				      </div>
				      <div class="modal-body">
				        <pre>
-> For children ages 14 through 59 months who have received PCV7, administer a single supplement dose of PCV13.
				        
-> For children age 24 through 71 months with certain conditions , such as, Immunocompetent with chronic heart disease, asplenia, and immune compromising conditions, administer 1 dose of PCV13 if 3 doses of PCV were received previously, or administer 2 doses of PCV13at least 8 weeks apart if fewer than 3 doses of PCV were received previously.
			           
-> A single dose of PCV13 may be administered to previously unvaccinated children aged 6 through 18 years who have anatomic or functional asplenia, immune compromising conditions, or cochlear implant or cerebrospinal fluid leak.
				           
-> Administer PPSV23 at least 8 weeks after the last does of PCV to children 2 years or older with certain underlying medical condition.</pre>
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-warning" data-dismiss="modal">Ok</button>
				      </div>
				    </div><!-- /.modal-content -->
				  </div><!-- /.modal-dialog -->
				</div><!-- /.modal -->		
	</form>
	<!-- <script src="js/bootstrap.min.js"></script>   --> 
<div id="updateDialog" style="display:none;">
<form action="GetAppointment" method="post" id="updateHistory">
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
<td><b>Appointment Date</b></td>
<td></td>
<td><input type="text" class="textColor" class="form-control" id="dateTimePicker" name="dateTimePicker" value="AppointmentDate"></td>
</tr>


<tr><td></td><td></td>
<td><input type="submit" class="btn btn-success" name="getAppointments" id="getAppointments" value="Get Appointment"></td>
</tr>
</table>
</form>
</div>
</body>
</html>