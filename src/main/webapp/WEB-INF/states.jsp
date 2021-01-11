<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>States</title>
<link rel="stylesheet" type="text/css" href="/css/states.css" />
</head>
<body>
	<header><h1>Welcome, <c:out value="${user.name}"></c:out><a href="/logout"><button>Logout</button></a></h1></header>
	<h2>Select a State</h2>
	<div class="table">
	<table>
		<thead>
			<tr>
				<th>
				States
				</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${states}" var="s">
			<tr>
				<td>
					<a href="/state/${s.id}"><c:out value="${s.name}"></c:out></a>
				</td>
			</tr>
	</c:forEach>
		</tbody>
	</table>
	</div>
	<div class="messages">
		<img alt="states" src="https://images-na.ssl-images-amazon.com/images/I/91cM19R8NeL._AC_SL1500_.jpg">
	</div>
</body>
</html>