let count = 0;
let countdownTen = 9;

let tenTwenty;
let addSubtract;
let addSubtractNumber;
let randomNumberMaxMinA;
let randomNumberMaxMinB;

let onOffTimer;

const questionA = document.getElementById("questionA");
const questionB = document.getElementById("questionB");
const questionSign = document.getElementById("questionSign");

// radio buttons for 10,  20, add, subtract
let ten = document.getElementById("ten");
let twenty = document.getElementById("twenty");
let add = document.getElementById("add");
let subtract = document.getElementById("subtract");

let backgroundStep = ["step1", "step2", "step3", "step4"];
let pixelStep;
let screenWidth;
let correctStepArray;


function instructions(){
	document.getElementsByClassName("instructions_screen")[0].style.display = "none";
	
	document.getElementById("options_start_screen").style.visibility = "visible";
	document.getElementById("options_start_screen").style.animation = "startBox 0.5s 1";
}

/*detects change in orientation of the screen and changes the step length to match different screen widths, 
and place sprite at bottom after fallen*/
window.addEventListener("orientationchange", function() {
	
	/*portrait*/
	if(screen.orientation.angle == 0){
		/*move sprite step position*/
		pixelStep = ["14.8%", "29.6%", "44.35%", "59.35%", "74.35%"];
		document.getElementById("sprite_box").style.left = pixelStep[count-1];
		
		/*when game over screen appears move srite into position*/
		if(document.getElementById("fallen_screen").style.display == "inline-block"){
		     /*move sprite position*/
			 document.getElementsByClassName("sprite_box")[0].style.top = "16vh";
	    }
		}
		/*lanscape*/
		if(screen.orientation.angle == 90){
			/*move sprite step position*/
			pixelStep = ["15.6%", "31%", "47%", "62.2%", "77.6%"];
			document.getElementById("sprite_box").style.left = pixelStep[count-1];
			
		/*when game over screen appears move srite into position*/
		if(document.getElementById("fallen_screen").style.display == "inline-block"){
			   /*move sprite position*/
		       document.getElementsByClassName("sprite_box")[0].style.top = "37.4vh";
			}
			}
		});

function correctStep(){
	
	/*gets screen width as to apply the step length on different screens*/
/*	let screenWidth = document.getElementById("screen-width").innerHTML = screen.width;*/
	
	correctStepArray = ["spriteMove1 1.2s 1", "spriteMove2 1.2s 1", "spriteMove3 1.2s 1", "spriteMove4 1.2s 1", "spriteMove5 1.2s 1"];
	
	/*screen widths laptop*/
	if (screen.width >= 851) {	
		pixelStep = ["61px", "122px", "183px", "245px", "306px"];
	}
	
	/*landscape mobile*/
	if (screen.width >= 551 && screen.width <= 850) {
		pixelStep = ["15.6%", "31%", "47%", "62.2%", "77.6%"];
	}
	
	/*portrait mobile*/
	if (screen.width <= 550) {	
		pixelStep = ["14.8%", "29.6%", "44.35%", "59.35%", "74.35%"];	
	}
	
     /*moves sprite to left based on css*/
     document.getElementById("sprite_box").style.animation = correctStepArray[count];
       
     /*plays correct sound*/
     if(count == 4){
      document.getElementById('end').play();
     }
     else{
     document.getElementById('correct').play();   
     }
     
     /*fills in step when get a correct answer*/
     if(count < 4){
     document.getElementById(backgroundStep[count]).style.background = "orange";
     }
     
     /*stops the sprite after it moves to left*/
     document.getElementById("sprite_box").style.left = pixelStep[count];


     
     /*run ending code when sprite has reached end of bridge*/
     if(count == 4){
	      /*remove event listener for stepwise*/
          document.getElementsByClassName("sprite_box_stepwise")[0].removeEventListener("webkitAnimationEnd", stepWise);


          document.getElementsByClassName("sprite_box")[0].addEventListener("webkitAnimationEnd", end);
     }
     else{
	      document.getElementsByClassName("sprite_box_stepwise")[0].addEventListener("webkitAnimationEnd", stepWise);
}
     
     count += 1;
         
}


