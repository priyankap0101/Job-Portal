package edu.project.JobPortalApplication.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.project.JobPortalApplication.entity.Job;

public interface JobRepo extends JpaRepository<Job, Long> {

	@Query(value = "select j from Job j")
	public List<Job> getAllJob();
}
