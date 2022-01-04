<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1" />
<title>Home</title>

<link rel="shortcut icon" href="URL/image/favicon/favicon.ico"  type="image/x-icon"/>

<link rel="stylesheet" href="URL/css/home.css">

</head>
<body>

<h1>Quest</h1>
<a class="sign_in" href="sign_in" >Sign In</a>

 <form:form action="logout" method="POST" class="nav_bar_sign_in" id="logoutLink_switch" >
 <a class="logout"  onclick="document.getElementById('logoutLink_switch').submit();" >Sign Out</a>
 </form:form>

<script src="URL/javascript/home.js"></script>
</body>
</html>