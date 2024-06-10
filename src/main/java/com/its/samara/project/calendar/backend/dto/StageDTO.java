package com.its.samara.project.calendar.backend.dto;

import com.its.samara.project.calendar.backend.entity.Task;
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
    private List<Task> tasks;
    private Boolean isDeleted;
}
