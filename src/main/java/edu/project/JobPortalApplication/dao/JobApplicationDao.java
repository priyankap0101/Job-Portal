package edu.project.JobPortalApplication.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.project.JobPortalApplication.entity.JobApplication;
import edu.project.JobPortalApplication.repositories.JobApplicationRepo;

@Repository
public class JobApplicationDao {

	@Autowired
	private JobApplicationRepo applicationRepo;

	public JobApplication createJobApplication(JobApplication application) {
		return applicationRepo.save(application);
	}
	public List<JobApplication> getJobApplicationByJob()
	{
		return applicationRepo.getJobApplicationByJob();
	}

	
}
