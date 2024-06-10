package com.its.samara.project.calendar.backend.service;

import com.its.samara.project.calendar.backend.converter.ProjectParser;
import com.its.samara.project.calendar.backend.converter.StageParser;
import com.its.samara.project.calendar.backend.dto.ProjectDTO;
import com.its.samara.project.calendar.backend.dto.StageDTO;
import com.its.samara.project.calendar.backend.entity.Employee;
import com.its.samara.project.calendar.backend.entity.Project;
import com.its.samara.project.calendar.backend.entity.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ProjectSaveOperator {
    @Autowired
    private StageService stageService;
    @Autowired
    private StageParser stageParser;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ProjectParser projectParser;
    @Autowired
    private ProjectService projectService;

    @Transactional
    public Project saveProject(ProjectDTO projectDTO) {

        Project project = projectParser.convertToEntity(projectDTO);
        Project savedProject = projectService.save(project);

        if (projectDTO.getCurrentStage() != null) {
            Stage currentStage = stageParser.convertToEntity(projectDTO.getCurrentStage());
            currentStage.setProjectId(savedProject.getId());
            Stage savedCurrentStage = stageService.save(currentStage);
            savedProject.setCurrentStageId(savedCurrentStage.getId());
            savedProject = projectService.save(savedProject);
        }

        for (StageDTO stageDTO : projectDTO.getOtherStages()) {
            Stage stage = stageParser.convertToEntity(stageDTO);
            stage.setProjectId(savedProject.getId());
            stageService.save(stage);
        }

        if (!projectDTO.getEmployees().isEmpty()) {
            for (Employee employee : projectDTO.getEmployees()) {
                if (employee.getProjectIds() != null) {
                    int[] newArray = new int[employee.getProjectIds().length + 1];
                    System.arraycopy(employee.getProjectIds(), 0, newArray, 0, employee.getProjectIds().length);
                    newArray[newArray.length - 1] = savedProject.getId();
                    employee.setProjectIds(newArray);
                } else {
                    employee.setProjectIds(new int[]{savedProject.getId()});
                }
                employeeService.save(employee);
            }
        }

        return savedProject;
    }
}
