package edu.project.JobPortalApplication.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.project.JobPortalApplication.dto.JobDto;
import edu.project.JobPortalApplication.entity.Job;
import edu.project.JobPortalApplication.repositories.JobRepo;

@Repository
public class JobDao {

	@Autowired
	private JobRepo jobRepo;

	public Job addJob(Job job) {
		return jobRepo.save(job);
	}

	public Job updateJob(Job job, long id) {
		Optional<Job> job2 = jobRepo.findById(id);

		if (job2.isEmpty()) {
			return null;
		} else {

			job.setJobId(id);
			return jobRepo.save(job);

		}

	}

	public Job getJob(long jobId) {

		Optional<Job> optional = jobRepo.findById(jobId);

		if (optional.isEmpty()) {
			return null;
		} else {
			return optional.get();
		}

	}

	public void deleteJob(Job job) {

		jobRepo.delete(job);
	}

	public List<Job> getAllJob() {
		return jobRepo.getAllJob();
	}

}
