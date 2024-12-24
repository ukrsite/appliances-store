package com.appliances.service;

import com.appliances.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface EmployeeService {
    Page<Employee> getPaginated(Pageable pageable);
    Optional<Employee> getById(Long id);
    Employee save(Employee employee);
    void deleteById(Long id);
}
