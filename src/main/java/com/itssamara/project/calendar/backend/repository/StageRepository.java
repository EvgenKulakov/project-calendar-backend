package com.itssamara.project.calendar.backend.repository;

import com.itssamara.project.calendar.backend.entity.Stage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StageRepository extends JpaRepository<Stage, Integer> {

    Optional<Stage> findAllByIdAndIsDeletedFalse(Integer id);

    List<Stage> findAllByIsDeletedFalse();
}
