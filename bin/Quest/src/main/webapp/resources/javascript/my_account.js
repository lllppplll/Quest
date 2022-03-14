/*alert("js working");*/

function updateInformation(x){
	sessionStorage.setItem("updated", x);
}

//check if updated message is needed when page refreshes.
if(location.search == "?update=true"){
	document.getElementsByClassName("updateInformation")[sessionStorage.getItem("updated")].style.display = "inline-block";
}