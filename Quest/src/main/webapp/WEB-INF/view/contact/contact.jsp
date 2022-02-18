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
<h1>Contact</h1>

<a class="title" href="./">Home</a>
<br/>

<form:form  action="process_contact" method="POST" modelAttribute="contactDTO" >
<input class="send_button_email" type="submit" value="Send"/>
<form:input class="to_email" placeholder="To:" path="to" />
<form:input class="subject_email" placeholder="Subject:" path="subject" />
<form:textarea placeholder="Write..." path="body" ></form:textarea>
<!-- <label for="myFile" class="btn">Select Image</label> -->
<!-- style="display:none;" -->
<form:input type="file" id="myFile"  path="filename" />
</form:form>
<br/>
<div>Email sent to: <span>${email_sent_to}</span></div>

</body>
</html>