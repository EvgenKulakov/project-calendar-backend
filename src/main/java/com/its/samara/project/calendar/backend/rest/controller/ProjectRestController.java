package com.its.samara.project.calendar.backend.rest.controller;

import com.its.samara.project.calendar.backend.entity.Employee;
import com.its.samara.project.calendar.backend.entity.Stage;
import com.its.samara.project.calendar.backend.service.EmployeeService;
import com.its.samara.project.calendar.backend.service.ProjectService;
import com.its.samara.project.calendar.backend.entity.Project;
import com.its.samara.project.calendar.backend.service.StageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
public class ProjectRestController {

    @Autowired
    private ProjectService projectService;
    @Autowired
    private StageService stageService;
    @Autowired
    private EmployeeService employeeService;


    @GetMapping("/projects")
    public List<Project> getAllProjects() {
        List<Project> projects = projectService.findAll();
        return projects;
    }

    @GetMapping("/projects/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable("id") Integer id) {
        Project project = projectService.findById(id);
        if (project == null) {
            return ResponseEntity.notFound().build();
        }

//        List<Stage> stages = stageService.findAllByProjectId(project.getId());
//        Set<Employee> employees = projectService.findAllEmployeeByProjectId(project.getId());


        return ResponseEntity.ok(project);
    }

    @PostMapping("/projects")
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        Project createdProject = projectService.save(project);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProject);
    }

    @PutMapping("/projects/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable("id") Integer id, @RequestBody Project projectDetails) {
        Project updatedProject = projectService.update(id, projectDetails);
        if (updatedProject == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedProject);
    }

    @PatchMapping("/projects/{id}")
    public ResponseEntity<Project> patchProject(@PathVariable("id") Integer id, @RequestBody Map<String, Object> updates) {
        Project patchedProject = projectService.patch(id, updates);
        if (patchedProject == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(patchedProject);
    }

    @DeleteMapping("/projects/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable("id") Integer id) {
        boolean deleted = projectService.softDelete(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