/*end of sprite moving, moves 1-4*/
function stepWise(){
	
	/*enable all buttons*/
     document.getElementById("zeroToTenNumbers").style.pointerEvents = "auto";
     document.getElementById("tenToTwentyNumbers").style.pointerEvents = "auto";     
     
     
     /*run random question numbers function*/
     getRandomNumbersForQuestion(tenTwenty, addSubtract);

    /*if correct answer then countdown timer will start at 10*/  
    document.getElementById("countdownBox").innerHTML = 10;
    /*set timer to 9*/
    countdownTen = 9;
    /*load timer again*/
    timer = setInterval(timerInterval, timerCount);
}

/*end pop up screen, moves 5*/
function end(){
     // display pop up 'well done' screen
      document.getElementById("well_done_screen").style.display = "inline-block";
     // play end sound
     document.getElementById('well_done').play();

     /*disables all buttons when one is pressed*/
     document.getElementById("zeroToTenNumbers").style.pointerEvents = "none";

/*hides the numbers in questions box*/
     document.getElementById("questionA").style.visibility = "hidden";
     document.getElementById("questionB").style.visibility = "hidden";
     document.getElementById("questionSign").style.visibility = "hidden";

     }


// incorrect
function incorrectStep(x){   
     
      /*incorrect sound*/
      document.getElementById('incorrect').play();

      /*disables all buttons when one is pressed*/
      document.getElementById("zeroToTenNumbers").style.pointerEvents = "none";
      document.getElementById("tenToTwentyNumbers").style.pointerEvents = "none";


     /*un-colour the last block sprite is on, if on a block*/
     if(count > 0){
     document.getElementById(backgroundStep[count-1]).style.background = "white";
     }

///////////////////////////////
     /*screen widths laptop*/
    if (screen.width >= 851) {
	/*sprite falls down*/
     document.getElementById("sprite_box").style.animation = "spriteMoveDown 2s 1";	
		   document.getElementsByClassName("sprite_box")[0].style.top = "48%";
		   console.log("load animation")
	}
	
	/*landscape mobile*/
	if (screen.width >= 551 && screen.width <= 850) {
		/*sprite falls down*/
     document.getElementById("sprite_box").style.animation = "spriteMoveDownLandscape 2s 1";
		   document.getElementsByClassName("sprite_box")[0].style.top = "37.4vh";
	}
	
	/*portrait mobile*/
	if (screen.width <= 550) {	
		/*sprite falls down*/
     document.getElementById("sprite_box").style.animation = "spriteMoveDownPortrait 2s 1";
		   document.getElementsByClassName("sprite_box")[0].style.top = "16vh";	
	}
////////////////////////////////

     /*incorrect user answer when popup appears - fallenpopup function*/
     document.getElementById("numberAnswerC").innerHTML = x;
     console.log(x);
     /*if number was not pressed when timer runs out set answer to "?" */
if(x == null){
	 document.getElementById("numberAnswerC").innerHTML = "?";
}  
     /*remove event listener for stepwise*/
     document.getElementsByClassName("sprite_box_stepwise")[0].removeEventListener("webkitAnimationEnd", stepWise);
     /*run code after sprite has fallen down*/
     document.getElementsByClassName("sprite_box_fallen")[0].addEventListener("webkitAnimationEnd", fallen);
     console.log("listener");
}

/*fallen pop-up screen*/
function fallen(){
	console.log("screen");
// display pop up 'game over' screen
document.getElementById("fallen_screen").style.display = "inline-block";
// show score, user answer and correct answer for question
fallenPopUp();
}

function backToStart(){
	//reset variables
	
	// count
	count = 0;
     
	//character
	document.getElementById("sprite_MrBall").style.display = "none"; 
	document.getElementById("sprite_MsTriangle").style.display = "none"; 
	document.getElementById("sprite_MrBox").style.display = "none"; 
	
	//enable buttons
	document.getElementById("zeroToTenNumbers").style.pointerEvents = "auto";
    document.getElementById("tenToTwentyNumbers").style.pointerEvents = "auto";
    
	//eventlistener
     document.getElementsByClassName("sprite_box_fallen")[0].removeEventListener("webkitAnimationEnd", fallen);
     document.getElementsByClassName("sprite_box")[0].removeEventListener("webkitAnimationEnd", end);
     document.getElementsByClassName("sprite_box_stepwise")[0].removeEventListener("webkitAnimationEnd", stepWise);

	//popup boxes none   
	document.getElementById("fallen_screen").style.display = "none";
    document.getElementById("well_done_screen").style.display = "none";
	document.getElementById("options_start_screen").style.display = "inline-block";
	
	//reset timer to 10
	document.getElementById("countdownBox").innerHTML = 10;
	countdownTen = 9;
	
	//re-position character
/*	document.getElementById("sprite_box").style.top = "50px";	
*/
    //un-fill bridge boxes
    for(let num = 0; num <= 3; num++){
    document.getElementById(backgroundStep[num]).style.background = "white";
    }
    
    //put question number back in when reached end - new game
    document.getElementById("questionA").style.visibility = "visible";
    document.getElementById("questionB").style.visibility = "visible";
    document.getElementById("questionSign").style.visibility = "visible";
    
    //animation move back to starting position//same animation can not be played twice in a row
    document.getElementById("sprite_box").style.animation = "test 3s 1";
    document.getElementsByClassName("sprite_box")[0].style.left = "0px";
    document.getElementsByClassName("sprite_box")[0].style.top = "0px";

    
    
}

