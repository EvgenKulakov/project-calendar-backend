package com.its.samara.project.calendar.backend.service;

import com.its.samara.project.calendar.backend.entity.Task;
import com.its.samara.project.calendar.backend.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    public List<Task> findAllByStageId(Integer stageId) {
        return taskRepository.findAllByStageIdAndIsDeletedFalse(stageId);
    }

    @Transactional
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    @Transactional
    public Task update(Integer id, Task taskDetails) {
        Optional<Task> optionalTask = taskRepository.findAllByIdAndIsDeletedFalse(id);

        if (optionalTask.isPresent()) {
            Task existingTask = optionalTask.get();
            existingTask.setStageId(taskDetails.getStageId());
            existingTask.setName(taskDetails.getName());
            existingTask.setDescription(taskDetails.getDescription());
            existingTask.setLinkToGitlab(taskDetails.getLinkToGitlab());
            existingTask.setStatus(taskDetails.getStatus());
            existingTask.setCreateDate(taskDetails.getCreateDate());
            existingTask.setDeadline(taskDetails.getDeadline());

            return taskRepository.save(existingTask);
        }

        else return null;
    }

    @Transactional
    public Task patch(Integer id, Map<String, Object> updates) {
        Optional<Task> optionalTask = taskRepository.findAllByIdAndIsDeletedFalse(id);
        if (optionalTask.isPresent()) {
            Task existingTask = optionalTask.get();

            updates.forEach((key, value) -> {
                switch (key) {
                    case "stageId" -> existingTask.setStageId((Integer) value);
                    case "name" -> existingTask.setName((String) value);
                    case "description" -> existingTask.setDescription((String) value);
                    case "linkToGitlab" -> existingTask.setLinkToGitlab((String) value);
                    case "status" -> existingTask.setStatus((Task.Status) value);
                    case "createDate" -> existingTask.setCreateDate((LocalDate) value);
                    case "deadline" -> existingTask.setDeadline((LocalDate) value);
                }
            });

            return taskRepository.save(existingTask);
        }

        else return null;
    }

    @Transactional
    public boolean softDelete(Integer id) {
        Optional<Task> optionalTask = taskRepository.findAllByIdAndIsDeletedFalse(id);
        if (optionalTask.isPresent()) {
            Task taskDeleted = optionalTask.get();
            taskDeleted.setIsDeleted(true);
            taskRepository.save(taskDeleted);
            return true;
        }
        else return false;
    }
}
