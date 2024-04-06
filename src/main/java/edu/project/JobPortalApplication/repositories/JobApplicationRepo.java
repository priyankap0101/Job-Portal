package edu.project.JobPortalApplication.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.project.JobPortalApplication.entity.JobApplication;

public interface JobApplicationRepo extends JpaRepository<JobApplication, Long> {

	@Query("select a from JobApplication a where a.job=?1")
	public List<JobApplication> getJobApplicationByJob();
}
