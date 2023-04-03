package com.janmejay.employeesystem.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.janmejay.employeesystem.entities.Employee;
import com.janmejay.employeesystem.exception.ResourceNotFoundException;
import com.janmejay.employeesystem.repo.EmployeeRepository;

@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {

	@Autowired
private EmployeeRepository employeeRepository;	

@GetMapping("/employees")
public List<Employee> getAllEmployee(){
	return employeeRepository.findAll();
}
@CrossOrigin(origins = "http://localhost:1234")
@PostMapping("/employees")
public Employee createEmployee(@RequestBody Employee employee) {
	return employeeRepository.save(employee);
}

@GetMapping("/employees/{id}")
public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long id)
{
	Employee employee=employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("employee not found with id"+id));
	return ResponseEntity.ok(employee);
}
@CrossOrigin(origins = "http://localhost:1234")
@PutMapping("/employees/{id}")
public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id, @RequestBody Employee employeeDetail){
	Employee employee=employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("employee not found with id"+id));
	employee.setFirstName(employeeDetail.getFirstName());
	employee.setLastName(employeeDetail.getLastName());
	employee.setEmailId(employeeDetail.getEmailId());
	Employee updatedEmployee=employeeRepository.save(employee);
	return ResponseEntity.ok(updatedEmployee);
}
@CrossOrigin(origins = "http://localhost:1234")
@DeleteMapping("/employees/{id}")
public ResponseEntity<Map<String, Boolean>> updateEmployee(@PathVariable("id") long id){
	Employee employee=employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("employee not found with id"+id));
	employeeRepository.delete(employee);
	Map<String, Boolean> map=new HashMap<>();
	map.put("Deleted",Boolean.TRUE);
	return ResponseEntity.ok(map);
}
}
