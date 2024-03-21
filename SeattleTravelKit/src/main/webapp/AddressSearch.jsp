<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find Address Data</title>
</head>
<body>
	<form action="findAddressByName" method="post">
		<h1>Search for Address by Usernames</h1>
		<p>
			<label for="username">UserName</label>
			<input id="username" name="username" value="${fn:escapeXml(param.username)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>

	<h1>Matching Address</h1>
        <table border="1">
            <tr>
                <th>UserName</th>
                <th>City</th>
                <th>Street</th>
                <th>State</th>
                <th>ZipCode</th>
                <th>Country</th>
                <th>Delete Address</th>
                <th>Update Address</th>
            </tr>
            
            <tr>
                <td><c:out value="${address.userName}" /></td>
                <td><c:out value="${address.city}" /></td>
                <td><c:out value="${address.street1}" /></td>
                <td><c:out value="${address.state}" /></td>
                <td><c:out value="${address.zipCode}" /></td>
                <td><c:out value="${address.country}" /></td>
                <td><a href=""/>Delete</a></td>
                <td><a href=""/>Update</a></td>
            </tr>
      
       </table>
</body>
</html>