function myFunction() {
  var myobj = document.getElementById("demo");
  myobj.remove();
}

/*//////////////////////////////////////////////////////////////////////////////////////////////////////////*/
/*//////////////////////////////////////////////////////////////////////////////////////////////////////////*/


/*Selection Pop-Up*/

/*check 10 or 20 AND check sign selection + or -*/
function whichRadioButtonsClicked(){

     /*MADE ON FIRST SELECTION SCREEN*/

     // Numbers
     /*ten add*/
     if(document.getElementById("ten").checked){ tenTwenty = 11;}

     /*twenty subtract*/
     if(document.getElementById("twenty").checked){ tenTwenty = 21;}

     // Operation
     /*add*/
     if(document.getElementById("add").checked){ addSubtractNumber = 0; addSubtract = "&plus;"; }
     /*subtract*/
     if(document.getElementById("subtract").checked){ addSubtractNumber = 1; addSubtract = "&minus;"; }

     /*Character*/
     // Mr Ball
     if(document.getElementById("MrBall").checked){
     document.getElementById("sprite_MrBall").style.display = "block"; }
      // Ms Triangle
     if(document.getElementById("MsTriangle").checked){
     document.getElementById("sprite_MsTriangle").style.display = "block"; }
     // Mr Box
     if(document.getElementById("MrBox").checked){
     document.getElementById("sprite_MrBox").style.display = "block"; }

     /*Countdown*/
     // Off
     if(document.getElementById("countdownOff").checked){
     document.getElementById("countdownBox").style.visibility = "hidden";
     /*timer off*/
     onOffTimer = 0;
     }

     // 10 seconds
     if(document.getElementById("countdownNumber").checked){
     document.getElementById("countdownBox").style.visibility = "visible";
     /*timer on*/
     onOffTimer = 1;
     }
     }

/*ten or twenty number selected*/
/*////////////////////////////////////*/

function tenoOrTwentyNumbers(tenTwenty){
	if (tenTwenty == 11){
		/*make number pad 0-10 visible*/
	 document.getElementById("zeroToTenNumbers").style.display = "block";
	 document.getElementById("tenToTwentyNumbers").style.display = "none";

	}
	if(tenTwenty == 21){
		/*make number pad 10-20 visible*/
	 document.getElementById("zeroToTenNumbers").style.display = "none";
	 document.getElementById("tenToTwentyNumbers").style.display = "block";

    }
}


/*/////////////////////////////////////*/

/*swaps numbers from 0-10 to 10-20*/
function swapButton(){
	
	if (document.getElementById("zeroToTenNumbers").style.display === "none"){
		
		document.getElementById("zeroToTenNumbers").style.display = "block";
	    document.getElementById("tenToTwentyNumbers").style.display = "none";
	}
	else{
		
		document.getElementById("zeroToTenNumbers").style.display = "none";
		document.getElementById("tenToTwentyNumbers").style.display = "block";
	}
	
	}
/*/////////////////////////////////////*/	
	
	
	

function getRandomNumbersForQuestion(tenTwenty, addSubtract){
	
/*	let fromNumber;
	
	if (tenTwenty == 11){
		fromNumber = 0;
	}else{
		fromNumber = 10;
	}*/
    //   0-10 or 0-20
/*    randomNumberMaxMinA = Math.floor(Math.random() * (tenTwenty - fromNumber)) + fromNumber;
    randomNumberMaxMinB = Math.floor(Math.random() * (tenTwenty - randomNumberMaxMinA));
*/
    randomNumberMaxMinA = Math.floor(Math.random() * tenTwenty);
    randomNumberMaxMinB = Math.floor(Math.random() * (tenTwenty - randomNumberMaxMinA));
    // compare numbers and find max and min
    numberFrom = Math.max(randomNumberMaxMinA, randomNumberMaxMinB);
    numberTo = Math.min(randomNumberMaxMinA, randomNumberMaxMinB);

    // set question numbers and sign, in DOM
    questionA.innerHTML = numberFrom;
    questionB.innerHTML = numberTo;
    questionSign.innerHTML = addSubtract;
}

