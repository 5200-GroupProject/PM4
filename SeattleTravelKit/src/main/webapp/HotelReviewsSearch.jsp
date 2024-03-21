<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find Hotel Reviews Data</title>
</head>
<body>
    <h1>Search for Hotel Reviews by Cleanliness Rating</h1>
    <form action="findHotelReviews" method="get"> <!-- Changed method to GET -->
        <label for="cleanlinessRating">Cleanliness Rating Above:</label>
        <input id="cleanlinessRating" name="cleanlinessRating" type="number" step="0.1" />
        <input type="submit" value="Search"/>
    </form>
    
    <h2>${messages.success}</h2>
    <c:if test="${not empty hotelIds}">
        <h3>Matching Hotel IDs</h3>
        <ul>
            <c:forEach var="hotelId" items="${hotelIds}">
                <li>${hotelId}</li>
            </c:forEach>
        </ul>
    </c:if>
</body>
</html>
