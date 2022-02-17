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
<form:input class="subject_email" placeholder="Sunbject:" path="subject" />
<textarea placeholder="Write..." ></textarea>



</form:form>

<div>${email_sent_to}</div>

</body>
</html>