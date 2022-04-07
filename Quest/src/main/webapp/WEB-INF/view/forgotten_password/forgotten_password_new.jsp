<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1" />
<title>Insert title here</title>
<link rel="stylesheet" href="URL/css/forgotten_password_new.css">
</head>
<body>

<!--  onsubmit="return validate()" -->

<h1 class="main_title" >Reset Password</h1>

<!--  Two boxes-->
<form:form  onsubmit="return validate_forgotten_password_new(document.getElementById('password1').value.trim().length, document.getElementById('password2').value.trim().length)" action="reset-password" method="POST" modelAttribute="forgottenPasswordNewDTO" >
<form:password id="password1" class="boxes"  placeholder="New Password" path="newPassword"/>
<p id="password_error1">* Password must be 8 - 15 characters</p>
<form:errors path="newPassword" />
<br/>
<form:password id="password2" class="boxes"  placeholder="Confirm Password" path="confirmPassword"/>
<p id="password_error2">* Password must be 8 - 15 characters</p>
<form:errors path="confirmPassword" />

<form:input class="email" value="${email}" path="email"/>
<br/>
<!--  Submit-->
<input id="reset_submit" class="boxes"  type="submit" value="Reset" />
</form:form>

<p id="no_match_error" >Passwords do not match. Please try again.</p>
<div>${passwordsNotMatch}</div>


<script src="URL/javascript/forgotten_password_new.js"></script>

</body>
</html>