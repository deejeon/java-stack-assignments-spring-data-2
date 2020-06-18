<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "form" uri = "http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title><c:out value="${currentCategory.name}"/></title>
	</head>
	<body>
		<h1><c:out value="${currentCategory.name}"/></h1>
		<h3>Products</h3>
		<ul>
			<c:forEach items="${currentCategoryProducts}" var="product">
			<li><c:out value="${product.name}"/></li>
			</c:forEach>
		</ul>
		
		<form action="/categories/${currentCategory.id}" method="POST">
			<div>
				<label for="addedProduct">Add Product: </label>
				<select name="addedProduct">
					<option value="" disabled selected>Select a Product</option>
					<c:forEach items="${otherProducts}" var="product">
					<option value="${product.id}"><c:out value="${product.name}"/></option>
					</c:forEach>
				</select>
			</div>
			<input type="submit" value="Add"/>
		</form>
	</body>
</html>