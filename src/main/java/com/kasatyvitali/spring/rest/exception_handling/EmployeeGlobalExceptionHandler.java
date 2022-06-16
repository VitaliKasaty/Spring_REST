package com.kasatyvitali.spring.rest.exception_handling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmployeeGlobalExceptionHandler {
	
	//обработка исключения, если введёного id работника не существует
	@ExceptionHandler
	public ResponseEntity<EmployeeIncorrectData> handleException(
			NoSuchEmployeeException exception) {
		EmployeeIncorrectData data = new EmployeeIncorrectData();
		data.setInfo(exception.getMessage());
		
		return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
	}
	
	//Перегрузка метода. Обработка всех остальных исключений
	@ExceptionHandler
	public ResponseEntity<EmployeeIncorrectData> handleException(
			Exception exception) {
		EmployeeIncorrectData data = new EmployeeIncorrectData();
		data.setInfo(exception.getMessage());
		
		return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
	}

}
