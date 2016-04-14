<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Parameters</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/praemoneo.css" rel="stylesheet">
	<link href="css/styleforParameter&Profile.css" rel="stylesheet">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<script>
function onLoad()

{       
	
   document.getElementById("viewParam").submit();

}
</script>
<body>
<c:if test="${fetchedParam==null}">
<body onload="javascript:onLoad()">
</c:if>
<h1>&nbsp; Your health conditions :</h1>
<div class= "customContainer">
<form action="ViewParameter" id="viewParam" method="post">
<table class="table table-hover table-striped tblcustom">
<tr><th>Health condition</th><th>Had I selected it?</th></tr>
<c:forEach items="${list}" var="object">
<tr><td>
	<c:out value="${object.getParameterName()}"></c:out>
	</td>
	<td>
	<c:out value="${object.getValue()}"></c:out>
</td></tr>
</c:forEach> 
</table>
</form>
</div>
</body>
</html>