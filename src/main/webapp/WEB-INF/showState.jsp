<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>State</title>
<link rel="stylesheet" type="text/css" href="/css/showState.css" />
</head>
<body>
	<header><p><c:out value="${state.name}"></c:out></p></header>
	<a href="/states">Back to states</a>
	<div class="post">
		<c:forEach items="${allPost}" var="p">
			<c:set var="one_p" value="p"></c:set>
			<c:if test="${p.state== state}">
			<p class="seperation"></p>
			<c:if test="${user.nickname == p.user}">
	<div class="userpost">
			<p><c:out value="${p.city}"></c:out></p>
			<p><c:out value="${p.description}"></c:out></p>
			<p><c:out value="${p.user }"></c:out></p>
			<c:if test="${p.image != null}">
			<img alt="picture" src="${p.image}">
			</c:if>
	</div>
			</c:if>
			<c:if test="${user.nickname != p.user}">
	<div class="eachpost">
			<p><c:out value="${p.description}"></c:out></p>
			<p><c:out value="${p.user }"></c:out></p>
			<c:if test="${p.image != null}">
			<img alt="picture" src="${p.image}">
			</c:if>
	</div>
			</c:if>
			</c:if>
		</c:forEach>
	</div>
	<form:form action="/create/post" method="post" modelAttribute="post">
		<form:label path="description">Post</form:label>
		<form:textarea path="description" column="100" row="50"/>
		<form:label path="city">City</form:label>
		<form:input path="city" type="text"></form:input>
		<form:label path="image">Image</form:label>
		<form:input path="image" type="text"></form:input>
		<form:input path="state" value="${state.id}" type="hidden"/>
		<form:input path="user" value="${user.nickname}" type="hidden"/>
		<form:button type="submit"> Create Post</form:button>
		</form:form>
</body>
</html>