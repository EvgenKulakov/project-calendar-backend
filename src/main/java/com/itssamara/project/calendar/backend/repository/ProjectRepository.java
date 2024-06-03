package com.itssamara.project.calendar.backend.repository;

import com.itssamara.project.calendar.backend.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

    Optional<Project> findAllByIdAndIsDeletedFalse(Integer id);

    List<Project> findAllByIsDeletedFalse();
}
