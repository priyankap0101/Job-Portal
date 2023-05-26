package edu.project.JobPortalApplication.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.project.JobPortalApplication.dao.ApplicantDao;
import edu.project.JobPortalApplication.dao.JobApplicationDao;
import edu.project.JobPortalApplication.dao.ProjectDao;
import edu.project.JobPortalApplication.dao.ResumeDao;
import edu.project.JobPortalApplication.dto.ApplicantDto;
import edu.project.JobPortalApplication.entity.Applicant;
import edu.project.JobPortalApplication.entity.JobApplication;
import edu.project.JobPortalApplication.entity.Project;
import edu.project.JobPortalApplication.entity.Resume;
import edu.project.JobPortalApplication.exception.ApplicantNotFoundById;
import edu.project.JobPortalApplication.util.responseStructre;

@Service
public class ApplicantService {

	@Autowired
	private ApplicantDao applicantDao;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private JobApplicationDao applicationDao;

	@Autowired
	private ResumeDao resumeDao;

	@Autowired
	private ProjectDao projectDao;

	public ResponseEntity<responseStructre<ApplicantDto>> saveApplicant(Applicant applicant) {

		applicant = applicantDao.addApplicant(applicant);

		ApplicantDto applicantDto = this.modelMapper.map(applicant, ApplicantDto.class);

		responseStructre<ApplicantDto> responseStructre = new responseStructre<>();

		responseStructre.setStatusCode(HttpStatus.CREATED.value());

		responseStructre.setMessage("Applicant Added");

		responseStructre.setData(applicantDto);

		return new ResponseEntity<responseStructre<ApplicantDto>>(responseStructre, HttpStatus.CREATED);
	}

	public ResponseEntity<responseStructre<ApplicantDto>> getApplicantById(int applicantId) {

		Applicant applicant = applicantDao.getApplicant(applicantId);

		if (applicant != null) {
			ApplicantDto applicantDto = this.modelMapper.map(applicant, ApplicantDto.class);
			responseStructre<ApplicantDto> responseStructre = new responseStructre<>();
			responseStructre.setStatusCode(HttpStatus.FOUND.value());
			responseStructre.setMessage("Applicant Found");
			responseStructre.setData(applicantDto);

			return new ResponseEntity<responseStructre<ApplicantDto>>(responseStructre, HttpStatus.CREATED);
		}

		throw new ApplicantNotFoundById("Applicant Not Found");
	}

	public ResponseEntity<responseStructre<ApplicantDto>> updateApplicant(int applicantId, Applicant applicant) {

		Applicant exApplicant = applicantDao.getApplicant(applicantId);

		if (exApplicant != null) {

			applicant.setApplicantId(applicantId);
			applicant.setJobApplications(exApplicant.getJobApplications());
			applicant.setResume(exApplicant.getResume());

			applicantDao.addApplicant(applicant);

			ApplicantDto applicantDto = this.modelMapper.map(applicant, ApplicantDto.class);
			responseStructre<ApplicantDto> responseStructre = new responseStructre<>();
			responseStructre.setStatusCode(HttpStatus.OK.value());
			responseStructre.setMessage("Applicant Updated Successfully");
			responseStructre.setData(applicantDto);

			return new ResponseEntity<responseStructre<ApplicantDto>>(responseStructre, HttpStatus.OK);

		} else {
			throw new ApplicantNotFoundById("Applicant Not Found");
		}
	}

	public ResponseEntity<responseStructre<ApplicantDto>> deleteApplicant(int applicantId) {
		Applicant applicant = applicantDao.getApplicant(applicantId);

		if (applicant != null) {
			/*
			 * Before deleting the applicant the applicant is to null in all the
			 * jobApplications later the applicant is delete the applicant
			 */
			for (JobApplication jobApplication : applicant.getJobApplications()) {
				jobApplication.setApplicant(null);
				// createJobApplication() method used to update
				applicationDao.createJobApplication(jobApplication);
			}
			applicantDao.deleteApplicant(applicant);
			Resume resume = applicant.getResume();
			/*
			 * After the applicant is deleted the the resume linked to the applicant should
			 * be deleted
			 */
			if (resume != null) {
				/*
				 * Before deleting the resume the skills should be set to null in the resume.
				 */
				resume.setSkills(null);
				// saveResume() method used to update
				resumeDao.saveResume(resume);
				for (Project project : applicant.getResume().getProjects()) {
					projectDao.deleteProject(project);
				}
				resumeDao.deleteResume(resume);
			}

			ApplicantDto applicantDto = this.modelMapper.map(applicant, ApplicantDto.class);
			responseStructre<ApplicantDto> responseStructure = new responseStructre<>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Applicant deleted successfully.");
			responseStructure.setData(applicantDto);
			return new ResponseEntity<responseStructre<ApplicantDto>>(responseStructure, HttpStatus.OK);
		}
		throw new ApplicantNotFoundById("Failed to delete Applicant!!");

	}

	public List<Applicant> getAllApplicant() {
		return applicantDao.getAllApplicant();
	}

}
