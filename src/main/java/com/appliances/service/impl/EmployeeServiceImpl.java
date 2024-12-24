package com.appliances.service.impl;

import com.appliances.aspect.Loggable;
import com.appliances.model.Employee;
import com.appliances.repository.EmployeeRepository;
import com.appliances.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.repository = employeeRepository;
    }

    @Loggable
    public Page<Employee> getPaginated(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Loggable
    public Optional<Employee> getById(Long id) {
        return Optional.of(repository.getReferenceById(id));
    }

    @Loggable
    public Employee save(Employee employee) {
        return repository.save(employee);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
