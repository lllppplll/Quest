describe("create account validation // UNIT TESTS", function(){
	
	beforeEach(function() {
		
		var HTMLElements = {};
		
		//To test document.getElementById elements
		document.getElementById = jasmine.createSpy('HTML Element').and.callFake(function(ID) {
			if(!HTMLElements[ID]) {
				console.log(ID);
				var newElement = document.createElement('div');
				HTMLElements[ID] = newElement;
				}
				return HTMLElements[ID];
				});
				});
	
	it("email validation", function(){
		// arrange
		// act
		// assert
		expect(emailValidation("email")).toEqual("none");
		expect(emailValidation("")).toEqual("block");
	});
		
	it("password validation", function(){
		// arrange
		// act
		// assert
		expect(passwordValidation("password")).toEqual("none");
		expect(passwordValidation("")).toEqual("block");
	});		
	
	it("form validation", function(){
		// arrange
		// act
		// assert
		expect(formValidation("none", "none")).toEqual(true);
		expect(formValidation("block", "none")).toEqual(false);
		expect(formValidation("none", "block")).toEqual(false);
		expect(formValidation("block", "block")).toEqual(false);
	});

	it("button disable", function(){
		// arrange
		// act
		formValidation("none", "none")
		// assert
		expect(document.getElementById("create_button").disabled).toEqual(true);
	});
	
	it("DOM", function(){
		// arrange
		// act
		DOMemail("none");
		DOMpassword("none");
	    DOMbutton(true);
		// assert
		expect(document.getElementById("email_required").style.display).toEqual("none");
		expect(document.getElementById("password_required_length").style.display).toEqual("none");
		expect(document.getElementById("create_button").disabled).toEqual(true);
	});
	
	it("validate Validation 'emailMessage' ", function(){
		// arrange
		// act
		validate_create_account("email", "");
		// assert
		expect(emailMessage).toEqual("none");
		expect(passwordMessage).toEqual("block");
	});
	it("validate Validation 'passwordMessage' ", function(){
		// arrange
		// act
		validate_create_account("", "password");
		// assert
		expect(emailMessage).toEqual("block");
		expect(passwordMessage).toEqual("none");
	});
	
	it("validate DOM changes 'emailMessage'", function(){
		// arrange
		// act
		validate_create_account("email", "");
		DOMemail(emailMessage);
		// assert
		expect(document.getElementById("email_required").style.display).toEqual("none");
		expect(document.getElementById("password_required_length").style.display).toEqual("block");
	});
	it("validate DOM changes 'passwordMessage' ", function(){
		// arrange
		// act
		validate_create_account("", "password");
		DOMpassword(passwordMessage);
		// assert
		expect(document.getElementById("email_required").style.display).toEqual("block");
		expect(document.getElementById("password_required_length").style.display).toEqual("none");
	});

	
});

////////////////////////////////////////////////////////////////////////////////
describe("create account validation // E2E TESTS", function(){
	
	beforeEach(function() {
		
		//var dummyElement = document.createElement('div');
		//document.getElementById = jasmine.createSpy('HTML Element').and.returnValue(dummyElement);
		var HTMLElements = {};
		
		//To test document.getElementById elements
		document.getElementById = jasmine.createSpy('HTML Element').and.callFake(function(ID) {
			if(!HTMLElements[ID]) {
				console.log(ID);
				var newElement = document.createElement('div');
				HTMLElements[ID] = newElement;
				}
				return HTMLElements[ID];
				});
				});
	
	it("email and password ", function(){
		// arrange	
		// act
		// assert
		expect(validate_create_account("email", "password")).toEqual(true);
		expect(validate_create_account("", "password")).toEqual(false);
		expect(validate_create_account("email", "")).toEqual(false);
		expect(validate_create_account("", "")).toEqual(false);
	});
	
		it("DOM test TRUE", function(){
		// arrange
		// act
		validate_create_account("email", "password");
		// assert
		expect(document.getElementById("email_required").style.display).toEqual("none");
		expect(document.getElementById("password_required_length").style.display).toEqual("none");
		expect(document.getElementById("create_button").disabled).toEqual(true);
	});
	
	it("DOM test FALSE", function(){
		// arrange
		// act
		validate_create_account("", "");
		// assert
		expect(document.getElementById("email_required").style.display).toEqual("block");
		expect(document.getElementById("password_required_length").style.display).toEqual("block");
		expect(document.getElementById("create_button").disabled).toEqual(undefined);
	});
});