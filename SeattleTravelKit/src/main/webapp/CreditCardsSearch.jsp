<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find CreditCard Data</title>
</head>
<body>
	<form action="findCreditCards" method="post">
		<h1>Search for CreditCards by Usernames</h1>
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
	<br/>
	<div id="userCreate"><a href="">Create New CreditCards</a></div> 
	<br/>
	<h1>Matching CrditCards</h1>
        <table border="1">
            <tr>
                <th>UserName</th>
                <th>CardNumber</th>
                <th>Expiration</th>
                <th>Delete CreditCard</th>
                <th>Update CreditCard</th>
            </tr>
            <c:forEach items="${cards}" var="card" >
                <tr>
                    <td><c:out value="${card.userName}" /></td>
                    <td><c:out value="${card.cardNumber}" /></td>
                    <td><c:out value="${card.expiration}" /></td>
                    <td><a href=""/>"Delete</a></td>
                    <td><a href=""/>"Update</a></td>
                </tr>
            </c:forEach>
       </table>
</body>
</html>
