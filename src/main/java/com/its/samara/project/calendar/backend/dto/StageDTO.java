package com.its.samara.project.calendar.backend.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class StageDTO {

    private Integer id;
    private Integer projectId;
    private String name;
    private String description;
    private String startDate;
    private String deadline;
    private List<TaskDTO> tasks;
    private Boolean isDeleted;
}
