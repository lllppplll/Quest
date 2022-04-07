<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1" />
<title>Insert title here</title>
<link rel="stylesheet" href="URL/css/contact.css">
</head>
<body>
<h1>Contact</h1>

<a id="home_button" href="./">Home</a>

<%-- <div id="email_sent" >Email sent to:</div><div>${email_sent_to}</div>
<div id="email_not_sent" >${email_not_sent}</div> --%>
<br/>
<div id="email_sent" >${email_sent}</div>

<div id="email_box" >
<form:form  action="process_contact" method="POST" modelAttribute="contactDTO" >
<input id="send_button_email" type="submit" value="Send"/>
<br/>
<%-- <form:input type="file" id="choose_file"  onclick="inputField()" path="filename" /> --%>

<form:input id="to_email" placeholder="To:" onclick="inputField()" path="to" />
<form:input id="subject_email" placeholder="Subject:" onclick="inputField()" path="subject" />
<form:textarea id="body_email" placeholder="Write..." onclick="inputField()" path="body" ></form:textarea>
</form:form>
</div>

<%-- <div>Email sent to: <span>${email_sent_to}</span></div> --%>
<script type="text/javascript" src="URL/javascript/contact.js" ></script>
</body>
</html>