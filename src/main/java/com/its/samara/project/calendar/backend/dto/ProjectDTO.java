package com.its.samara.project.calendar.backend.dto;

import com.its.samara.project.calendar.backend.entity.Employee;
import com.its.samara.project.calendar.backend.entity.Project;
import com.its.samara.project.calendar.backend.entity.Stage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDTO {

    private Integer id;
    private String name;
    private String description;
    private Project.Status status;
    private Integer currentStageId;
    private LocalDate startDate;
    private LocalDate deadline;
    private Integer estimatedHours;
    private Boolean isDeleted;
    private List<Stage> stages;
    private Set<Employee> employees;
}
