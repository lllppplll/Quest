<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, minimum-scale=1" />

<title>Create Account</title>
</head>
<body>

<a href="./" >Home</a>

	<form:form action="create_account_submit" modelAttribute="createAccountDTO" method="POST">
		<table>
			<tr>
				<td><form:input placeholder="Email" path="email" /></td>
			</tr>
			<tr>
				<td><form:password placeholder="Password" path="password" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="Submit" /></td>
			</tr>
		</table>
	</form:form>
	
</body>
</html>