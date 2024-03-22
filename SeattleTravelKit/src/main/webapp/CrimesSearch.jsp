<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find Crime Records</title>
</head>
<body>
	<form action="findCrimes" method="post">
		<h1>Search for Crimes by ZipCode in Seattle</h1>
		<p>
			<label for="zipCode">ZipCode</label>
			<input id="zipCode" name="zipCode" value="${fn:escapeXml(param.zipCode)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<p><a href="/SeattleTravelKit/safestZipCodes">Click here to view the top 10 safest ZipCodes in Big Seattle Area</a></p>
	
	<h1>Matching Crimes</h1>
        <table border="1">
            <tr>
                <th>CaseNumber</th>
                <th>Address</th>
                <th>ZipCode</th>
            </tr>
            <c:forEach items="${crimes}" var="crime" >
                <tr>
                    <td><c:out value="${crime.getCaseNumber()}" /></td>
                    <td><c:out value="${crime.getAddress()}" /></td>
                    <td><c:out value="${crime.getZipCode()}" /></td>
                </tr>
            </c:forEach>
       </table>
</body>
</html>
