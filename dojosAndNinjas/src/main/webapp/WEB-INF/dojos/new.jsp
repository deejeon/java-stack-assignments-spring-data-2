<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "form" uri = "http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>New Dojo</title>
	</head>
	<body>
		<h1>New Dojo</h1>
		<form:form action="/dojos" method="POST" modelAttribute="dojo">
			<div>
				<form:label path="name">Dojo Name: </form:label>
				<form:errors path="name"/>
				<form:input path="name"/>
			</div>
			<input type="submit" value="Submit"/>
		</form:form>
	</body>
</html>