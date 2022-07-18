package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;

public interface EmployeeService {

	List<Employee> getAllEmployees();
	
	Integer createEmployee(Employee emp);
	
	void updateEmployee(Employee emp, Integer id);
	
	void deleteEmployee(Integer id);

	Employee findById(Integer id);
	
	void updateEmployeeEmail(Integer id,String email);
	
	Page<Employee> findInPages(int pageNo,int pageSize);
	
	
	Page<Employee> findInPagesWithSort(int pageNo,int pageSize,String sortColumn,String sortDirection);
	
	List<Employee> sortedColumnEmployee(String sortColumn,String sortDirection);
}
