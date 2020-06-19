<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "form" uri = "http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>New Question | Dojo Overflow</title>
	</head>
	<body>
		<h1>What is your question?</h1>
		<form:form action="/questions" method="POST" modelAttribute="q">
		<div>
			<form:label path="question">Question: </form:label>
			<form:errors path="question" />
			<form:textarea path="question" />
		</div>
		<div>
			<label>Tags: </label>
			<c:out value="${tagError}" />
			<input name="tagsString" />
		</div>
		<input type="submit" value="Submit" />
		</form:form>
	</body>
</html>