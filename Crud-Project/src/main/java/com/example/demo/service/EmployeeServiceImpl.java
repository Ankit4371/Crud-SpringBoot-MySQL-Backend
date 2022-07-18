package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.dao.EmployeeRepository;
import com.example.demo.entity.Employee;
import com.example.demo.exception.ResourceNotFoundException;


@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeRepository employeeRepository;
	
	
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}


	@Override
	public Integer createEmployee(Employee emp) {
		Employee empNew = employeeRepository.save(emp);
		return empNew.getId();
	}


	@Override
	public void updateEmployee(Employee emp,Integer id) {
		// TODO Auto-generated method stub
		Employee emp1 = findById(id);
		emp1.setEmailId(emp.getEmailId());
		emp1.setFirstName(emp.getFirstName());
		emp1.setLastName(emp.getLastName());
		employeeRepository.save(emp1);
	}


	@Override
	public void deleteEmployee(Integer id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DeleteException. Employee not exist with id: " + id));

		employeeRepository.delete(employee);
	}
	
	public Employee findById(Integer id ) {
		Employee emp = employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("FetchException "));
		return emp;
	}


	@Override
	public void updateEmployeeEmail(Integer id, String email) {
		// TODO Auto-generated method stub
		Employee emp = findById(id);
		emp.setEmailId(email);
		employeeRepository.save(emp);
		
	}


	@Override
	public Page<Employee> findInPages(int pageNo, int pageSize) {
		// page no starts from 0 in paginaton
		Pageable pageable = PageRequest.of(pageNo-1, pageSize);
		return employeeRepository.findAll(pageable);
	}


	@Override
	public Page<Employee> findInPagesWithSort(int pageNo, int pageSize, String sortColumn, String sortDirection) {
		
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())
				? Sort.by(sortColumn).ascending() : Sort.by(sortColumn).descending();
		
		Pageable pageable = PageRequest.of(pageNo-1,pageSize,sort);
		return employeeRepository.findAll(pageable);
	}


	@Override
	public List<Employee> sortedColumnEmployee(String sortColumn, String sortDirection) {
		
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())
				? Sort.by(sortColumn).ascending() : Sort.by(sortColumn).descending();
		
		return employeeRepository.findAll(sort);
	}

	



}
