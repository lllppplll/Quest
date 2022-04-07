/*function sent(){
	document.getElementById("sent").style.display = "block";
}*/

/*let emailSent = document.getElementById("email_sent_to").innerText;
let emailNotSent = document.getElementById("email_not_sent").innerText

if(emailSent == true){
	document.getElementById("email_sent_to").style.display = "block";
}else{
	document.getElementById("email_sent_to").style.display = "none";
}
if(emailNotSent == false){
	document.getElementById("email_not_sent").style.display = "block";
}else{
	document.getElementById("email_not_sent").style.display = "none";
}*/

let emailSent = document.getElementById("email_sent").innerText;

if(emailSent == "true"){
	document.getElementById("email_sent").style.display = "block";
	document.getElementById("email_sent").innerHTML = "Message Sent &#10003;";
	document.getElementById("to_email").value = "";
	document.getElementById("subject_email").value = "";
	document.getElementById("body_email").value = "";
	
}
if(emailSent == "false"){
	document.getElementById("email_sent").style.display = "block";
	document.getElementById("email_sent").style.color = "red";
	document.getElementById("email_sent").innerHTML = "'To' field required &#10060;";
	
}

function inputField(){
	document.getElementById("email_sent").style.display = "none";
}