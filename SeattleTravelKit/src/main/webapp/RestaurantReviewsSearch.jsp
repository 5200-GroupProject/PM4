<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find Restaurant Reviews Data</title>
</head>
<body>
    <h1>Search for Restaurant Reviews by Food Quality Rating</h1>
    <form action="findRestaurantReviews" method="get"> <!-- Ensure this matches your servlet's URL pattern -->
        <label for="foodQualityRating">Food Quality Rating Above:</label>
        <input id="foodQualityRating" name="foodQualityRating" type="number" step="0.1" />
        <input type="submit" value="Search"/>
    </form>
    
    <c:if test="${not empty messages.success}">
        <h2>${messages.success}</h2>
    </c:if>
    
    <c:if test="${not empty restaurantIds}">
        <h3>Matching Restaurant IDs</h3>
        <ul>
            <c:forEach var="restaurantId" items="${restaurantIds}">
                <li>${restaurantId}</li>
            </c:forEach>
        </ul>
    </c:if>
</body>
</html>
