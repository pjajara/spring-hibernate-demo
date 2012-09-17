/**
 * 
 */
package com.emc.xcp.utils;

/**
 * @author jajarp
 *
 */

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component("contactFormValidator")
public class ContactFormValidator implements Validator
{

	@SuppressWarnings("unchecked")
	public boolean supports(Class clazz) {
		return Contact.class.isAssignableFrom(clazz);
	}

	
	public void validate(Object model, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name",
				"required.name", "Name is required.");
	}
}
