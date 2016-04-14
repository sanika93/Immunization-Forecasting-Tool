
 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Profile</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/praemoneo.css" rel="stylesheet">
	<link href="css/styleforParameter&Profile.css" rel="stylesheet">
    
<script>
function onLoad()
{	
	document.getElementById("ViewProfilePage").submit();
}
</script>

</head>
<c:if test="${loadedProfile == null}">
<body onload="javascript:onLoad()">
</c:if>
<body>
<h1>&nbsp; Your Profile Details :</h1>
<div class= "customContainer">
<form action="ViewProfile" method="post" name ="ViewProfilePage" id="ViewProfilePage">
<table class="table table-hover table-striped tblcustom">
<tr><td>
First Name:</td><td><input type="text" name="fname" size="26" readonly value=<c:out value="${ Patient.getFirstName()}"/>></input>
</td></tr>
<tr><td>
Middle Name:</td><td>
<c:choose>
	<c:when test="${Patient.getMiddleName() == null }">
		<input type="text" size="26" name="mname" readonly value="Not specified"/>
	</c:when>
	<c:otherwise>
		<input type="text" name="mname" size="26" readonly value=<c:out value="${Patient.getMiddleName()}"/>></input>
	</c:otherwise>
</c:choose>
</td></tr>
<tr><td>
Last Name:</td><td><input type="text" name="lname" size="26" readonly value=<c:out value="${ Patient.getLastName()}"/>></input>
</td></tr>
<tr><td>
Date Of Birth:</td><td><input type="date" name="dob" size="26" readonly value=<c:out value="${Patient.getDateOfBirth()}"/>></input>

</td></tr>
<tr><td>
Gender:</td><td>
<c:choose>
	<c:when test="${fn:contains(Patient.getGender(),'m')}">
		<input type="text" name="gender" size="26" readonly value="Male"/>
	</c:when>
	<c:otherwise>
		<input type="text" name="gender" readonly size="26" value="Female"/>
	</c:otherwise>
</c:choose>
</td></tr>
<tr><td>
Email ID:</td><td><input type="text" name="email" size="26" readonly value=<c:out value="${ Patient.getEmailID()}"/>></input>
</td></tr>
<tr><td>
Contact number:</td><td> <input type="text" name="contactnum" size="26" readonly value=<c:out value="${ Patient.getContactNumber()}"/>></input>
</td></tr>
<tr><td>
Weight:</td><td><input type="text" name="weight" readonly size="26" value=<c:out value="${ Patient.getWeight()}"/>></input>
</td></tr>
<tr><td>
Present Address:</td><td><textarea name="address" rows="4" cols="27" readonly><c:out value="${ Patient.getAddress()}"/></textarea>
</td></tr>
</table>
</form>
</div>
</body>
</html>