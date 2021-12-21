//form validation
let email = document.getElementById("email");
let password = document.getElementById("password");

let emailRequired = document.getElementById("email_required");
let passwordRequiredLength = document.getElementById("password_required_length");

function validate(){
	
	//show * required sentence
	// trim() = remove white spaces
	if(email.value.trim() == ""){ emailRequired.style.display = "block"; }else{
		emailRequired.style.display = "none";
	}
	//password must be 8 - 15 characters long
	if(password.value.trim().length <= 7 || password.value.trim().length >= 16){passwordRequiredLength.style.display = "block"; }else{
		passwordRequiredLength.style.display = "none";
	}
	
	//fields required
	if(email.value == "" || password.value.length <= 7 || password.value.length >= 16){
		
		//clear passord field
		document.getElementById("password").value = '';
		
		return false;	
	}
	else{
		//disables pressing of submit button more than once
		document.getElementById("create_button").disabled = true;
		return true;
	}
	
}