package com.app.service;

import java.util.List;

import com.app.model.Employee;

public interface EmployeeService {
	
	public Employee saveEmp(Employee emp);
	
	public boolean deleteEmp(int id);
	
	public Employee updateEmp(int id,float salary);
	
	public Employee getEmp(int id);
	
	public List<Employee> getAllEmp();
}
