<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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

	<a href="./">Home</a>

	<form:form   action="create_account_submit" modelAttribute="createAccountDTO" method="POST">
		<table>
			<tr>
				<td><form:input id="email" placeholder="Email" path="email" /></td>
			</tr>
			<tr>
				<td><span id="email_required">* Email required js</span>
				    <form:errors class="email_error" path="email" />
				</td>			
			</tr>
			<tr>
				<td><form:password id="password" placeholder="Password" path="password" /></td>
			</tr>
			<tr>
				<td><span id="password_required_length">* Password must be 8 - 15 characters js</span>
				    <form:errors class="password_length_error" path="password" />
				</td>
			</tr>
			<tr>
				<td><input id="create_button" type="submit" value="Submit" /></td>
			</tr>
		</table>
	</form:form>
	
<script src="URL/javascript/create_account.js"></script>
</body>
</html>