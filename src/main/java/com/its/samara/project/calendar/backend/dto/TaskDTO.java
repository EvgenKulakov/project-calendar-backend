package com.its.samara.project.calendar.backend.dto;

import com.its.samara.project.calendar.backend.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {

    private Integer id;
    private Integer stageId;
    private String name;
    private String description;
    private String linkToGitlab;
    private Task.Status status;
    private String createDate;
    private String deadline;
    private Boolean isDeleted;

}
