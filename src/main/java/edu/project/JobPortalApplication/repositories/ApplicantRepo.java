package edu.project.JobPortalApplication.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.project.JobPortalApplication.entity.Applicant;

public interface ApplicantRepo extends JpaRepository<Applicant, Long> {

	@Query(value = "select a from Applicant  a")
	public Optional<List<Applicant>> getAllApplicant();
}
