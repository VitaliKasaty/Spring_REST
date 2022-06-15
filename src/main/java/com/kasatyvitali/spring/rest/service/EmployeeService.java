package com.kasatyvitali.spring.rest.service;

import java.util.List;

import com.kasatyvitali.spring.rest.entity.Employee;

public interface EmployeeService {	
	public List<Employee> getAllEmployees();	
	public void saveEmployee(Employee employee);
	public Employee getEmployee(int id);
	public void deleteEmployee(int id);
}
