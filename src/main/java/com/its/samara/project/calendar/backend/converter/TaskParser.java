package com.its.samara.project.calendar.backend.converter;

import com.its.samara.project.calendar.backend.dto.TaskDTO;
import com.its.samara.project.calendar.backend.entity.Task;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class TaskParser {

    private static final String DATE_FORMAT_FOR_VIEWING = "dd-MM-yyyy";

    public Task convertToEntity(TaskDTO taskDTO) {

        return new Task(
                taskDTO.getId(),
                taskDTO.getStageId(),
                taskDTO.getName(),
                taskDTO.getDescription(),
                taskDTO.getLinkToGitlab(),
                taskDTO.getStatus(),
                LocalDate.parse(taskDTO.getCreateDate()),
                LocalDate.parse(taskDTO.getDeadline()),
                taskDTO.getIsDeleted()
        );
    }

    public TaskDTO convertToDTO(Task task) {

        return new TaskDTO(
                task.getId(),
                task.getStageId(),
                task.getName(),
                task.getDescription(),
                task.getLinkToGitlab(),
                task.getStatus(),
                task.getCreateDate().format(DateTimeFormatter.ofPattern(DATE_FORMAT_FOR_VIEWING)),
                task.getDeadline().format(DateTimeFormatter.ofPattern(DATE_FORMAT_FOR_VIEWING)),
                task.getIsDeleted()
        );
    }
}
