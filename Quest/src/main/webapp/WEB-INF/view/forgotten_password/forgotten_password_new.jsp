<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1" />
<title>Insert title here</title>
</head>
<body>

<!--  onsubmit="return validate()" -->

<h1 class="main_title" >Reset Password</h1>
<!--  Two boxes-->
<form:form  onsubmit="return false" action="saveNewPassword" method="POST" modelAttribute="forgottenPasswordNewDTO" >
<form:password id="password1" class="boxes"  placeholder="New Password" path="newPassword"/>
<form:errors path="newPassword" />
<br/>
<form:password id="password2" class="boxes"  placeholder="Confirm Password" path="confirmPassword"/>
<form:errors path="confirmPassword" />
<br/>
<form:input class="email" value="${email}" path="email"/>
<form:input class="email" value="${token}" path="token"/>
<!--  Submit-->
<input class="boxes"  type="submit" value="Reset" />
</form:form>

<div>${passwordsNotMatch}</div>

<%-- 

<h1 class="main_title" >Reset Password</h1>
<!--  Two boxes-->
<form:form  onsubmit="return validate()" action="saveNewPassword" method="POST" modelAttribute="changePassword" >

<form:password id="password1" class="boxes"  placeholder="New Password" path="newPassword"/>
<p id="passwordReqLen1">* Password must be 8 - 15 characters</p>
<form:errors class="password_length_error" path="newPassword" />
<br/>
<form:password id="password2" class="boxes"  placeholder="Confirm Password" path="confirmPassword"/>
<p id="passwordReqLen2">* Password must be 8 - 15 characters</p>
<form:errors class="password_length_error" path="confirmPassword" />
<br/>
<form:input class="email" value="${email}" path="email"/>
<form:input class="token" value="${param.token}" path="token"/>


<!--  Submit-->
<input class="boxes"  type="submit" value="Reset" />

</form:form> --%>
<script src="URL/javascript/forgotten_password_new.js"></script>
</body>
</html>