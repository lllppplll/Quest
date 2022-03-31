<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1" />
<title>Quest</title>

<!-- <link rel="shortcut icon" href="URL/image/favicon/favicon.ico"  type="image/x-icon"/> -->

<link rel="stylesheet" href="URL/css/home.css">

</head>
<body>

    <div id="nav_bar" >
	<div id="title" >Quest</div>
	
	<div id="isAdmin" >${admin}</div>
	<a id="admin" href="admin" >Admin</a>

	<a id="contact" href="contact">Email</a>
	<a id="my_account" href="my_account">My Account</a>

	<!-- logout -->
	<div id="signIn" >${signIn}</div>
	<div id="signInOut" >
	<form:form action="logout" method="POST"  id="logout">
		<a class="logout" onclick="document.getElementById('logout').submit();">Sign Out</a>
	</form:form>
	
	<a id="sign_in" href="sign_in">Sign In</a>
	</div>
	</div>
	
	<a id="maths_print" href="KS1_maths_print" >Maths: Print</a>
	<a id="maths_game" href="game" >Maths: Game</a>
	
	
	
<!-- 	<div id="main_box" >
	<h1>Revision: Print</h1>
	
	<div id="key_stage_boxes" >
	<button id="KS1_button" onclick="KS1()">Key
		Stage 1</button>
	<button id="KS2_button">Key Stage 2</button>
	<button id="KS3_button">Key Stage 3</button>
	<button id="KS4_button">Key Stage 4</button>
	</div>
	</div>
	<br/><br/>
	<div id="KS1_subjects" >
		<h1>Key Stage 1: Subjects:</h1>
		<a id="maths" href="KS1_maths_print" >Maths</a>
		<a href="#" >English</a>
		<a href="#" >Science</a>
	</div>
	
	<div id="main_box1" >
	<h1>Games:</h1>
	
	<div id="key_stage_boxes1" >
	<button id="KS1_button_game" onclick="KS1Game()">Key
		Stage 1</button>
	<button id="KS2_button">Key Stage 2</button>
	<button id="KS3_button">Key Stage 3</button>
	<button id="KS4_button">Key Stage 4</button>
	</div>
	</div>
	<br/><br/> -->
	
	
<!-- <div>footer</div> -->

<script src="URL/javascript/key_stage_1.js"></script>
<script src="URL/javascript/home.js"></script>
</body>
</html>