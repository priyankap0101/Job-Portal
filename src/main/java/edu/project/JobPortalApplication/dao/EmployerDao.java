package edu.project.JobPortalApplication.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import edu.project.JobPortalApplication.entity.Employer;
import edu.project.JobPortalApplication.exception.EmployerNotFoundById;
import edu.project.JobPortalApplication.repositories.EmployerRepo;

@Repository
public class EmployerDao {

	@Autowired
	private EmployerRepo employerRepo;

	public Employer addEmployer(Employer employer) {
		return employerRepo.save(employer);
	}

	public Employer getEmployer(long employerId) {
		// TODO Auto-generated method stub

		Optional<Employer> optional = employerRepo.findById(employerId);

		if (optional.isEmpty()) {

			return null;
		} else {
			return optional.get();
		}
	}

	public Employer updateEmployer(Employer employer, long employerId) {
		Optional<Employer> optional = employerRepo.findById(employerId);

		if (optional.isEmpty()) {

			return null;
		} else {
			employer.setEmployerId(employerId);
			employerRepo.save(employer);
			return optional.get();
		}

	}

	public void deleteEmployer(Employer employer) {
		employerRepo.delete(employer);
	}

	public List<Employer> getAllEmployer() {
		return employerRepo.getAllEmployer();
	}

}
