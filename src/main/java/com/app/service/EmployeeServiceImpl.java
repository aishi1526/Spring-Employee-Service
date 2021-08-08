package com.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.EmployeeDAO;
import com.app.model.Employee;
import com.app.service.EmployeeService;
@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeDAO dao;

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Employee saveEmp(Employee emp) {
		emp=dao.save(emp);
		return emp;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean deleteEmp(int id) {
		boolean flag=dao.existsById(id);
		if(flag==true) {
			dao.deleteById(id);
		}
		return flag;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Employee updateEmp(int id, float salary) {
		Employee emp=null;
		boolean flag=dao.existsById(id);
		if(flag==true) {
			Optional<Employee> optional=dao.findById(id);
			emp=optional.get();
			emp.setEmp_salary(salary);
			emp=dao.save(emp);
			return emp;
		}
		return null;
	}

	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public Employee getEmp(int id) {
		Employee emp=null;
		boolean flag=dao.existsById(id);
		if(flag==true) {
			Optional<Employee> optional=dao.findById(id);
			emp=optional.get();
			return emp;
		}
		return null;
	}

	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public List<Employee> getAllEmp() {
		Iterable<Employee> iterable = dao.findAll();
		List<Employee> list = new ArrayList<Employee>();
		iterable.forEach(list::add);
		return list;
	}

}
