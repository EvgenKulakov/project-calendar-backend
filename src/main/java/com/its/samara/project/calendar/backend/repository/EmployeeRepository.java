package com.its.samara.project.calendar.backend.repository;

import com.its.samara.project.calendar.backend.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Optional<Employee> findAllByIdAndIsDeletedFalse(Integer id);

    List<Employee> findAllByIsDeletedFalse();
}
