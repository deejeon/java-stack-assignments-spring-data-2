<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "form" uri = "http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>All Persons</title>
	</head>
	<body>
		<h1>All Persons</h1>
		<table>
			<thead>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>License Number</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${persons}" var="person">
				<tr>
					<td><c:out value="${person.firstName}"/></td>
					<td><c:out value="${person.lastName}"/></td>
					<td><c:out value="${person.license.number}"/></td>
					<td><a href="/persons/${person.id}">View</a></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		<a href="/persons/new">New Person</a> | <a href="/licenses/new">New License</a>
	</body>
</html>