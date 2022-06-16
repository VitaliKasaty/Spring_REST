package com.kasatyvitali.spring.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kasatyvitali.spring.rest.entity.Employee;
import com.kasatyvitali.spring.rest.exception_handling.EmployeeIncorrectData;
import com.kasatyvitali.spring.rest.exception_handling.NoSuchEmployeeException;
import com.kasatyvitali.spring.rest.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class MyRESTController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/employees")
	public List<Employee> showAllEmployees() {
		
		List<Employee> allEmployees = employeeService.getAllEmployees();
		return allEmployees;
	}
	
	@GetMapping("/employees/{id}")
	public Employee getEmployee(@PathVariable int id) {
		
		Employee employee = employeeService.getEmployee(id);
		
		if (employee == null) {
			throw new NoSuchEmployeeException("There is no employee with ID = "
					+ id + " in DataBase");
		} 
		return employee;		
	}
	
	@PostMapping("/employees")
	public Employee addNewEmployee(@RequestBody Employee employee) {
		
		employeeService.saveEmployee(employee);
		
		return employee;
	}
	
	@PutMapping("/employees")
	public Employee updateEmployee(@RequestBody Employee employee) {
		
		employeeService.saveEmployee(employee);
		
		return employee;		
	}
	
	@DeleteMapping("/employees/{id}")
	public String deleteEmployee(@PathVariable int id) {
		
		Employee employee = employeeService.getEmployee(id);
		if (employee == null) {
			throw new NoSuchEmployeeException("There is no Employee with "
					+ "id = " + id + " in Database");
		}
		employeeService.deleteEmployee(id);
		
		return "Employee with ID = " + id + " was deleted.";
	}

}
