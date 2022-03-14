<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>

<link rel="stylesheet" href="URL/css/create_account.css">

<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1" />

<title>Create Account</title>
</head>
<body>
<h1 id="create_title" >Create an Account</h1>

	<a class="title" href="./sign_in">Back</a>
	<br/>
<br/>

<div id="create_form" >
	<form:form onsubmit="return validate_create_account(document.getElementById('email').value.trim(), document.getElementById('password').value.trim().length)" action="create_account_submit" modelAttribute="createAccountDTO" method="POST">
	
		<form:input id="email" placeholder="Email" path="email" />

		<p id="email_required">* Email required</p>
	    <form:errors class="email_error" path="email" />
	
	    <form:password id="password" placeholder="Password" path="password" />

		<p id="password_required_length">* Password must be 8 - 15 characters</p>
		<form:errors class="password_length_error" path="password" />
	
		<input id="create_button" type="submit" value="Submit" />
		
	</form:form>
	<p>${returnMessage}</p>
	<p>${isEmail}</p>
	</div>

	
<script src="URL/javascript/create_account.js"></script>
</body>
</html>