package com.its.samara.project.calendar.backend.converter;

import com.its.samara.project.calendar.backend.dto.ProjectDTO;
import com.its.samara.project.calendar.backend.entity.Employee;
import com.its.samara.project.calendar.backend.entity.Project;
import com.its.samara.project.calendar.backend.entity.Stage;
import com.its.samara.project.calendar.backend.service.EmployeeService;
import com.its.samara.project.calendar.backend.service.StageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectParser {

    @Autowired
    private StageService stageService;
    @Autowired
    private EmployeeService employeeService;

    public Project convertToEntity(ProjectDTO projectDTO) {

        int[] employeeIds = projectDTO.getEmployees() != null
                ? projectDTO.getEmployees().stream().mapToInt(Employee::getId).toArray()
                : null;

        Project project = new Project(
                projectDTO.getId(),
                projectDTO.getName(),
                projectDTO.getDescription(),
                projectDTO.getStatus(),
                projectDTO.getCurrentStage().getId(),
                projectDTO.getStartDate(),
                projectDTO.getDeadline(),
                employeeIds,
                projectDTO.getEstimatedHours(),
                projectDTO.getIsDeleted()
        );

        return project;
    }

    public ProjectDTO convertToDTO(Project project) {

        List<Stage> stageList = stageService.findAllByProjectId(project.getId());
        Stage currentStage = stageList.stream()
                .filter(stage -> stage.getId().equals(project.getCurrentStageId()))
                .findFirst().orElse(null);

        List<Employee> employees = project.getEmployeeIds() != null
                ? employeeService.findAllByIds(project.getEmployeeIds())
                : null;

        ProjectDTO projectDTO = new ProjectDTO(
                project.getId(),
                project.getName(),
                project.getDescription(),
                project.getStatus(),
                currentStage,
                project.getStartDate(),
                project.getDeadline(),
                project.getEstimatedHours(),
                stageList,
                employees,
                project.getIsDeleted()
        );

        return projectDTO;
    }
}
