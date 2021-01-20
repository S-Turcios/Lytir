<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="/css/registration.css" />
<title>Lytir Registration</title>
</head>
<body>
<h1>Welcome to Lytir Registration</h1>
	<p class="login">Already have an account click here <a href="/">Login</a> to login.</p>
    <div class="border">
<p class="errors"><form:errors path="user.*"/></p>
    <form:form class="form" method="POST" action="/registration" modelAttribute="user">
    	<p>
    		<form:label path="name">Name:</form:label>
    		<form:input Type="text" path="name"></form:input>
 
    		<form:label path="nickname">NickName:</form:label>
    		<form:input Type="text" path="nickname"></form:input>
    	</p>
        <p>
            <form:label path="email">Email:</form:label>
            <form:input path="email"/>
        </p>
        <p>
            <form:label path="password">Password:</form:label>
            <form:password path="password"/>
       
            <form:label path="passwordConfirmation">Password Confirmation:</form:label>
            <form:password path="passwordConfirmation"/>
        </p>
        <input type="submit" value="Register!"/>
    </form:form>
    </div>
<p class="intro">Lytir is an interactive site for people to share with others about the places they have been to and seen!</p>
</body>
</html>