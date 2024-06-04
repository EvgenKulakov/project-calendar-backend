package com.its.samara.project.calendar.backend.converter;

import com.its.samara.project.calendar.backend.dto.StageDTO;
import com.its.samara.project.calendar.backend.entity.Stage;
import com.its.samara.project.calendar.backend.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StageParser {

    @Autowired
    private TaskService taskService;

    public Stage convertToEntity(StageDTO stageDTO) {

        Stage stage = new Stage(
                stageDTO.getId(),
                stageDTO.getProjectId(),
                stageDTO.getName(),
                stageDTO.getDescription(),
                stageDTO.getStartDate(),
                stageDTO.getDeadline(),
                stageDTO.getIsDeleted()
        );

        return stage;
    }

    public StageDTO convertToDTO(Stage stage) {

        StageDTO stageDTO = new StageDTO(
                stage.getId(),
                stage.getProjectId(),
                stage.getName(),
                stage.getDescription(),
                stage.getStartDate(),
                stage.getDeadline(),
                taskService.findAllByStageId(stage.getId()),
                stage.getIsDeleted()
        );

        return stageDTO;
    }
}
