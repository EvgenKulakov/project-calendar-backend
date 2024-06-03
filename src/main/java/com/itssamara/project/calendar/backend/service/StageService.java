package com.itssamara.project.calendar.backend.service;

import com.itssamara.project.calendar.backend.entity.Stage;
import com.itssamara.project.calendar.backend.repository.StageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public Stage save(Stage stage) {
        return stageRepository.save(stage);
    }

    @Transactional
    public void softDelete(Stage stage) {
        stage.setIsDeleted(true);
        stageRepository.save(stage);
    }
}
