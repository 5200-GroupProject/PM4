<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find AttractionReviews</title>
</head>
<body>
    <form action="findAttractionReviews" method="post">
        <h1>Search for AttractionReviews by Duration Range</h1>
        
        <c:if test="${not empty messages.error}">
    		<div style="color: blue;">
        		${messages.error}
    		</div>
		</c:if>
        
        <p>
            <label for="duration">Reviews with Duration Above:</label>
            <input id="duration" name="duration" value="${fn:escapeXml(param.duration)}" placeholder="Enter duration"> 
           
        </p>
        <p>
            <input type="submit" value="Search">
            <br/><br/><br/>
            <span id="successMessage">${messages.success}</span>
        </p>
    </form>
    <h1>Matching Attraction Reviews</h1>
        <table border="1">
            <tr>
              	<th>ReviewId</th>
                <th>AttractionId</th>
                <th>Duration</th>
            </tr>
            <c:forEach items="${attractionReviews}" var="attractionReview" >
                <tr>
                	<td><c:out value="${attractionReview.reviewId}" /></td>
                    <td><c:out value="${attractionReview.attractionId}" /></td>
                    <td><c:out value="${attractionReview.duration}" /></td>      
                </tr>
            </c:forEach>
       </table>
</body>
</html>

