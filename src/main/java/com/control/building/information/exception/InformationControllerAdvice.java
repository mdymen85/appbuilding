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
		return new ResponseError(error.getCode(), 
				messageSource.getMessage(error.getCode(), null, Locale.ENGLISH), 
				HttpStatus.BAD_REQUEST)
				.getResponseErrorObject(); 
	}
	
	@ExceptionHandler(FloorMustNotBeNullException.class) 
	public ResponseEntity<ResponseErrorObject> floorMustNotBeNullException(FloorMustNotBeNullException error) {			
		return new ResponseError(error.getCode(), 
				messageSource.getMessage(error.getCode(), null, Locale.ENGLISH), 
				HttpStatus.BAD_REQUEST)
				.getResponseErrorObject(); 
	}
	
	@ExceptionHandler(ApartmentMustNotBeNullException.class) 
	public ResponseEntity<ResponseErrorObject> apartmentMustNotBeNullException(ApartmentMustNotBeNullException error) {			
		return new ResponseError(error.getCode(), 
				messageSource.getMessage(error.getCode(), null, Locale.ENGLISH), 
				HttpStatus.BAD_REQUEST)
				.getResponseErrorObject(); 
	}
	
	@ExceptionHandler(FloorDoesNotExistException.class) 
	public ResponseEntity<ResponseErrorObject> floorDoesNotExistException(FloorDoesNotExistException error) {			
		return new ResponseError(error.getCode(), 
				messageSource.getMessage(error.getCode(), new Object[] {error.getUuid(), error.getNumber()}, Locale.ENGLISH), 
				HttpStatus.BAD_REQUEST)
				.getResponseErrorObject(); 
	}
	
	@ExceptionHandler(ApartmentDoesNotExistsException.class) 
	public ResponseEntity<ResponseErrorObject> apartmentDoesNotExistsException(ApartmentDoesNotExistsException error) {			
		return new ResponseError(error.getCode(), 
				messageSource.getMessage(error.getCode(), new Object[] {error.getApartment(), error.getFloor(), error.getUuid()}, Locale.ENGLISH), 
				HttpStatus.BAD_REQUEST)
				.getResponseErrorObject(); 
	}
	
	@ExceptionHandler(BuildingDoesNotExistsException.class) 
	public ResponseEntity<ResponseErrorObject> buildingDoesNotExistsException(BuildingDoesNotExistsException error) {			
		return new ResponseError(error.getCode(), 
				messageSource.getMessage(error.getCode(), new Object[] {error.getUuid()}, Locale.ENGLISH), 
				HttpStatus.BAD_REQUEST)
				.getResponseErrorObject(); 
	}
	
	@ExceptionHandler(FloorAlreadyExistsException.class) 
	public ResponseEntity<ResponseErrorObject> floorAlreadyExistsException(FloorAlreadyExistsException error) {			
		return new ResponseError(error.getCode(), 
				messageSource.getMessage(error.getCode(), new Object[] {error.getNumber(), error.getUuid()}, Locale.ENGLISH), 
				HttpStatus.BAD_REQUEST)
				.getResponseErrorObject(); 
	}	
	
	
	@ExceptionHandler(ApartmentAlreadyExistsException.class) 
	public ResponseEntity<ResponseErrorObject> apartmentAlreadyExistsException(ApartmentAlreadyExistsException error) {			
		return new ResponseError(error.getCode(), 
				messageSource.getMessage(error.getCode(), new Object[] {error.getApartmentNumber(), error.getFloorNumber(), error.getUuid()}, Locale.ENGLISH), 
				HttpStatus.BAD_REQUEST)
				.getResponseErrorObject(); 
	}	
}
