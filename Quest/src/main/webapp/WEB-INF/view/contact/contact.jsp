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

<div id="email_box" >
<form:form  action="process_contact" method="POST" modelAttribute="contactDTO" >
<input id="send_button_email" type="submit" value="Send"/>
<form:input type="file" id="choose_file"  path="filename" />
<form:input id="to_email" placeholder="To:" path="to" />
<form:input id="subject_email" placeholder="Subject:" path="subject" />
<form:textarea id="body_email" placeholder="Write..." path="body" ></form:textarea>
<!-- <label for="myFile" class="btn">Select Image</label> -->
<!-- style="display:none;" -->
</form:form>
</div>

<%-- <div>Email sent to: <span>${email_sent_to}</span></div> --%>

</body>
</html>