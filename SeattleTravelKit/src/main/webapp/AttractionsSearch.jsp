<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find attraction</title>
</head>
<body>
	<form action="findAttractions" method="get">
		<h1>Search for Attractions by ZipCode </h1>
		<p>
			<label for="ZipCode">ZipCode</label>
			<input id="ZipCode" name="ZipCode" value="${fn:escapeXml(param.ZipCode)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br/>
	
	<br/>
	<h1>Matching Attractions</h1>
        <table border="1">
            <tr>
                <th>AttractionName</th>
                <th>Phone</th>
                <th>Website</th>
                <th>ZipCode</th>
                <th>Area</th>
            </tr>
            <c:forEach items="${attractions}" var="attraction" >
                <tr>
                    <td><c:out value="${attraction.attractionsName}" /></td>
                    <td><c:out value="${attraction.phone}" /></td>
                    <td><c:out value="${attraction.website}" /></td>
                    <td><c:out value="${attraction.zipCode}" /></td>
                    <td><c:out value="${attraction.area}" /></td>
                </tr>
            </c:forEach>
       </table>
</body>
</html>