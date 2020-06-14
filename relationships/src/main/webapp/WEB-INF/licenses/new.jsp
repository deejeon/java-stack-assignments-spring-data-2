<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "form" uri = "http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>New License</title>
	</head>
	<body>
		<h1>New License</h1>
		<form:form action="/licenses" method="POST" modelAttribute="license">
			<div>
				<form:label path="person.id">Person: </form:label>
				<form:errors path="person.id"/>
				<form:select path="person.id">
					<c:forEach items="${persons}" var="person">
					<form:option value="${person.id}"><c:out value="${person.firstName}"/> <c:out value="${person.lastName}"/></form:option>
					</c:forEach>
				</form:select>
			</div>
			<div>
				<form:label path="state">State: </form:label>
				<form:errors path="state"/>
				<form:input path="state"/>
			</div>
			<div>
				<form:label path="expirationDate">Expiration Date: </form:label>
				<form:errors path="expirationDate"/>
				<form:input type="date" path="expirationDate"/>
			</div>
			<input type="submit" value="Submit"/>
		</form:form>
	</body>
</html>