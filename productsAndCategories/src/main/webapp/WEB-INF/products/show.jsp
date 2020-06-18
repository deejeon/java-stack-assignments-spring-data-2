<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "form" uri = "http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title><c:out value="${currentProduct.name}"/></title>
	</head>
	<body>
		<h1><c:out value="${currentProduct.name}"/></h1>
		<h3>Categories</h3>
		<ul>
			<c:forEach items="${currentProductCategories}" var="category">
			<li><c:out value="${category.name}"/></li>
			</c:forEach>
		</ul>
		
		<form action="/products/${currentProduct.id}" method="POST">
			<div>
				<label for="addedCategory">Add Category: </label>
				<select name="addedCategory">
					<option value="" disabled selected>Select a Category</option>
					<c:forEach items="${otherCategories}" var="category">
					<option value="${category.id}"><c:out value="${category.name}"/></option>
					</c:forEach>
				</select>
			</div>
			<input type="submit" value="Add"/>
		</form>
	</body>
</html>