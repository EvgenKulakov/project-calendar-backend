package com.its.samara.project.calendar.backend.rest.controller;

import com.its.samara.project.calendar.backend.converter.TaskParser;
import com.its.samara.project.calendar.backend.dto.TaskDTO;
import com.its.samara.project.calendar.backend.entity.Task;
import com.its.samara.project.calendar.backend.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class TaskRestController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private TaskParser taskParser;

    @GetMapping("/tasks")
    public List<Task> getAllProjects() {
        return taskService.findAll();
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable("id") Integer id) {
        Task task = taskService.findById(id);
        if (task == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(task);
    }

    @PostMapping("/tasks")
    public ResponseEntity<Task> createTask(@RequestBody TaskDTO taskDTO) {
        Task task = taskParser.convertToEntity(taskDTO);
        Task createdTask = taskService.save(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable("id") Integer id, @RequestBody Task taskDetails) {
        Task updatedTask = taskService.update(id, taskDetails);
        if (updatedTask == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedTask);
    }

    @PatchMapping("/tasks/{id}")
    public ResponseEntity<Task> patchTask(@PathVariable("id") Integer id, @RequestBody Map<String, Object> updates) {
        Task patchedTask = taskService.patch(id, updates);
        if (patchedTask == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(patchedTask);
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable("id") Integer id) {
        boolean deleted = taskService.softDelete(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
