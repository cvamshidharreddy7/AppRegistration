package com.jda.user.ValidateUser;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class ValidateUser implements Validator{

	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object user, Errors result) {
		ValidationUtils.rejectIfEmptyOrWhitespace(result, "username", "required.username", "user name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(result, "password", "required.password", "password is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(result, "firstName", "required.firstName", "First name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(result, "lastname", "required.lastname", "last name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(result, "email", "required.email", "email is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(result, "phone", "required.phone", "phone is required.");
		
	}

}
