package com.website.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import com.website.service.ServiceImpl;

public class ServiceImplTest {

	@Test
	void InputFieldErrorsHasErrors() {
		
		// arrange
		ServiceImpl test = new ServiceImpl();
		boolean result = true;
		
		// act
		boolean output = test.InputFieldErrors(result);
		
		// assert
		assertEquals(output, false);
	}
	
	@Test
	void InputFieldErrorsNoErrors() {
		
		// arrange
		ServiceImpl test = new ServiceImpl();
		boolean result = false;
		
		// act
		boolean output = test.InputFieldErrors(result);
		
		// assert
		assertEquals(output, true);
	}
	
}
