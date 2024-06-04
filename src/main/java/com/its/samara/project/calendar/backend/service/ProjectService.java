package com.its.samara.project.calendar.backend.service;

import com.its.samara.project.calendar.backend.entity.Employee;
import com.its.samara.project.calendar.backend.entity.Project;
import com.its.samara.project.calendar.backend.entity.Stage;
import com.its.samara.project.calendar.backend.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Transactional
    public Project findById(Integer id) {
        return projectRepository.findAllByIdAndIsDeletedFalse(id).orElse(null);
    }

    @Transactional
    public List<Project> findAll() {
        return projectRepository.findAllByIsDeletedFalse();
    }

    @Transactional
    public Project save(Project project) {
        return projectRepository.save(project);
    }

    @Transactional
    public Project update(Integer id, Project projectDetails) {
        Optional<Project> optionalProject = projectRepository.findAllByIdAndIsDeletedFalse(id);

        if (optionalProject.isPresent()) {
            Project existingProject = optionalProject.get();
            existingProject.setName(projectDetails.getName());
            existingProject.setDescription(projectDetails.getDescription());
            existingProject.setStatus(projectDetails.getStatus());
//            existingProject.setCurrentStage(projectDetails.getCurrentStage());
            existingProject.setStartDate(projectDetails.getStartDate());
            existingProject.setDeadline(projectDetails.getDeadline());
            existingProject.setEstimatedHours(projectDetails.getEstimatedHours());
//            existingProject.setStages(projectDetails.getStages());
//            existingProject.setEmployees(projectDetails.getEmployees());

            return projectRepository.save(existingProject);
        }

        else return null;
    }

    public Project patch(Integer id, Map<String, Object> updates) {
        Optional<Project> optionalProject = projectRepository.findAllByIdAndIsDeletedFalse(id);
        if (optionalProject.isPresent()) {
            Project existingProject = optionalProject.get();

            updates.forEach((key, value) -> {
                switch (key) {
                    case "name" -> existingProject.setName((String) value);
                    case "description" -> existingProject.setDescription((String) value);
                    case "status" -> existingProject.setStatus((Project.Status) value);
//                    case "currentStage" -> existingProject.setCurrentStage((Stage) value);
                    case "startDate" -> existingProject.setStartDate((LocalDate) value);
                    case "deadline" -> existingProject.setDeadline((LocalDate) value);
                    case "estimatedHours" -> existingProject.setEstimatedHours((Integer) value);
//                    case "stages" -> existingProject.setStages((List<Stage>) value);
//                    case "employees" -> existingProject.setEmployees((Set<Employee>) value);
                }
            });

            return projectRepository.save(existingProject);
        }

        else return null;
    }

    @Transactional
    public boolean softDelete(Integer id) {
        Optional<Project> optionalProject = projectRepository.findAllByIdAndIsDeletedFalse(id);
        if (optionalProject.isPresent()) {
            Project projectDeleted = optionalProject.get();
            projectDeleted.setIsDeleted(true);
            projectRepository.save(projectDeleted);
            return true;
        }
        else return false;
    }
}
