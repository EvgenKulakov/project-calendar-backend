package com.its.samara.project.calendar.backend.converter;

import com.its.samara.project.calendar.backend.dto.ProjectDTO;
import com.its.samara.project.calendar.backend.dto.StageDTO;
import com.its.samara.project.calendar.backend.entity.Employee;
import com.its.samara.project.calendar.backend.entity.Project;
import com.its.samara.project.calendar.backend.entity.Stage;
import com.its.samara.project.calendar.backend.service.EmployeeService;
import com.its.samara.project.calendar.backend.service.StageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectParser {

    @Autowired
    private StageService stageService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private StageParser stageParser;

    private static final String DATE_FORMAT_FOR_VIEWING = "dd-MM-yyyy";

    public Project convertToEntity(ProjectDTO projectDTO) {

        Integer currentStageId = projectDTO.getCurrentStage() != null ? projectDTO.getCurrentStage().getId() : null;

        int[] employeeIds = projectDTO.getEmployees() != null
                ? projectDTO.getEmployees().stream().mapToInt(Employee::getId).toArray()
                : null;

        Project project = new Project(
                projectDTO.getId(),
                projectDTO.getName(),
                projectDTO.getDescription(),
                projectDTO.getStatus(),
                currentStageId,
                LocalDate.parse(projectDTO.getStartDate()) ,
                LocalDate.parse(projectDTO.getDeadline()),
                employeeIds,
                projectDTO.getEstimatedHours(),
                projectDTO.getIsDeleted()
        );

        return project;
    }

    public ProjectDTO convertToDTO(Project project) {

        List<Stage> stageList = stageService.findAllByProjectId(project.getId());
        List<StageDTO> otherStageDTOList = new ArrayList<>(stageList.stream().map(stageParser::convertToDTO).toList());
        StageDTO currentStage = otherStageDTOList.stream()
                .filter(stageDTO -> stageDTO.getId().equals(project.getCurrentStageId()))
                .findFirst().orElse(null);
        if (currentStage != null) {
            otherStageDTOList.remove(currentStage);
        }

        List<Employee> employees = project.getEmployeeIds() != null
                ? employeeService.findAllByIds(project.getEmployeeIds())
                : null;

        ProjectDTO projectDTO = new ProjectDTO(
                project.getId(),
                project.getName(),
                project.getDescription(),
                project.getStatus(),
                currentStage,
                project.getStartDate().format(DateTimeFormatter.ofPattern(DATE_FORMAT_FOR_VIEWING)),
                project.getDeadline().format(DateTimeFormatter.ofPattern(DATE_FORMAT_FOR_VIEWING)),
                project.getEstimatedHours(),
                otherStageDTOList,
                employees,
                project.getIsDeleted()
        );

        return projectDTO;
    }
}
