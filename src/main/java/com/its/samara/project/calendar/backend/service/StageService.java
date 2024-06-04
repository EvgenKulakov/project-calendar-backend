package com.its.samara.project.calendar.backend.service;

import com.its.samara.project.calendar.backend.entity.Project;
import com.its.samara.project.calendar.backend.entity.Stage;
import com.its.samara.project.calendar.backend.repository.StageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class StageService {

    @Autowired
    private StageRepository stageRepository;

    @Transactional
    public Stage findById(Integer id) {
        return stageRepository.findAllByIdAndIsDeletedFalse(id).orElse(null);
    }

    @Transactional
    public List<Stage> findAll() {
        return stageRepository.findAllByIsDeletedFalse();
    }

    @Transactional
    public List<Stage> findAllByProjectId(Integer projectId) {
        return stageRepository.findAllByProjectIdAndIsDeletedFalse(projectId);
    }

    @Transactional
    public Stage save(Stage stage) {
        return stageRepository.save(stage);
    }

    @Transactional
    public Stage update(Integer id, Stage stageDetails) {
        Optional<Stage> optionalStage = stageRepository.findAllByIdAndIsDeletedFalse(id);

        if (optionalStage.isPresent()) {
            Stage existingStage = optionalStage.get();
//            existingStage.setProject(stageDetails.getProject());
            existingStage.setName(stageDetails.getName());
            existingStage.setDescription(stageDetails.getDescription());
            existingStage.setStartDate(stageDetails.getStartDate());
            existingStage.setDeadline(stageDetails.getDeadline());

            return stageRepository.save(existingStage);
        }

        else return null;
    }

    public Stage patch(Integer id, Map<String, Object> updates) {
        Optional<Stage> optionalStage = stageRepository.findAllByIdAndIsDeletedFalse(id);
        if (optionalStage.isPresent()) {
            Stage existingStage = optionalStage.get();

            updates.forEach((key, value) -> {
                switch (key) {
//                    case "project" -> existingStage.setProject((Project) value);
                    case "name" -> existingStage.setName((String) value);
                    case "description" -> existingStage.setDescription((String) value);
                    case "startDate" -> existingStage.setStartDate((LocalDate) value);
                    case "deadline" -> existingStage.setDeadline((LocalDate) value);
                }
            });

            return stageRepository.save(existingStage);
        }

        else return null;
    }

    @Transactional
    public boolean softDelete(Integer id) {
        Optional<Stage> optionalStage = stageRepository.findAllByIdAndIsDeletedFalse(id);
        if (optionalStage.isPresent()) {
            Stage stageDeleted = optionalStage.get();
            stageDeleted.setIsDeleted(true);
            stageRepository.save(stageDeleted);
            return true;
        }
        else return false;
    }
}
