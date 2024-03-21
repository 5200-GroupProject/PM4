<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find a Restaurant</title>
</head>
<body>
    <form action="findRestaurants" method="post">
        <h1>Search for a Restaurant by ZipCode</h1>
        <p>
            <label for="zipcode">ZipCode:</label>
            <input id="zipcode" name="ZipCode" value="${fn:escapeXml(param.ZipCode)}" placeholder="Enter zip code">
        </p>
        <p>
            <input type="submit" value="Search">
            <br/><br/><br/>
            <span id="successMessage">${messages.success}</span>
        </p>
    </form>
    <h1>Matching Restaurants</h1>
        <table border="1">
            <tr>
               	<th>Restaurant Id</th>
            	<th>Restaurant Name</th>
                <th>Address</th>
                <th>Rating</th>
                <th>Area</th>
                <th>Category</th>
                <th>Service</th>
                <th>ZipCode</th>
            </tr>
            <c:forEach items="${restaurants}" var="restaurant" >
                <tr>
                	<td><c:out value="${restaurant.restaurantId}" /></td>
                    <td><c:out value="${restaurant.restaurantName}" /></td>
                    <td><c:out value="${restaurant.address}" /></td>
                    <td><c:out value="${restaurant.rating}" /></td>
                    <td><c:out value="${restaurant.area}" /></td>
                    <td><c:out value="${restaurant.category}" /></td>
                    <td><c:out value="${restaurant.service}" /></td>
                    <td><c:out value="${restaurant.zipCode}" /></td>                
                </tr>
            </c:forEach>
       </table>
</body>
</html>
