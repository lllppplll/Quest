<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1" />

<title>Forgotten Password</title>
<link rel="stylesheet" href="URL/css/forgotten_password.css">
</head>
<body>
<h1 class="main_title" >Forgotten Password</h1>

<br/>
<a class="title" href="./sign_in">Back</a>
<br/>
<br/>

<div id="password_box" >
<h3 class="sub_title" >Enter Your Email Address:</h3>


<form:form action="forgotten_password_submit" method="POST" modelAttribute="forgottenPasswordDTO" >

<form:input class="boxes"  placeholder="Email" path="email"/>
<br/><br/>
<input id="reset"  type="submit" value="Reset" />

</form:form>
<div id="email_error" >${emailError}</div>
<div>${tokenNotValid}</div>
</div>


</body>
</html>