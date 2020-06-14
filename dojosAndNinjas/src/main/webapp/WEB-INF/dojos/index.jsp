<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "form" uri = "http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>All Dojos</title>
	</head>
	<body>
		<h1>All Dojos</h1>
		<table>
			<thead>
				<tr>
					<th>Dojo Name</th>
					<th>Ninja Count</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${dojos}" var="dojo">
				<tr>
					<td><c:out value="${dojo.name}"/></td>
					<td><c:out value="${dojo.ninjas.size()}"/></td>
					<td><a href="/dojos/${dojo.id}">View</a></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		<a href="/dojos/new">New Dojo</a> | <a href="/ninjas/new">New Ninja</a>
	</body>
</html>