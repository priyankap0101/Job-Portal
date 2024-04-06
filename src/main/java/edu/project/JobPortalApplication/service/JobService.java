package edu.project.JobPortalApplication.service;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.project.JobPortalApplication.dao.EmployerDao;
import edu.project.JobPortalApplication.dao.JobApplicationDao;
import edu.project.JobPortalApplication.dao.JobDao;
import edu.project.JobPortalApplication.dto.JobDto;
import edu.project.JobPortalApplication.entity.Employer;
import edu.project.JobPortalApplication.entity.Job;
import edu.project.JobPortalApplication.entity.JobApplication;
import edu.project.JobPortalApplication.exception.EmployerNotFoundById;
import edu.project.JobPortalApplication.exception.JobNotFoundById;
import edu.project.JobPortalApplication.util.responseStructre;

@Service
public class JobService {

	@Autowired
	private JobDao dao;

	@Autowired
	private EmployerDao employerDao;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private JobApplicationDao jobApplicationDao;

	public ResponseEntity<responseStructre<Job>> addJob(JobDto dto, long employerId) {
		Employer employer = employerDao.getEmployer(employerId);

		if (employer != null) {
			Job job = this.mapper.map(dto, Job.class);

			job.setJobCreationDateTime(LocalDateTime.now()); // current date and time

			job.setEmployer(employer);

			job = dao.addJob(job);

			employer.getJobs().add(job);

			employerDao.addEmployer(employer);

			dto.setJobId(job.getJobId());

			responseStructre<Job> responseStructre = new responseStructre<>();

			responseStructre.setStatusCode(HttpStatus.CREATED.value());
			responseStructre.setMessage("Job Added Successfully");
			responseStructre.setData(dto);

			return new ResponseEntity<responseStructre<Job>>(responseStructre, HttpStatus.CREATED);
		} else {
			throw new EmployerNotFoundById("Employer not Found By Id");
		}
	}

	public ResponseEntity<responseStructre<Job>> getJob(long jobId) {
		Job job = dao.getJob(jobId);

		if (job != null) {
			responseStructre<Job> responseStructre = new responseStructre<>();

			responseStructre.setStatusCode(HttpStatus.OK.value());
			responseStructre.setMessage("Job With Requested Id Fetched");
			responseStructre.setData(job);

			return new ResponseEntity<responseStructre<Job>>(responseStructre, HttpStatus.OK);

		} else {
			throw new JobNotFoundById("Job not found with requested id");
		}

	}

	public ResponseEntity<responseStructre<JobDto>> updateJob(Job job, long id) {
		Job exJob = dao.getJob(id);
		if (exJob != null) {

			job.setJobId(exJob.getJobId());
			job.setApplications(exJob.getApplications());
			job.setEmployer(exJob.getEmployer());
			job.setJobCreationDateTime(exJob.getJobCreationDateTime());

			dao.updateJob(job, id);

			JobDto jobDto = this.mapper.map(job, JobDto.class);

			responseStructre<JobDto> responseStructre = new responseStructre<>();

			responseStructre.setStatusCode(HttpStatus.OK.value());
			responseStructre.setMessage("Job With Requested Id Updated");
			responseStructre.setData(job);

			return new ResponseEntity<responseStructre<JobDto>>(responseStructre, HttpStatus.OK);

		} else {
			throw new JobNotFoundById("Job not found with requested id");
		}

	}

	public ResponseEntity<responseStructre<JobDto>> deleteJob(long jobId) {
		Job job = dao.getJob(jobId);

		if (job != null) {
			for (JobApplication jobApplication : job.getApplications()) {
				jobApplication.setJob(null);

				jobApplicationDao.createJobApplication(jobApplication);

			}

			dao.deleteJob(job);
			JobDto jobDto = this.mapper.map(job, JobDto.class);

			responseStructre<JobDto> responseStructre = new responseStructre<>();

			responseStructre.setStatusCode(HttpStatus.OK.value());
			responseStructre.setMessage("Job With Requested Id Deleted");
			responseStructre.setData(job);

			return new ResponseEntity<responseStructre<JobDto>>(responseStructre, HttpStatus.OK);

		} else {
			throw new JobNotFoundById("Job not found with requested id");
		}
	}

	public List<Job> getAllJob() {
		return dao.getAllJob();

	}

}
