<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1" />
<title>My Account</title>
<link rel="stylesheet" href="URL/css/my_account.css">
</head>
<body>
<h1>My Account</h1>
<a class="title" href="./">Home</a>

<br/><br/>

<form:form action="update_my_account" method="POST" modelAttribute="myAccountDTO" >

<table class="Information_table" >

<tr><td>Name:</td><td><form:input type="text" placeholder="firstname" value="${userInformation.firstname}" path="firstname" /></td></tr>
<tr><td></td><td><form:input type="text" placeholder="surname" value="${userInformation.surname}" path="surname" /></td><td><input class="submitButton" type="submit" value="Update" onclick="updateInformation(0)" /></td></tr>
<tr><td></td><td class="updateInformation" >Updated!</td></tr>

<tr><td>Address:</td>

<td class="table_data" ><form:input type="text" placeholder="house name/no." value="${userInformation.housename}" path="housename" /></tr>

<tr><td></td><td><form:input type="text" placeholder="address1" value="${userInformation.address1}" path="address1" /></td></tr>
<tr><td></td><td><form:input type="text" placeholder="address2" value="${userInformation.address2}" path="address2" /></td></tr>
<tr><td></td><td><form:input type="text" placeholder="town/city" value="${userInformation.town}" path="town" /></td></tr>
<tr><td></td><td><form:input type="text" placeholder="postcode" value="${userInformation.postcode}" path="postcode" /></td><td><input class="submitButton" type="submit" value="Update" onclick="updateInformation(1)"/></td></tr>
<tr><td></td><td class="updateInformation">Updated!</td></tr>

<tr><td>Phone Number:</td><td><form:input type="text" placeholder="number" value="${userInformation.phonenumber}" path="phonenumber" /></td><td><input class="submitButton" type="submit" value="Update" onclick="updateInformation(2)"/></td></tr>
<tr><td></td><td class="updateInformation" >Updated!</td></tr>

<tr><td>Date Of Birth:</td><td><form:input type="text" placeholder="dd/mm/yyyy" value="${userInformation.dob}" path="dob" /></td><td><input class="submitButton" type="submit" value="Update" onclick="updateInformation(3)"/></td></tr>
<tr><td></td><td class="updateInformation">Updated!</td></tr>
</table>
</form:form>

<script src="URL/javascript/my_account.js"></script>
</body>
</html>