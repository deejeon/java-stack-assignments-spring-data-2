<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "form" uri = "http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Question | Dojo Overflow</title>
	</head>
	<body>
		<a href="/questions">Dashboard</a>
		<h1><c:out value="${question.question}"/></h1>
		<h3>Tags</h3>
		<ul>
			<c:forEach items="${question.tags}" var="tag">
			<li><c:out value="${tag.subject}" /></li>
			</c:forEach>
		</ul>
		
		<h3>Answers</h3>
		<ul>
			<c:forEach items="${answers}" var="a">
			<li><c:out value="${a.answer}"/></li>
			</c:forEach>
		</ul>
		
		<h3>Add your answer:</h3>
		<form:form action="/answers" method="POST" modelAttribute="newAns">
			<input type="hidden" name="questionId" value="${question.id}"/>
			<div>
				<form:label path="answer">Answer: </form:label>
				<form:errors path="answer"/>
				<form:textarea path="answer"/>
			</div>
			<input type="submit" value="Answer it!"/>
		</form:form>
	</body>
</html>