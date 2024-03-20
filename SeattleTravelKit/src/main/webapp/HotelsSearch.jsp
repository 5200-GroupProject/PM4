<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find a Hotel</title>
</head>
<body>
    <form action="findHotels" method="post">
        <h1>Search for a Hotel by ZipCode</h1>
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
    <h1>Matching Hotels</h1>
        <table border="1">
            <tr>
           	    <th>HotelId</th>
                <th>HotelName</th>
                <th>Rating</th>
                <th>Website</th>
                <th>Phone</th>
                <th>Details</th>
                <th>Address</th>
                <th>City</th>
                <th>ZipCode</th>
            </tr>
            <c:forEach items="${hotels}" var="hotel" >
                <tr>
                	<td><c:out value="${hotel.hotelId}" /></td>
                    <td><c:out value="${hotel.hotelName}" /></td>
                    <td><c:out value="${hotel.rating}" /></td>
                    <td><a href="${hotel.website}">Website</a></td>
                    <td><c:out value="${hotel.phone}" /></td>
                    <td><c:out value="${hotel.details}" /></td>
                    <td><c:out value="${hotel.address}" /></td>
                    <td><c:out value="${hotel.city}" /></td>
                    <td><c:out value="${hotel.zipCode}" /></td>
                </tr>
            </c:forEach>
       </table>
</body>
</html>
