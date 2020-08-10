package com.couponsystem.jay.rest.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.couponsystem.jay.exceptions.AlreadyExistsException;
import com.couponsystem.jay.exceptions.LoginFailledException;
import com.couponsystem.jay.exceptions.NoAccessException;
import com.couponsystem.jay.exceptions.NotFoundException;
import com.couponsystem.jay.rest.controller.AdminController;
import com.couponsystem.jay.rest.controller.CompanyController;
import com.couponsystem.jay.rest.controller.CustomerController;

@ControllerAdvice(assignableTypes = {AdminController.class,CompanyController.class,CustomerController.class})
@RestController
public class AdviceController {
	
	AdviceError error = new AdviceError();
	
	@ExceptionHandler(AlreadyExistsException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public final AdviceError handleAlreadyExistsError(AlreadyExistsException ex,WebRequest request) {
		error.setCode((HttpStatus.BAD_REQUEST).value());
		error.setMessage("Sorry Already used, try again!");
		return error;
	}
	
	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public final AdviceError handleNotFoundError(NotFoundException ex, WebRequest request) {
		error.setCode((HttpStatus.NOT_FOUND).value());
		error.setMessage("Sorry Not found, try again!");
		return error;
	}
	
	@ExceptionHandler(LoginFailledException.class)
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	public final AdviceError handleLoginFailError(LoginFailledException ex,WebRequest request) {
		error.setCode((HttpStatus.UNAUTHORIZED).value());
		error.setMessage("Login Failed");
		return error;
	}
	
	@ExceptionHandler(NoAccessException.class)
	@ResponseStatus(value = HttpStatus.FORBIDDEN)
	public final AdviceError handleNoAccessError(NoAccessException ex,WebRequest request) {
		error.setCode((HttpStatus.FORBIDDEN).value());
		error.setMessage("No access for this action!");
		return error;
	}

}
