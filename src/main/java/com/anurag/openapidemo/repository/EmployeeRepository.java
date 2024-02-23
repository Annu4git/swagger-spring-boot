package com.anurag.openapidemo.repository;

import com.anurag.openapidemo.entity.Employee;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends ListCrudRepository<Employee, Integer> { }
