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

<div id="sign_in_box" >Sign In

<a id="title" href="./">Home</a>
<br/>
<a id="create_account" href="create_account" >Create Account</a>

<br/>

<form:form  action="process_login" method="POST" >
<input type="email" id="email" placeholder="Email" name="username" />
<input type="password" id="password" placeholder="Password" name="password"  /><br/>
<input id="submit_button" type="submit" value="Sign In"/>
</form:form>


<%-- <form:form  action="process_login" method="POST" modelAttribute="signInDTO" >
<form:input class="email" placeholder="Email" path="email" name="username"/>
<form:password class="password" placeholder="Password" path="password" name="password" />
<input class="submit_button" type="submit" value="Sign In"/>
</form:form> --%>

<br/>
<a id="forgotten_password" href="forgotten_password" >Forgotten Password</a><br/>
</div>

</body>
</html>