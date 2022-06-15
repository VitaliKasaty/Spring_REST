package com.kasatyvitali.spring.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kasatyvitali.spring.rest.dao.EmployeeDAO;
import com.kasatyvitali.spring.rest.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	private EmployeeDAO employeeDAO;

	@Override
	@Transactional //Передаёт Spring'у ответственность за работу с транзакциями
	public List<Employee> getAllEmployees() {		
		return employeeDAO.getAllEmployees();
	}

	@Override
	@Transactional
	public void saveEmployee(Employee employee) {
		employeeDAO.saveEmployee(employee);		
	}

	@Override
	@Transactional
	public Employee getEmployee(int id) {		
		return employeeDAO.getEmployee(id);
	}

	@Override
	@Transactional
	public void deleteEmployee(int id) {		
		employeeDAO.deleteEmployee(id);
	}

}
