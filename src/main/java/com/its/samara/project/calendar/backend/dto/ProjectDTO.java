package com.its.samara.project.calendar.backend.dto;

import com.its.samara.project.calendar.backend.entity.Employee;
import com.its.samara.project.calendar.backend.entity.Project;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDTO {

    private Integer id;
    private String name;
    private String description;
    private Project.Status status;
    private StageDTO currentStage;
    private String startDate;
    private String deadline;
    private Integer estimatedHours;
    private List<StageDTO> otherStages;
    private List<Employee> employees;
    private Boolean isDeleted;
}
