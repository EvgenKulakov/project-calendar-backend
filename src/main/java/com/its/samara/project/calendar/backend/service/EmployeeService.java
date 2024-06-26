package com.its.samara.project.calendar.backend.service;

import com.its.samara.project.calendar.backend.entity.Employee;
import com.its.samara.project.calendar.backend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.IntStream;

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
    public List<Employee> findAllByIds(int[] ids) {
        List<Integer> idsList = IntStream.of(ids).boxed().toList();
        return employeeRepository.findAllByIdInAndIsDeletedFalse(idsList);
    }

    @Transactional
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Transactional
    public Employee update(Integer id, Employee employeeDetails) {
        Optional<Employee> optionalEmployee = employeeRepository.findAllByIdAndIsDeletedFalse(id);

        if (optionalEmployee.isPresent()) {
            Employee existingEmployee = optionalEmployee.get();
            existingEmployee.setFirstName(employeeDetails.getFirstName());
            existingEmployee.setLastName(employeeDetails.getLastName());
            existingEmployee.setProjectIds(employeeDetails.getProjectIds());

            return employeeRepository.save(existingEmployee);
        }

        else return null;
    }

    @Transactional
    public Employee patch(Integer id, Map<String, Object> updates) {
        Optional<Employee> optionalEmployee = employeeRepository.findAllByIdAndIsDeletedFalse(id);
        if (optionalEmployee.isPresent()) {
            Employee existingEmployee = optionalEmployee.get();

            updates.forEach((key, value) -> {
                switch (key) {
                    case "firstName" -> existingEmployee.setFirstName((String) value);
                    case "lastName" -> existingEmployee.setLastName((String) value);
                    case "projectIds" -> existingEmployee.setProjectIds((int[]) value);
                }
            });

            return employeeRepository.save(existingEmployee);
        }

        else return null;
    }

    @Transactional
    public boolean softDelete(Integer id) {
        Optional<Employee> optionalEmployee = employeeRepository.findAllByIdAndIsDeletedFalse(id);
        if (optionalEmployee.isPresent()) {
            Employee employeeDeleted = optionalEmployee.get();
            employeeDeleted.setIsDeleted(true);
            employeeRepository.save(employeeDeleted);
            return true;
        }
        else return false;
    }
}
