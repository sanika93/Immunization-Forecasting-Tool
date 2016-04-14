<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<script src="js/formFunctions.js"></script>
	<!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/styleforParameter&Profile.css" rel="stylesheet">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
   <title>Allergy History</title>
</head>
<script type="text/javascript">

function onLoad()

{       
	
   document.getElementById("viewAllergyHistory").submit();

}

</script>

<body>
<c:if test="${fetchedAllergy==null}">
<body onload="javascript:onLoad()">
</c:if>
 <h1>&nbsp;
    Your Allergy History:
 </h1> 
 <div class= "customContainer">
 <form action="ViewAllergyHistory" method="post" id="viewAllergyHistory">
  
  <table class="table table-hover table-striped tblcustom">  
   <thead>
            <tr>
                <th>Allergy Name</th>
                <th>Vaccine Name</th>
                <th>Dose</th>
                <th>Description</th>
                
           </tr>
   </thead>
   <tbody>    

 <c:forEach var="element" items="${listData}">  
                                <tr>

                                <td>${element.getAllergyName()}</td>
								
								<td>${element.getVaccineName()}</td>
								
                                <td>${element.getDose()}</td>

                                <td>${element.getDescription()}</td>  
                               
                                
                                                                                          
                                </tr>                        
</c:forEach>
</tbody>
</table>
</form>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
	<script src="js/scriptReset.js"></script>
    <script src="js/jquery-1.7.1.min.js"></script>
	<script src="js/jquery.validate.js"></script>
</body>
</html>