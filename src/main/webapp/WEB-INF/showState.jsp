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
			<h2><c:out value="${p.user }"></c:out></h2>
			<h3><c:out value="${p.city}"></c:out></h3>
			<p><c:out value="${p.description}"></c:out></p>
			<c:if test="${p.image != 'add an image'}">
			<img alt="picture" src="${p.image}">
			</c:if>
			<form:form action="/delete/${p.id}/${state.id}" method="delete">
				<button type="submit">Delete</button>
			</form:form>
		
	</div>
			</c:if>
			<c:if test="${user.nickname != p.user}">
	<div class="eachpost">
			<h2><c:out value="${p.user }"></c:out></h2>
			<p><c:out value="${p.description}"></c:out></p>
			<c:if test="${p.image != 'add an image'}">
			<img alt="picture" src="${p.image}">
			</c:if>
			<div id="comments">
			<div class="comments">
			<h3>Comments</h3>
			<table>
				<thead>
					<tr>
						<td>User</td>
						<td>Description</td>
					</tr>
				</thead>
					<tbody>
			<c:forEach items="${comments}" var="c">
			<c:if test="${c.post == p}">
						<tr>
							<td>			
								<c:out value="${c.user.nickname }"></c:out>
							</td>
							<td>			
								<c:out value="${c.description}"></c:out>
							</td>
						</tr>
			</c:if>
			</c:forEach>
					</tbody>
			</table>
			</div>
			</div>
	</div>
	<form:form action="/comment/${state.id}" method="post" modelAttribute="comment">
	<p>
		<form:errors path="description"></form:errors>
		</p>
		<form:label path="description">Description</form:label>
		<form:textarea path="description" column="100" row="50"/>
		<form:input type="hidden" path="user" value="${user.id}"/>
		<form:input type="hidden" path="post" value="${p.id}"/>
		
		<form:button type="submit"> Comment</form:button>
		</form:form>
			</c:if>
			
			</c:if>
		</c:forEach>
	</div>
	<form:form action="/create/post/${state.id}" method="post" modelAttribute="post">
	<p>
		<form:errors path="description"></form:errors>
		</p>
		<p>
		<form:errors path="city"></form:errors>
	</p>
		<form:label path="description">Post</form:label>
		<form:textarea path="description" column="100" row="50"/>
		<form:label path="city">City</form:label>
		<form:input path="city" type="text"></form:input>
		<form:label path="image">Image</form:label>
		<form:input path="image" type="text" value="add an image"></form:input>
		<form:input path="state" value="${state.id}" type="hidden"/>
		<form:input path="user" value="${user.nickname}" type="hidden"/>
		<form:button type="submit"> Create Post</form:button>
		</form:form>
</body>
<script>
 
</script>
</html>