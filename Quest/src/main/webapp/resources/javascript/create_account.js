//form validation
let email = document.getElementById("email");
let password = document.getElementById("password");

let emailRequired = document.getElementById("email_required");
let passwordRequiredLength = document.getElementById("password_required_length");

//Disable sumbit button if form is valid
let disableButton = document.getElementById("create_button");

function validate(emailInput, passwordInput){
	
	//Validation
	//Email Validation - not blank
	emailMessage = emailValidation(emailInput);
	//Password Validation - password must be 8 - 15 characters long
	passwordMessage = passwordValidation(passwordInput);
	
    //DOM Changes
	DOMemail(emailMessage);
	DOMpassword(passwordMessage);
	//All feilds required to be true
	return formValidation(emailMessage, passwordMessage);
	
	}
	
	//Email Validation
function emailValidation(emailInput){
	 if(emailInput == ""){ message = "block"; } else { message = "none" }
	 return message;
	}

function passwordValidation(passwordInput){	
	if(passwordInput <= 7 || passwordInput >= 16){ message = "block"; } else { message = "none" }
	return message;
	}
	
	//Form Validation
function formValidation(email, password){
	 if(email == "none" && password == "none"){ 
		valid = true; 
		DOMbutton(valid);
		} 
		else { 
			valid = false; 
			}
		return valid;
			}
			
function DOMemail(eMessage){
	 document.getElementById("email_required").style.display = eMessage;
}
function DOMpassword(pMessage){
	 document.getElementById("password_required_length").style.display = pMessage;
}
function DOMbutton(valid){
	document.getElementById("create_button").disabled = valid;
}



/*function pass(z,k){
	
	loadResources(z,k)
}
function loadResources(x,y){
	document.getElementById('1').style.display = x;
	//document.getElementById('2').style.display = y;
	//document.getElementById('M').style.display = y;
}*/
				
/*function e(value){
	document.getElementById("new").value = value;
}*/

/*function validateIsValid(){
	 if(returnValid(email.value.trim(), password.value.trim().length)){
		disableButton.disabled = true;
		return true; }
			else{
				return false;
			}
			}*/
			
/*function returnValid(valueEmail, valuePassword){
	if(valueEmail == "" || valuePassword <= 7 || valuePassword >= 16){ 
		return false; }
		else{			
			return true;
			}
			}*/
			/////










//simple test (add)
/*function add(a, b){
	return a + (b+a);
}*/

/*function loadResources(){

//a = document.getElementById('email').value.trim().length;
b = document.getElementById("email_required").style;
}*/
	
	//emailRequired.style.display= (email.value.trim() == "") ? "block" : "none";
	//emailRequired.style.display = showMessage(email.value.trim());
	//emailRequired.style.display = email.value.trim();
	//Password Validation - password must be 8 - 15 characters long
//	passwordRequiredLength.style.display = 
//(password.value.trim().length <= 7 || password.value.trim().length >= 16) ? "block" : "none";
	
	//return validateTrueOrFalse();

//}

/*function validateTrueOrFalse(){
let disableButton = document.getElementById("create_button");
	
	//if true, disable pressing of submit button more than once
	if(email.value.trim() == "" || password.value.trim().length <= 7 || password.value.trim().length >= 16){ 
		return false; }
		
		disableButton.disabled = true;
		return true;
}*/