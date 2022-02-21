/*alert("javascript page is working");*/

function KS1(){
	document.getElementById("KS1_subjects").style.height = "120px";
	document.getElementById("KS1_subjects").style.overflow = "show";
}

/*let toggle = false;
let section_array = ["about_me_section", "projects_section", "contact_section"];

//toggles menu on and off
function menu(){
	
	if(toggle == false){
		document.getElementsByClassName("nav_bar_boxB")[0].style.display = "block";
		document.getElementsByClassName("name")[0].style.transform = "translate(-90px, -180px)";
		document.getElementsByClassName("job_title")[0].style.transform = "translate(-90px, -200px)";
		//lanscape mode extend head colour when button is pressed
		if(window.orientation == 90){
			document.getElementsByClassName("head")[0].style.height = "640px";
		}
		
		}
	if(toggle == true){
		location.reload();
		}
		
		toggle = !toggle;
}

//colapses menu bar when resized screen
function size(){
let width = window.innerWidth;
if(width >= 720 && toggle == true){
	location.reload();
}
}
///////////////////////////////////////

//move (smooth) to different sections when nav bar buttons pushed 
function main_nav_bar(x){
	document.getElementById(section_array[x]).scrollIntoView({behavior: "smooth", block: "start", inline: "nearest"});
}
function sub_nav_bar(x){
	document.getElementById(section_array[x]).scrollIntoView({behavior: "smooth", block: "start", inline: "nearest"});
}
///////////////////////////////////////

let fadeInElement = document.getElementById("fadeInAboutMe");
//fade in and scroll in
/////////////////////////
//event listener when scrolling 
window.addEventListener("scroll", scrollingAboutMeParagraph);

function scrollingAboutMeParagraph(){
	
	//get device size of window height
	let windowHeight = window.innerHeight;
	//get where the top of element begins
	let revealTop = fadeInElement.getBoundingClientRect().top;
	//how far past bottom of screen when element should come fade in
	let revealPoint = -100;
	
	//add a class(css-variable) to an element called class="active"
	if(revealTop < (windowHeight - revealPoint)){
		fadeInElement.classList.add("AboutMeFadeIn");
		//remove event listener
		window.removeEventListener("scroll", scrollingAboutMeParagraph);
	}
	else{
		fadeInElement.classList.remove("AboutMeFadeIn");
	}
	
	
	
}*/




