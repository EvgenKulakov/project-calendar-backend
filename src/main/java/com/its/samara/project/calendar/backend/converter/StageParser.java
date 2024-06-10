package com.its.samara.project.calendar.backend.converter;

import com.its.samara.project.calendar.backend.dto.StageDTO;
import com.its.samara.project.calendar.backend.dto.TaskDTO;
import com.its.samara.project.calendar.backend.entity.Stage;
import com.its.samara.project.calendar.backend.entity.Task;
import com.its.samara.project.calendar.backend.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class StageParser {
    @Autowired
    private TaskService taskService;
    @Autowired
    private TaskParser taskParser;

    private static final String DATE_FORMAT_FOR_VIEWING = "dd-MM-yyyy";

    public Stage convertToEntity(StageDTO stageDTO) {

        Stage stage = new Stage(
                stageDTO.getId(),
                stageDTO.getProjectId(),
                stageDTO.getName(),
                stageDTO.getDescription(),
                LocalDate.parse(stageDTO.getStartDate()),
                LocalDate.parse(stageDTO.getDeadline()),
                stageDTO.getIsDeleted()
        );

        return stage;
    }

    public StageDTO convertToDTO(Stage stage) {

        List<Task> taskList = taskService.findAllByStageId(stage.getId());
        List<TaskDTO> taskDTOList = taskList.stream().map(taskParser::convertToDTO).toList();

        StageDTO stageDTO = new StageDTO(
                stage.getId(),
                stage.getProjectId(),
                stage.getName(),
                stage.getDescription(),
                stage.getStartDate().format(DateTimeFormatter.ofPattern(DATE_FORMAT_FOR_VIEWING)),
                stage.getDeadline().format(DateTimeFormatter.ofPattern(DATE_FORMAT_FOR_VIEWING)),
                taskDTOList,
                stage.getIsDeleted()
        );

        return stageDTO;
    }
}
