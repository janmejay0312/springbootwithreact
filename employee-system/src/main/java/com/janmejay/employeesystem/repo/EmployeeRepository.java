package com.janmejay.employeesystem.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.janmejay.employeesystem.entities.Employee;

@Repository
public interface EmployeeRepository  extends JpaRepository<Employee,Long>{

}
