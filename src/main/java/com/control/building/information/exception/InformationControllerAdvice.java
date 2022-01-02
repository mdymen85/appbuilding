package com.control.building.information.exception;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.control.building.information.exception.responseerror.ResponseError;
import com.control.building.information.exception.responseerror.ResponseErrorObject;

import lombok.RequiredArgsConstructor;

@ControllerAdvice
@RequiredArgsConstructor
public class InformationControllerAdvice {

	private final MessageSource messageSource;
	
	@ExceptionHandler(BuildingBasicInformationException.class) 
	public ResponseEntity<ResponseErrorObject> buildingBasicInformationException(BuildingBasicInformationException error) {			
		return new ResponseError(error.getCode(), messageSource.getMessage(error.getCode(), null, Locale.ENGLISH), HttpStatus.BAD_REQUEST).getResponseErrorObject(); 
	}
	
	@ExceptionHandler(FloorMustNotBeNullException.class) 
	public ResponseEntity<ResponseErrorObject> floorMustNotBeNullException(FloorMustNotBeNullException error) {			
		return new ResponseError(error.getCode(), messageSource.getMessage(error.getCode(), null, Locale.ENGLISH), HttpStatus.BAD_REQUEST).getResponseErrorObject(); 
	}
	
	@ExceptionHandler(ApartmentMustNotBeNullException.class) 
	public ResponseEntity<ResponseErrorObject> apartmentMustNotBeNullException(ApartmentMustNotBeNullException error) {			
		return new ResponseError(error.getCode(), messageSource.getMessage(error.getCode(), null, Locale.ENGLISH), HttpStatus.BAD_REQUEST).getResponseErrorObject(); 
	}
	
	@ExceptionHandler(FloorDoesNotExistException.class) 
	public ResponseEntity<ResponseErrorObject> floorDoesNotExistException(FloorDoesNotExistException error) {			
		return new ResponseError(error.getCode(), messageSource.getMessage(error.getCode(), new Object[] {error.getId()}, Locale.ENGLISH), HttpStatus.BAD_REQUEST).getResponseErrorObject(); 
	}
	
	
}
