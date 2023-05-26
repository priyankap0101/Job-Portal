package edu.project.JobPortalApplication.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import edu.project.JobPortalApplication.dto.ApplicantDto;
import edu.project.JobPortalApplication.entity.Applicant;

import edu.project.JobPortalApplication.repositories.ApplicantRepo;
import edu.project.JobPortalApplication.util.responseStructre;

@Repository
public class ApplicantDao {

	@Autowired
	private ApplicantRepo applicantRepo;

	public Applicant addApplicant(Applicant applicant) {
		return applicantRepo.save(applicant);
	}

	public Applicant getApplicant(long applicantId) {

		Optional<Applicant> optional = applicantRepo.findById(applicantId);

		if (optional.isEmpty()) {
			return null;
		} else {
			return optional.get();
		}

	}

	public Applicant updateApplicant(Applicant applicant, long applicantId) {
		Optional<Applicant> optional = applicantRepo.findById(applicantId);

		if (optional.isEmpty()) {
			return null;
		} else {
			applicant.setApplicantId(applicantId);
			applicantRepo.save(applicant);
			return optional.get();
		}

	}

	public void deleteApplicant(Applicant applicant) {

		applicantRepo.delete(applicant);

	}

	public List<Applicant> getAllApplicant() {
		Optional<List<Applicant>> applicants = applicantRepo.getAllApplicant();

		if (applicants.isEmpty()) {
			return null;
		} else {
			return applicants.get();
		}
	}

}
