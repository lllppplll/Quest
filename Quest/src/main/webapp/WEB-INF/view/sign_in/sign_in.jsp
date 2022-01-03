<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1" />
<title>Sign In</title>
</head>
<body>

<div class="sign_in_box" >Sign In

<a class="title" href="./">Home</a>

<a class="create_account" href="create_account" >Create an account</a>

<form:form  action="process_login" method="POST" >

<input class="email" placeholder="Email" name="username" />

<input type="password" class="password" placeholder="Password" name="password"  /><br/>

<a class="forgotten_password" href="forgotten" >Forgotten Password</a><br/>

<input class="submit_button" type="submit" value="Sign In"/>

</form:form>

</div>

</body>
</html>