package com.its.samara.project.calendar.backend.repository;

import com.its.samara.project.calendar.backend.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Optional<Employee> findAllByIdAndIsDeletedFalse(Integer id);

    List<Employee> findAllByIsDeletedFalse();

//    @Query("SELECT emp FROM employees emp JOIN emp.projects prj WHERE prj.id = :projectId AND emp.isDeleted = false")
//    Set<Employee> findAllByProjectIdAndIsDeletedFalse(@Param("projectId") Integer projectId);
}
