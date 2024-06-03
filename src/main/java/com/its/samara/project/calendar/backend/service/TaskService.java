package com.its.samara.project.calendar.backend.service;

import com.its.samara.project.calendar.backend.entity.Task;
import com.its.samara.project.calendar.backend.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Transactional
    public Task findById(Integer id) {
        return taskRepository.findAllByIdAndIsDeletedFalse(id).orElse(null);
    }

    @Transactional
    public List<Task> findAll() {
        return taskRepository.findAllByIsDeletedFalse();
    }

    @Transactional
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    @Transactional
    public void softDelete(Task task) {
        task.setIsDeleted(true);
        taskRepository.save(task);
    }
}
