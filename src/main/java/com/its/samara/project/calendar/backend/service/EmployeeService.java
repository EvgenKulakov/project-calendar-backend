package com.its.samara.project.calendar.backend.service;

import com.its.samara.project.calendar.backend.entity.Employee;
import com.its.samara.project.calendar.backend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional
    public Employee findById(Integer id) {
        return employeeRepository.findAllByIdAndIsDeletedFalse(id).orElse(null);
    }

    @Transactional
    public List<Employee> findAll() {
        return employeeRepository.findAllByIsDeletedFalse();
    }

    @Transactional
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Transactional
    public void softDelete(Employee employee) {
        employee.setIsDeleted(true);
        employeeRepository.save(employee);
    }
}