/*function countdownTimer(){
	document.getElementById("countdownBox").innerHTML = countdownTen;
	countdownTen -= 1;
}*/

let timer;
let timerCount = 1000;

function startingMainGame(){
	
	timer = setInterval(timerInterval, timerCount);

whichRadioButtonsClicked();
//show 0-10 numbers 0r show 0-20 numbers
tenoOrTwentyNumbers(tenTwenty);
getRandomNumbersForQuestion(tenTwenty, addSubtract);
/*tenoOrTwentyNumbers(tenTwenty);*/

if(onOffTimer == 0){
		
	clearInterval(timer);
		
	}

/*push margin on question box up due to 0-10 and 10-20 numbers being float center*/
/*document.getElementById("numberAnswerBox").style.transform = "translateY(-48px)";*/

/*close start pop up screen*/
document.getElementById("options_start_screen").style.display = "none";

/*show swap button*/
if(document.getElementById("twenty").checked){
	document.getElementById("swapNumbers_button").style.visibility = "visible";
}//take swap button off if 0-10 numbers selected
else{
	document.getElementById("swapNumbers_button").style.visibility = "hidden";
}
/*put window at top of page when play button is pushed on select screen pop up*/
 window.scrollTo(0, 0);

}

/*timer count down*/
function timerInterval(){ 
	
	// set number
    document.getElementById("countdownBox").innerHTML = countdownTen;
	
	// when count is 0 then run incorrecr code function and stop timer
	if(document.getElementById("countdownBox").innerHTML == "0"){
		incorrectStep();
		clearInterval(timer);
	}
	else{
		/*if timer is checked on radio box*/
		if(onOffTimer == 1){
			
		countdownTen -= 1;
		
		}	
	}
}



/*Fallen Pop-Up*/
function fallenPopUp(){

/*score*/
document.getElementById("userScore").innerHTML = count;

/*question A*/
document.getElementsByClassName("NumberAnswerA")[0].innerHTML = numberFrom;
document.getElementsByClassName("NumberAnswerA")[1].innerHTML = numberFrom;
// operator sign
document.getElementsByClassName("NumberAnswerSign")[0].innerHTML = addSubtract;
document.getElementsByClassName("NumberAnswerSign")[1].innerHTML = addSubtract;
/*question B*/
document.getElementsByClassName("NumberAnswerB")[0].innerHTML = numberTo;
document.getElementsByClassName("NumberAnswerB")[1].innerHTML = numberTo;

/*incorrect user answer*/

/*correct answer*/
let arrayAnswerFallen = [numberFrom + numberTo, numberFrom - numberTo];
document.getElementById("numberAnswerUserC").innerHTML = arrayAnswerFallen[addSubtractNumber];

}

/*//////////////////////////////////////////////////////////////////////////////////////////////////////////*/
/*//////////////////////////////////////////////////////////////////////////////////////////////////////////*/

/*Answer*/

//x = the number of which answer button was clicked on - in (jsp file)0-10, 11-20 buttons.
function numberAnswerBox(x){

/*var myScreenOrientation = window.screen.orientation;
myScreenOrientation.lock("portrait");*/


/*disables all buttons when one is pressed*/
document.getElementById("zeroToTenNumbers").style.pointerEvents = "none";
document.getElementById("tenToTwentyNumbers").style.pointerEvents = "none";


/*which radio buttons to compare answer to*/
let arrayAnswer = [numberFrom + numberTo, numberFrom - numberTo];

if(x == (arrayAnswer[addSubtractNumber])){

clearInterval(timer);
    


/*run correct function*/
correctStep();

if(count == 5){
	//stops the timer
     clearInterval(timer);
}

}
else{
/*run incorrect answer*/
incorrectStep(x);

/*stop timer countdowmn*/
clearInterval(timer);
}

}
/*//////////////////////////////////////////////////////////////////////////////////////////////////////////*/
/*//////////////////////////////////////////////////////////////////////////////////////////////////////////*/
