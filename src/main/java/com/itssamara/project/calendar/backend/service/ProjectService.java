package com.itssamara.project.calendar.backend.service;

import com.itssamara.project.calendar.backend.entity.Project;
import com.itssamara.project.calendar.backend.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Transactional
    public Project findById(Integer id) {
        return projectRepository.findAllByIdAndIsDeletedFalse(id).orElse(null);
    }

    @Transactional
    public List<Project> findAll() {
        return projectRepository.findAllByIsDeletedFalse();
    }

    @Transactional
    public Project save(Project project) {
        return projectRepository.save(project);
    }

    @Transactional
    public void softDelete(Project project) {
        project.setIsDeleted(true);
        projectRepository.save(project);
    }
}
