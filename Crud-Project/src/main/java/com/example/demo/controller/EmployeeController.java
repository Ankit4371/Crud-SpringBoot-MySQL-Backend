package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Employee;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.service.EmployeeService;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService  employeeService; 
	
	@RequestMapping(method = RequestMethod.OPTIONS)
	   ResponseEntity<?> collectionOptions() 
	   {
	      return ResponseEntity
	          .ok()
	          .allow(HttpMethod.GET, HttpMethod.POST, HttpMethod.OPTIONS,HttpMethod.PATCH,HttpMethod.PUT
	        		  ,HttpMethod.DELETE)
	              .build();
	   }
	
	@GetMapping()
	public ResponseEntity<List<Employee>> getAllEmployees(){
		List<Employee> employees = employeeService.getAllEmployees();
		return new ResponseEntity<>(employees,HttpStatus.OK);
	}
	@PostMapping()
	public ResponseEntity<String> saveEmployee(@RequestBody Employee emp) {
		Integer id = employeeService.createEmployee(emp);
		if(id == null) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<String>("Employee Saved with ID:" + id ,HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable  Integer id){
		Employee emp = employeeService.findById(id);
		return ResponseEntity.ok(emp);
	}
	@PostMapping("{id}")
	public ResponseEntity<String> updateByPostEmployee(@RequestBody Employee emp,@PathVariable Integer id) {
		employeeService.updateEmployee(emp,id);
		return new ResponseEntity<String>("Employee Updated "  ,HttpStatus.OK);
	}
	@PutMapping("{id}")
	public ResponseEntity<String> updatebyPutEmployee(@RequestBody Employee emp,@PathVariable Integer id) {
		employeeService.updateEmployee(emp,id);
		return new ResponseEntity<String>("Employee Updated "  ,HttpStatus.OK);
	}
	@PatchMapping("{id}")
	public ResponseEntity<String> updatebyPatchEmailOfEmployee(@RequestBody String email,@PathVariable Integer id) {
		employeeService.updateEmployeeEmail(id,email);
		return new ResponseEntity<String>("Employee Updated "  ,HttpStatus.OK);
	}
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable Integer id) {
		employeeService.deleteEmployee(id);
		return new ResponseEntity<String>("Employee Deleted with ID:" + id ,HttpStatus.OK);
	}
	
}
