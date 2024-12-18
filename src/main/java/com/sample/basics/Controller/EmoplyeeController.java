package com.sample.basics.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.basics.Exceptions.EmployeeNotFoundException;
import com.sample.basics.Pojo.Employee;
import com.sample.basics.Service.EmployeeService;

@RestController
@RequestMapping("employees")
public class EmoplyeeController {
	@Autowired
	EmployeeService employee;
	
	@GetMapping
	public List<Employee> getEmployees()
	{
		return employee.getAllEmployees();
	}
	@GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable int id) {
        return employee.getEmployeeById(id);
    }
	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<String> handleEmployeeNotFound(EmployeeNotFoundException e)
	{
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	}
	
	@PostMapping
	public Employee addEmployee(@RequestBody Employee emp)
	{
		return employee.saveEmployee(emp);
	}
	
	@DeleteMapping("/{id}")
	public String deleteEmployee(@PathVariable int id)
	{
		return employee.deleteEmployeeById(id);
	}
	

}
