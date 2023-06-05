package edu.project.JobPortalApplication.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.project.JobPortalApplication.dao.ApplicantDao;
import edu.project.JobPortalApplication.dao.ResumeDao;
import edu.project.JobPortalApplication.dto.ResumeDto;
import edu.project.JobPortalApplication.entity.Applicant;
import edu.project.JobPortalApplication.entity.Resume;
import edu.project.JobPortalApplication.exception.ResumeNotFoundById;
import edu.project.JobPortalApplication.util.responseStructre;

@Service
public class ResumeService {

	@Autowired
	private ResumeDao dao;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private ApplicantDao applicantDao;

	public ResponseEntity<responseStructre<Resume>> saveResume(long applicantId, ResumeDto dto) {
		Applicant applicant = applicantDao.getApplicant(applicantId);

		if (applicant != null) {
			Resume existingResume = applicant.getResume();
			Resume resume = this.mapper.map(dto, Resume.class);
			if (existingResume != null) {
				resume.setResumeId(existingResume.getResumeId());
			}

			resume.setApplicant(applicant);
			resume = dao.saveResume(resume);
			applicant.setResume(resume);

			applicantDao.addApplicant(applicant);

			responseStructre<Resume> responseStructre = new responseStructre<>();

			responseStructre.setStatusCode(HttpStatus.CREATED.value());
			responseStructre.setMessage("Resume Added Successfully");
			responseStructre.setData(resume);

			return new ResponseEntity<responseStructre<Resume>>(responseStructre, HttpStatus.CREATED);
		} else {
		
			throw new ResumeNotFoundById("Resume not found with requested id..!!");
		}
	}

	public ResponseEntity<responseStructre<Resume>> getResumeById(long resumeId) {

		Optional<Resume> optional = dao.getResumeById(resumeId);

		if (optional.isPresent()) {

			responseStructre<Resume> responseStructre = new responseStructre<>();

			responseStructre.setStatusCode(HttpStatus.FOUND.value());
			responseStructre.setMessage("Resume Found Successfully");
			responseStructre.setData(optional.get());

			return new ResponseEntity<responseStructre<Resume>>(responseStructre, HttpStatus.FOUND);

		}
		throw new ResumeNotFoundById("Resume not found with requested id..!!");
	}
}
