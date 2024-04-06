package edu.project.JobPortalApplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.project.JobPortalApplication.entity.Project;

public interface ProjectRepo extends JpaRepository<Project, Long> {

}
