package com.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.dao.EmployeeDAO;
import com.app.model.Employee;
import com.app.service.EmployeeService;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@RequestMapping(value="/emp")
public class EmployeeController {
	
	@Autowired
	private EmployeeService service;
	
	//adding records 
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public ResponseEntity<Employee> addEmp(@RequestBody Employee obj){
		Employee emp=service.saveEmp(obj);
		if(emp!=null)
			return new ResponseEntity<Employee>(emp,HttpStatus.CREATED);
		else
			return new ResponseEntity(null,HttpStatus.BAD_REQUEST);
	}
	
	//deleting records
	@RequestMapping(value="/delete/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteEmp(@PathVariable int id){
		boolean flag=service.deleteEmp(id);
		if(flag==true)
			return new ResponseEntity<Boolean>(flag, HttpStatus.ACCEPTED);
		else
			return new ResponseEntity<Boolean>(flag, HttpStatus.NOT_FOUND);
	}
	
	//updating records
	@RequestMapping(value="/update",method=RequestMethod.PUT)
	public ResponseEntity<Employee> updateEmp(@RequestBody ObjectNode obj){
		int id=obj.get("emp_id").intValue();
		float salary=obj.get("emp_salary").floatValue();
		Employee emp=service.updateEmp(id, salary);
		if(emp!=null)
			return new ResponseEntity<Employee>(emp,HttpStatus.ACCEPTED);
		else
			return new ResponseEntity(null,HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/get/{id}")
	public ResponseEntity<Employee> getEmpById(@PathVariable int id){
		Employee obj=service.getEmp(id);
		if (obj != null) {
			return new ResponseEntity<Employee>(obj, HttpStatus.OK);
		} else
			return new ResponseEntity(null, HttpStatus.NOT_FOUND);
	}
	
	//list of all employees
	@RequestMapping(value="/getAll")
	public ResponseEntity<List<Employee>> getAllEmp(){
		List<Employee> list=service.getAllEmp();
		return new ResponseEntity<List<Employee>>(list,HttpStatus.OK);
	}
	//find bugs
	public String findbugs() {
		// i = 100;
        String str = null;
        System.out.println("This is the output from findbugs method : " + str);
        return str.toUpperCase();
        
    }
}
