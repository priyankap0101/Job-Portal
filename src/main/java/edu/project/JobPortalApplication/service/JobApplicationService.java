package edu.project.JobPortalApplication.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.project.JobPortalApplication.dao.ApplicantDao;
import edu.project.JobPortalApplication.dao.JobApplicationDao;
import edu.project.JobPortalApplication.dao.JobDao;
import edu.project.JobPortalApplication.entity.Applicant;
import edu.project.JobPortalApplication.entity.Job;
import edu.project.JobPortalApplication.entity.JobApplication;
import edu.project.JobPortalApplication.entity.Resume;
import edu.project.JobPortalApplication.exception.JobNotFoundById;
import edu.project.JobPortalApplication.util.responseStructre;

@Service
public class JobApplicationService {

	@Autowired
	private JobApplicationDao jobApplicationDao;

	@Autowired
	private ApplicantDao applicantDao;

	@Autowired
	private JobDao jobDao;

	public ResponseEntity<responseStructre<JobApplication>> createJobApplication(long applicantId, long jobId) {
		Applicant applicant = applicantDao.getApplicant(applicantId);

		if (applicant != null) {
			Job job = jobDao.getJob(jobId);

			if (job != null) {
				JobApplication application = new JobApplication();
				application.setJobApplicationDateTime(LocalDateTime.now());
				application.setJob(job);
				application.setApplicant(applicant);

				// saving the job application

				application = jobApplicationDao.createJobApplication(application);

				// setting and updating job application for the job
				job.getApplications();
				jobDao.addJob(job);

				applicant.getJobApplications().add(application);

				applicantDao.addApplicant(applicant);

				responseStructre<JobApplication> responseStructre = new responseStructre<>();

				responseStructre.setStatusCode(HttpStatus.CREATED.value());
				responseStructre.setMessage("JobApplication Added Successfully");
				responseStructre.setData(application);

				return new ResponseEntity<responseStructre<JobApplication>>(responseStructre, HttpStatus.CREATED);

			} else {
				return null;
			}

		} else {
			return null;
		}
	}

}
