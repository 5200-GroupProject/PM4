<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find a User</title>
</head>
<body>
	<form action="findCrimes" method="post">
		<h1>Search for a BlogUser by FirstName</h1>
		<p>
			<label for="zipCode">FirstName</label>
			<input id="zipCode" name="zipCode" value="${fn:escapeXml(param.zipCode)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<h1>Matching Crimes</h1>
        <table border="1">
            <tr>
                <th>CaseNumber</th>
                <th>Date</th>
                <th>Address</th>
                <th>Zip Code</th>
            </tr>
            <c:forEach items="${crimes}" var="crime" >
                <tr>
                    <td><c:out value="${crime.getCaseNumber()}" /></td>
                    <td><fmt:formatDate value="${crime.getCreatedDateTime()}" pattern="yyyy-MM-dd"/></td>
                    <td><c:out value="${crime.getAddress()}" /></td>
                    <td><c:out value="${crime.getZipCode()}" /></td>
                </tr>
            </c:forEach>
       </table>
</body>
</html>
