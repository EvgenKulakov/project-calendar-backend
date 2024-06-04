package com.its.samara.project.calendar.backend.repository;

import com.its.samara.project.calendar.backend.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    Optional<Task> findAllByIdAndIsDeletedFalse(Integer id);

    List<Task> findAllByIsDeletedFalse();

    List<Task> findAllByStageIdAndIsDeletedFalse(Integer stageId);
}
