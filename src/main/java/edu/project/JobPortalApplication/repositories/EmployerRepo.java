package edu.project.JobPortalApplication.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.project.JobPortalApplication.entity.Employer;

public interface EmployerRepo extends JpaRepository<Employer, Long> {

	@Query(value = "select e from Employer e")
	public List<Employer> getAllEmployer();
}
