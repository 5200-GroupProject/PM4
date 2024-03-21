<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Top 10 Safest ZipCodes</title>
</head>
<body>
	<h1>Top 10 Safest ZipCodes with the Least Crimes</h1>
	<c:if test="${not empty messages.success}">
        <p><b>${messages.success}</b></p>
    </c:if>
	<table border="1">
		<tr>
			<th>ZipCode</th>
			<th>Crime Count</th>
		</tr>
		<c:forEach items="${safestZipCodes}" var="zipcodeStats">
			<tr>
				<td><c:out value="${zipcodeStats.zipCode}" /></td>
				<td><c:out value="${zipcodeStats.crimeCount}" /></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>