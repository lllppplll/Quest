/*alert("js working");*/

/*if(isnotmatch.innerHTML == "1"){
	document.getElementById("no_match_error").style.display = "inline-block";
}*/

//form validation
let password_error1 = document.getElementById("password_error1");
let password_error2 = document.getElementById("password_error2");
let no_match_error = document.getElementById("no_match_error");

function validate(password1, password2){
//password must be 8 - 15 characters long
	if(password1 <= 7 || password1 >= 16){
		password_error1.style.display = "block"; }else{
		password_error1.style.display = "none";
	}
	
	//password must be 8 - 15 characters long
	if(password2 <= 7 || password2 >= 16){
		password_error2.style.display = "block"; }else{
		password_error2.style.display = "none";
	}
	
	if((password1 >= 8 && password1 <= 15) && (password2 >= 8 && password2 <= 15) && password1 != password2){
	    no_match_error.style.display = "block"; }else{
		no_match_error.style.display = "none";
	}
	
	//fields required
	if(password1 <= 7 || password1 >= 16
	   || password2 <= 7 || password2 >= 16 
	   || password1 != password2) {
		
		return false;	
	}
	else{
		
		return true;
	}
	
	}