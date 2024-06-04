package com.its.samara.project.calendar.backend.dto;

import com.its.samara.project.calendar.backend.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StageDTO {

    private Integer id;
    private Integer projectId;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate deadline;
    private List<Task> tasks;
    private Boolean isDeleted;
}
