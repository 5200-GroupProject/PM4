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

<form action="findUsers" method="post">
	<img src="https://imgur.com/NnMj65o.png" alt="Seattle Travel Kit">
	<h1>Welcome to Seattle Travel Kit</h1>
	<h3>Your One-Stop Compass for Exploring the Evergreen City</h3>
	<button type="button" onclick="window.location='findCreditCards';">Credit Cards</button>
	<button type="button" onclick="window.location='findAttractions';">Attractions</button>
	<button type="button" onclick="window.location='findRestaurants';">Restaurants</button>
	<button type="button" onclick="window.location='findHotels';">Hotels</button>
	<button type="button" onclick="window.location='findCrimes';">Crimes</button>
	
	<button type="button" onclick="window.location='findAttractionReviews';">Attraction Reviews</button>
	<button type="button" onclick="window.location='findRestaurantReviews';">Restaurant Reviews</button>
	<button type="button" onclick="window.location='findHotelReviews';">Hotel Reviews</button>
	<hr>
    <h2>Search for User by UserName</h2>
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
<div id="userCreate"><a href="userCreate">Create User</a></div>
<br/>

<h2>Matching User</h2>
<c:choose>
    <c:when test="${not empty user}">
        <table border="1">
            <tr>
                <th>UserName</th>
                <th>Email</th>
                <th>Password</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Phone</th>
                <th>Delete User</th>
                <th>Update User</th>
            </tr>
            <tr>
                <td><c:out value="${user.userName}" /></td>
                <td><c:out value="${user.email}" /></td>
                <td><c:out value="${user.password}" /></td>
                <td><c:out value="${user.firstName}" /></td>
                <td><c:out value="${user.lastName}" /></td>
                <td><c:out value="${user.phone}" /></td>
                <td><a href="userDelete?username=${user.userName}">Delete</a></td>
                <td><a href="userUpdate?username=${user.userName}">Update</a></td>
            </tr>
        </table>
    </c:when>
    <c:otherwise>
        <p>No User Found.</p>
    </c:otherwise>
</c:choose>
</body>
</html>

