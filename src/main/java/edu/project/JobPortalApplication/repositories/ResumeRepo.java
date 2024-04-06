package edu.project.JobPortalApplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.project.JobPortalApplication.entity.Resume;

public interface ResumeRepo extends JpaRepository<Resume, Long> {

}
