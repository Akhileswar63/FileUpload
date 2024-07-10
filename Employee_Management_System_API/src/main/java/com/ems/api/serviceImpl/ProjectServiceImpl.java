package com.ems.api.serviceImpl;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ems.api.entity.ProjectEntity;
import com.ems.api.repository.ProjectRepository;
import com.ems.api.service.ProjectService;

@Service
@Transactional  // Ensure transactional behavior for database operations
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepo;

    @Override
    public ProjectEntity saveProject(ProjectEntity project) {
        return projectRepo.save(project);
    }

    @Override
    public ProjectEntity updateProject(ProjectEntity project, Integer projectId) {
        // Ensure project exists before updating
        if (!projectRepo.existsById(projectId)) {
            throw new RuntimeException("Project not found");
        }
        project.setProjectId(projectId);
        return projectRepo.save(project);
    }

    @Override
    public ProjectEntity getProjectById(Integer projectId) {
        Optional<ProjectEntity> findById = projectRepo.findById(projectId);
        return findById.orElse(null); // Return null if project is not found
    }

    @Override
    public List<ProjectEntity> getAllProjects() {
        return projectRepo.findAll();
    }

    @Override
    public String deleteProjectById(Integer projectId) {
        // Ensure project exists before deleting
        if (!projectRepo.existsById(projectId)) {
            throw new RuntimeException("Project not found");
        }
        projectRepo.deleteById(projectId);
        return "Project with ID " + projectId + " has been deleted successfully.";
    }
}
