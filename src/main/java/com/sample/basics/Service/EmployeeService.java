package com.sample.basics.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sample.basics.Exceptions.EmployeeNotFoundException;
import com.sample.basics.Pojo.Employee;

@Service
public class EmployeeService {

	private List<Employee> employees = new ArrayList<>();
	private int currentId = 1;
	
	public Employee saveEmployee(Employee employee) {
        if (employee.getId() == 0) {  // New Employee
            employee.setId(currentId++);
            employees.add(employee);
        }
        
        return employee;
    }
	
	
	public List<Employee> getAllEmployees()
	{
		if(employees.size()>0)
		return employees;
		throw new EmployeeNotFoundException("No employees added yet");
	}
	
	public Employee getEmployeeById(int id) {
        for(Employee e: employees)
        {
        	if(e.getId() == id)
        	{
        		return e;
        	}
        }
        throw new EmployeeNotFoundException("Employee with ID:"+id+" Not Found");
    }
	public String deleteEmployeeById(int id)
	{
		 Iterator<Employee> iterator = employees.iterator();
		    while (iterator.hasNext()) {
		        Employee e = iterator.next();
		        if (e.getId() == id) {
		            iterator.remove(); // Safe removal
		            return "Employee deleted";
		        }
		    }
		throw new EmployeeNotFoundException("Employee not found");
	}
}
