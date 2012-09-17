/**
 * 
 */
package com.emc.xcp.utils;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.emc.xcp.domain.Customer;
/**
 * @author jajarp
 *
 */

@Component("customerForm")
public class CustomerFormValidator implements Validator{


	@SuppressWarnings("rawtypes")
	public boolean supports(Class clazz) {
		// TODO Auto-generated method stub
		return Customer.class.isAssignableFrom(clazz);
	}


	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "required.firstName","Firstname is required.");
		
	}

}
