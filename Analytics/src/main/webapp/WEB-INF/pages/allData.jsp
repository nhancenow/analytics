<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="refresh" content="1" >
<title>DashBoard</title>
</head>
<body>
<h2 style="color:#36A8F9">Live Streaming</h2>
	<table>
			<c:forEach var="event" items="${eventList}">
				<tr><td>${event.eventName}</td>
				<td>${event.eventCategory}</td>
				<td>${event.pageId}</td>
				<td>${event.userName}</td>
				<td>${event.emailId}</td>
				<td>${event.mobile}</td>
				<td>${event.ipAddress}</td></tr>
				
			</c:forEach>
	</table>
</body>
</html>