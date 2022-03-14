/*alert("js working");*/


//load add function to start
add_checked();


//add sign
function add_checked() {

    //store values
	let add_array = [ [0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0] ];
	let numbers_unique = false;
	
	for (i = 0; i <= 9; i++) {
		
		//get random numbers
		let randomA = Math.floor((Math.random() * 10) + 1);
		let randomB = Math.floor((Math.random() * 10) + 1);
		
		//check numbers are unique
		while(numbers_unique == false){
			
		for(j=0; j<=9; j++){
	    
			
		//check numbers are not the same in array
		if(randomA == add_array[j][0]){
			if(randomB == add_array[j][1]){
				//numbers are the same, repeat steps.
				j=-1;
				//check numbers again with new values
				randomA = Math.floor((Math.random() * 10) + 1);
				randomB = Math.floor((Math.random() * 10) + 1);
				
			}

		}
		
		//conut
		if(j == 9){

			numbers_unique = true;
		}
		
		}//end of for loop j
		}//end of while loop
		
	    //reset values-
		numbers_unique = false;
		
	    //place numbers in array
	    add_array[i][0] = randomA;
		add_array[i][1] = randomB;

		
		//numbers and sign in dom
		document.getElementsByClassName("questionA")[i].innerHTML = randomA;
		document.getElementsByClassName("questionB")[i].innerHTML = randomB;
		document.getElementsByClassName("sign")[i].innerHTML = "&plus;";
		
		//put answers in dom
		document.getElementsByClassName("answer")[i].innerHTML = randomA + randomB;
		
	}
}

//subjact sign
function subtract_checked() {
	
	let randomASubtract;
	let randomBSubtract;
	let subtract_array = [ [0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0] ];
	let numbers_unique = false;
	
	for (i = 0; i <= 9; i++) {

        //random numbers
		let randomA = Math.floor((Math.random() * 20) + 1);
		let randomB = Math.floor((Math.random() * 20) + 1);
		
		
		while(numbers_unique == false){
			
		for(j=0; j<=9; j++){
			
		//different random numbers so a - b does not = 0
		while(randomA == randomB){
			randomA = Math.floor((Math.random() * 20) + 1);
		    randomB = Math.floor((Math.random() * 20) + 1);
	        }
	        
	        //put highest number first - used in subtract
     	    randomASubtract = Math.max(randomA, randomB);
	        randomBSubtract = Math.min(randomA, randomB);
		
			
		//check numbers are not the same in array
		if(randomASubtract == subtract_array[j][0]){
			if(randomBSubtract == subtract_array[j][1]){
				//numbers are the same, repeat steps.
				j=-1;
				//check numbers again with new values
				randomA = Math.floor((Math.random() * 20) + 1);
				randomB = Math.floor((Math.random() * 20) + 1);
			}
		}
		
		//conut
		if(j == 9){
			numbers_unique = true;
		}
		
		}//end of for loop j
		}//end of while loop
		
		
		//reset values-
		numbers_unique = false;
		
	    //place numbers in array
	    subtract_array[i][0] = randomASubtract;
		subtract_array[i][1] = randomBSubtract;
		//place values on dom
	    document.getElementsByClassName("questionA")[i].innerHTML = randomASubtract;
		document.getElementsByClassName("questionB")[i].innerHTML = randomBSubtract;
		document.getElementsByClassName("sign")[i].innerHTML = "&minus;";
	    //put answers in dom
		document.getElementsByClassName("answer")[i].innerHTML = randomASubtract - randomBSubtract;
	}
}