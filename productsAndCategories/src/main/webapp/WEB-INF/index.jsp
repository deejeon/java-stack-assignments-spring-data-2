<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "form" uri = "http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Products and Categories</title>
	</head>
	<body>
		<h1>Products</h1>
		<a href="/products/new">New Product</a>
		<table>
			<thead>
				<tr>
					<th>Name</th>
					<th>Description</th>
					<th>Price</th>
					<th>Category Count</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${products}" var="product">
				<tr>
					<td><a href="/products/${product.id}"><c:out value="${product.name}"/></a></td>
					<td><c:out value="${product.description}"/></td>
					<td>$<c:out value="${product.price}"/></td>
					<td><c:out value="${product.categories.size()}"/></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<h1>Categories</h1>
		<a href="/categories/new">New Category</a>
		<table>
			<thead>
				<tr>
					<th>Name</th>
					<th>Product Count</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${categories}" var="category">
				<tr>
					<td><a href="/categories/${category.id}"><c:out value="${category.name}"/></a></td>
					<td><c:out value="${category.products.size()}"/></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</body>
</html>