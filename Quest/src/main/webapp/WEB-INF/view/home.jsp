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

<!-- logout -->
 <form:form action="logout" method="POST" class="nav_bar_sign_in" id="logoutLink_switch" >
 <a class="logout"  onclick="document.getElementById('logoutLink_switch').submit();" >Sign Out</a>
 </form:form>
 <a class="my_account" href="my_account" >My Account</a>
 <a class="contact" href="contact" >Contact</a>
 <br/>
 <a href="key_stage_1" >Key Stage 1</a>
 <a href="#" >Key Stage 2</a>
 <a href="#" >Key Stage 3</a>
 <a href="key_stage_4" >Key Stage 4</a>


<script src="URL/javascript/home.js"></script>
</body>
</html>