package com.its.samara.project.calendar.backend.rest.controller;

import com.its.samara.project.calendar.backend.converter.StageParser;
import com.its.samara.project.calendar.backend.dto.StageDTO;
import com.its.samara.project.calendar.backend.entity.Stage;
import com.its.samara.project.calendar.backend.service.StageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class StageRestController {

    @Autowired
    private StageService stageService;
    @Autowired
    private StageParser stageParser;

    @GetMapping("/stages")
    public List<Stage> getAllProjects() {
        return stageService.findAll();
    }

    @GetMapping("/stages/{id}")
    public ResponseEntity<StageDTO> getStageById(@PathVariable("id") Integer id) {
        Stage stage = stageService.findById(id);
        if (stage == null) {
            return ResponseEntity.notFound().build();
        }

        StageDTO stageDTO = stageParser.convertToDTO(stage);

        return ResponseEntity.ok(stageDTO);
    }

    @PostMapping("/stages")
    public ResponseEntity<Stage> createStage(@RequestBody Stage stage) {
        Stage createdStage = stageService.save(stage);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStage);
    }

    @PutMapping("/stages/{id}")
    public ResponseEntity<Stage> updateStage(@PathVariable("id") Integer id, @RequestBody Stage stageDetails) {
        Stage updatedStage = stageService.update(id, stageDetails);
        if (updatedStage == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedStage);
    }

    @PatchMapping("/stages/{id}")
    public ResponseEntity<Stage> patchStage(@PathVariable("id") Integer id, @RequestBody Map<String, Object> updates) {
        Stage patchedStage = stageService.patch(id, updates);
        if (patchedStage == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(patchedStage);
    }

    @DeleteMapping("/stages/{id}")
    public ResponseEntity<Void> deleteStage(@PathVariable("id") Integer id) {
        boolean deleted = stageService.softDelete(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
